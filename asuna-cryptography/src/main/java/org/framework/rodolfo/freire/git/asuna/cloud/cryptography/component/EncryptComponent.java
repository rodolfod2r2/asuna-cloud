package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.component;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.annotation.EncryptField;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory.EncryptFactory;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory.EnumFactory;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util.CustomObject;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util.CustomReflection;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.framework.rodolfo.freire.git.asuna.cloud.cryptography.annotation.EnumEncrypt.TRUE;

@Slf4j
@Component
public class EncryptComponent {

    private final EncryptFactory factory;

    public EncryptComponent(EncryptFactory factory) {
        this.factory = factory;
    }

    private String getFieldValue(Object object, Field field) {
        String value = "";
        if (object != null) {
            value = String.valueOf(CustomReflection.getAttributeValue(object, field.getName()));
        }
        return value;
    }

    public void decryptObject(Object object, EnumFactory enumFactory) {

        List<Field> fields = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredFields()));
        fields.addAll(new ArrayList<>(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields())));

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(EncryptField.class)) {
                EncryptField encryptField = field.getAnnotation(EncryptField.class);

                String valueField = getFieldValue(object, field);
                if (encryptField.decryptField().equals(TRUE) && CustomObject.notBlank(valueField)) {
                    valueField = factory.getDecrypt(enumFactory, valueField);
                }
                CustomReflection.setAttributeValue(object, field.getName(), valueField);
            }
        }

    }

    public void encryptObject(Object object, EnumFactory enumFactory) {

        List<Field> fields = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredFields()));
        fields.addAll(new ArrayList<>(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields())));

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(EncryptField.class)) {
                EncryptField encryptField = field.getAnnotation(EncryptField.class);

                String valueField = getFieldValue(object, field);
                if (encryptField.encryptField().equals(TRUE) && CustomObject.notBlank(valueField)) {
                    valueField = factory.getEncrypt(enumFactory, valueField);
                }
                CustomReflection.setAttributeValue(object, field.getName(), valueField);
            }
        }
    }
}
