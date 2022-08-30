package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util;

import lombok.var;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class CustomObject {

    public CustomObject() {

    }

    public static Boolean isBlank(Object obj) {
        try {

            if (obj == null) {
                return true;
            }

            if (obj instanceof Collection) {
                Iterator iterator = ((Collection) obj).iterator();
                Object o;
                do {
                    if (!iterator.hasNext()) {
                        return true;
                    }
                    o = iterator.next();
                } while (!notBlank(o));
                return false;
            }

            if (obj instanceof String) {
                if (CustomText.isBlankOrNull((String) obj)) {
                    return true;
                }
            } else if (obj instanceof Number) {
                if (obj == null) {
                    return true;
                }
            } else {
                if (obj instanceof Boolean) {
                    return false;
                }

                if (obj.getClass().isArray()) {
                    int length = Array.getLength(obj);

                    for (int i = 0; i < length; ++i) {
                        Object arrayElement = Array.get(obj, i);
                        if (notBlank(arrayElement)) {
                            return false;
                        }
                    }

                    return true;
                }

                if (obj instanceof AbstractMap && ((AbstractMap) obj).isEmpty()) {
                    return true;
                }
            }

            boolean existGetters = false;
            boolean nullable = true;
            List<Field> fields = new ArrayList<>();
            getAllFields(fields, obj.getClass());

            for (Field field : fields) {
                if (CustomReflection.existGet(obj, field.getName())) {
                    existGetters = true;
                    if (notBlank(CustomReflection.getAttributeValue(obj, field.getName()))) {
                        nullable = false;
                        break;
                    }
                }
            }

            if (existGetters && nullable) {
                return true;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return false;
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {

        for (Field field : type.getDeclaredFields()) {
            if (field.getType().getName().equalsIgnoreCase(type.getName())) {
                if (!field.getType().getName().equalsIgnoreCase(type.getName())) {
                    fields.add(field);
                }
            } else {
                fields.add(field);
            }
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static Boolean notBlank(Object obj) {
        return !isBlank(obj);
    }

    public static Boolean notBlank(Object... objs) {
        if (objs == null) {
            return false;
        } else {
            for (Object o : objs) {
                if (isBlank(o)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static Boolean isBlank(Object... objs) {
        if (objs != null) {
            for (Object o : objs) {
                if (!isBlank(o)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static <E> Object or(E o1, E o2) {
        return notBlank(o1) ? o1 : o2;
    }

    public static <E> E coalesce(E o1, E o2) {
        return notBlank(o1) ? o1 : o2;
    }

    @SafeVarargs
    public static <E> E coalesce(E... elements) {

        for (E o : elements) {
            if (notBlank(o)) {
                return o;
            }
        }
        return null;
    }

}
