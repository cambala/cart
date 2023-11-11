package org.cambala.aop.scanner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cambala.aop.Template;
import org.cambala.measurement.Measure;
import org.cambala.templates.AbstractTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class FieldsScanner {

    private static final String basePackage = "org.cambala"; // TODO::как это расхардкодить?
    private static final String basePath = basePackage.replace('.', '/');
    private static final Map<String, Class> TEMPLATES = new HashMap<>();
    private static final Map<String, Class> MEASUREMENT = new HashMap<>();

    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

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
                    if (FieldsScanner.TEMPLATES.containsKey(instance.getTemplateId())) {
                        throw new IllegalStateException(String.format("Шаблон поля с id: %s уже существует", instance.getTemplateId()));
                    }
                    FieldsScanner.TEMPLATES.put(instance.getTemplateId(), classObject);
                } else {
                    boolean isImplementationOfMeasureInterface = Set.of(classObject.getInterfaces()).contains(Measure.class);
                    if (isImplementationOfMeasureInterface) {
                        Measure[] instances = (Measure[]) classObject.getEnumConstants();
                        for (Object measureKey : instances) {
                            if (FieldsScanner.MEASUREMENT.containsKey(measureKey.toString())) {
                                throw new IllegalStateException(String.format("Единица измерения с id: %s уже существует", measureKey));
                            }
                            FieldsScanner.MEASUREMENT.put(measureKey.toString(), classObject);
                        }
                    }
                }
            }
        }
    }

    public static Map<String, Class> getTemplates() {
        return FieldsScanner.TEMPLATES;
    }

    public static Map<String, Class> getMeasurement() {
        return FieldsScanner.MEASUREMENT;
    }

    public static Measure getMeasureInstance(String value) {
        if (value == null) {
            return null;
        }
        Class measureClass = MEASUREMENT.get(value);
        Measure measureInstance = null;
        for (Object e : measureClass.getEnumConstants()) {
            if (e.toString().equals(value)) {
                measureInstance = (Measure) e;
            }
        }
        if (measureInstance == null) {
            throw new IllegalArgumentException(String.format("Неизвестное значение единицы измерения: %s", value));
        }
        return measureInstance;
    }

    public static Object getTemplateInstance(String templateId, Map<String, String> fieldValue) {
        Class templateClass = TEMPLATES.get(templateId);
        if (templateClass == null) {
            throw new IllegalArgumentException(String.format("Шаблон поля с id %s не найден", templateId));
        }
        return mapper.convertValue(fieldValue, templateClass);
    }
}
