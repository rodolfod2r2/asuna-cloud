package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@NoArgsConstructor
public class CryptographyConfiguration {

    @Value("${core.symmetric.security.cipher.type}")
    private String symmetricSecurityCipherType;

    @Value("${core.symmetric.security.cipher.provider}")
    private String symmetricSecurityCipherProvider;

    @Value("${core.symmetric.security.cipher.key}")
    private String symmetricSecurityCipherKey;

    @Value("${core.symmetric.security.cipher.iv}")
    private String symmetricSecurityCipherIv;

    @Value("${core.symmetric.security.cipher.algorithm.type}")
    private String symmetricSecurityCipherAlgorithmType;

    @Value("${core.asymmetric.security.cipher.type}")
    private String asymmetricSecurityCipherType;

    @Value("${core.asymmetric.security.keyPairGenerator.type}")
    private String asymmetricSecurityKeyPairGeneratorType;

    @Value("${core.asymmetric.security.keyPairGenerator.size}")
    private String asymmetricSecurityKeyPairGeneratorSize;

}
