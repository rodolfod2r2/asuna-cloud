package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface EncryptField {

    EnumEncrypt encryptField() default EnumEncrypt.FALSE;

    EnumEncrypt decryptField() default EnumEncrypt.FALSE;

}
