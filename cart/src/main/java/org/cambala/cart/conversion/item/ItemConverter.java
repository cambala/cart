package org.cambala.cart.conversion.item;

import org.cambala.aop.scanner.FieldsScanner;
import org.cambala.cart.dto.item.ItemRequest;
import org.cambala.cart.models.item.Item;
import org.cambala.fields.implementation.NumericField;
import org.cambala.fields.implementation.TextField;
import org.cambala.measurement.Measure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public final class ItemConverter {

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
                return FieldsScanner.getTemplateInstance(templateId, map);
            } else {
                // должна быть мапа из двух пар максимум (одна из них с ключом measure)
                if (map.keySet().size() > 2) {
                    throw new IllegalArgumentException("Слишком много атрибутов кастомного поля");
                } else if (map.keySet().size() == 2 && !map.containsKey("measure")) {
                    throw new IllegalArgumentException("Недопустимый атрибутный состав кастомного поля");
                }

                String measure = map.get("measure");
                Measure measureInstance = FieldsScanner.getMeasureInstance(measure);
                var keySet = map.keySet().toArray();
                String key = (String) (!keySet[0].equals("measure") ? keySet[0] : keySet[1]);
                String value = map.get (key);
                Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                if (pattern.matcher(value).matches() && map.containsKey("measure")) {
                    return new NumericField(key, value, measureInstance);
                };
                return new TextField(key, value);
            }

        }).filter(Objects::nonNull).toList();
    }
}
