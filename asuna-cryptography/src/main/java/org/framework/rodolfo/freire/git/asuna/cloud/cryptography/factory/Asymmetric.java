package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.factory;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.config.CryptographyConfiguration;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

@Slf4j
@Service
public class Asymmetric implements Encrypt {

    private final CryptographyConfiguration cryptographyConfiguration;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Asymmetric(CryptographyConfiguration cryptographyConfiguration) {
        this.cryptographyConfiguration = cryptographyConfiguration;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(cryptographyConfiguration.getAsymmetricSecurityKeyPairGeneratorType());
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException ex) {
            log.error("");
        }

    }

    @Override
    public String encrypted(String text) {
        try {
            Cipher encrypted = Cipher.getInstance(cryptographyConfiguration.getAsymmetricSecurityCipherType());
            encrypted.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(encrypted.doFinal(text.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }


    @Override
    public String decrypted(String text) {
        try {
            Cipher decrypted = Cipher.getInstance(cryptographyConfiguration.getAsymmetricSecurityCipherType());
            decrypted.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decodedValue = Base64.getDecoder().decode(text);
            byte[] decValue = decrypted.doFinal(decodedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }


}
