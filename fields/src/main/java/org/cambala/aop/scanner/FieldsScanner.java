package org.cambala.aop.scanner;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.templates.AbstractTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class FieldsScanner {

    private static final String basePackage = "org.cambala"; // TODO::как это расхардкодить?
    private static final String basePath = basePackage.replace('.', '/');
    private static final Map<String, Class> templates = new HashMap<>();

    static {
        try {
            scan();
        } catch (IOException
                 | URISyntaxException
                 | ClassNotFoundException
                 | InstantiationException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void scan() throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources = classLoader.getResources(basePath);

        collectTemplates(resources);
    }

    private static void collectTemplates(Enumeration<URL> resources) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            Path rootDirectoryPath = new File(resource.toURI()).toPath();
            List<File> files = getFiles(rootDirectoryPath);

            scanFiles(files);
        }
    }

    private static List<File> getFiles(Path rootDirectory) {
        List<File> files;
        try (Stream<Path> pathsTree = Files.walk(rootDirectory)) {
            files = pathsTree
                    .filter(path -> !path.toString().contains("test-classes") && path.toString().endsWith(".class"))
                    .map(path -> new File(path.toString()))
                    .toList();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return files;
    }

    private static void scanFiles(List<File> files) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (File file : files) {
            String filePath = file.getPath();
            if (filePath.endsWith(".class")) {
                String className = filePath.split(basePath)[1].replace("/", ".").replace(".class", "");
                Class classObject = Class.forName(basePackage + className);
                if (classObject.isAnnotationPresent(Template.class)) {
                    AbstractTemplate instance = (AbstractTemplate) classObject.newInstance();
                    if (FieldsScanner.templates.containsKey(instance.getTemplateId())) {
                        throw new IllegalStateException(String.format("Шаблон поля с id: %s уже существует", instance.getTemplateId()));
                    }
                    FieldsScanner.templates.put(instance.getTemplateId(), classObject);
                }
            }
        }
    }

    public static Map<String, Class> getTemplates() {
        return FieldsScanner.templates;
    }
}
