package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory;

import org.springframework.stereotype.Component;

import static org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory.EnumFactory.ASYMMETRIC;


@Component
public class EncryptFactory {

    final
    Symmetric symmetric;

    final
    Asymmetric asymmetric;

    public EncryptFactory(Symmetric symmetric, Asymmetric asymmetric) {
        this.symmetric = symmetric;
        this.asymmetric = asymmetric;
    }

    public String getEncrypt(EnumFactory enumFactory, String text) {
        if (enumFactory.equals(ASYMMETRIC)) {
            return asymmetric.encrypted(text);
        } else {
            return symmetric.encrypted(text);
        }
    }

    public String getDecrypt(EnumFactory enumFactory, String text) {
        if (enumFactory.equals(ASYMMETRIC)) {
            return asymmetric.decrypted(text);
        } else {
            return symmetric.decrypted(text);
        }
    }
}
