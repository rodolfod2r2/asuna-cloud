package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.config.CryptographyConfiguration;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

@Slf4j
@Service
public class Symmetric implements Encrypt {

    private final CryptographyConfiguration cryptographyConfiguration;

    public Symmetric(CryptographyConfiguration cryptographyConfiguration) {
        this.cryptographyConfiguration = cryptographyConfiguration;
    }


    @Override
    public String encrypted(String text) {
        try {
            Cipher encrypted = Cipher.getInstance(cryptographyConfiguration.getSymmetricSecurityCipherType(), cryptographyConfiguration.getSymmetricSecurityCipherProvider());
            SecretKeySpec key = new SecretKeySpec(cryptographyConfiguration.getSymmetricSecurityCipherKey().getBytes(StandardCharsets.UTF_8), cryptographyConfiguration.getSymmetricSecurityCipherAlgorithmType());
            encrypted.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(cryptographyConfiguration.getSymmetricSecurityCipherIv().getBytes(StandardCharsets.UTF_8)));
            return Base64.getEncoder().encodeToString(encrypted.doFinal(text.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException | NoSuchProviderException | InvalidAlgorithmParameterException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public String decrypted(String text) {
        try {
            Cipher decrypted = Cipher.getInstance(cryptographyConfiguration.getSymmetricSecurityCipherType(), cryptographyConfiguration.getSymmetricSecurityCipherProvider());
            SecretKeySpec key = new SecretKeySpec(cryptographyConfiguration.getSymmetricSecurityCipherKey().getBytes(StandardCharsets.UTF_8), cryptographyConfiguration.getSymmetricSecurityCipherAlgorithmType());
            decrypted.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cryptographyConfiguration.getSymmetricSecurityCipherIv().getBytes(StandardCharsets.UTF_8)));
            byte[] decodedValue = Base64.getDecoder().decode(text);
            byte[] decValue = decrypted.doFinal(decodedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException | NoSuchProviderException | InvalidAlgorithmParameterException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

}
