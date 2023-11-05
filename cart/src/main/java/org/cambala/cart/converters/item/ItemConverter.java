package org.cambala.cart.converters.item;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cambala.aop.scanner.FieldsScanner;
import org.cambala.cart.dto.item.ItemRequest;
import org.cambala.cart.models.catalog.Item;
import org.cambala.fields.implementation.TextField;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class ItemConverter {

    private static final Map<String, Class> TEMPLATES = FieldsScanner.getTemplates();

    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

    public static Item toItem(ItemRequest request) {
        List<Object> attributes = toAttributes(request.getAttributes());
        return Item.builder()
                .id(Objects.requireNonNullElse(request.getId(), UUID.randomUUID()))
                .attributes(attributes)
                .build();
    }

    private static List<Object> toAttributes(List<Map<String, String>> attributes) {
        return attributes.stream().map(map -> {
            if (map.containsKey("templateId")) {
                String templateId = map.get("templateId");
                Class templateClass = TEMPLATES.get(templateId);
                return mapper.convertValue(map, templateClass);
            } else {
                // должна быть мапа из одной пары
                String key = (String) map.keySet().toArray()[0];
                String value = map.get(key);
//                Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
//                if (pattern.matcher(value).matches() && map.containsKey("measure")) {
//                    return new NumericField(key, value);
//                };
                return new TextField(key, value);
            }

        }).filter(Objects::nonNull).toList();
    }
}
