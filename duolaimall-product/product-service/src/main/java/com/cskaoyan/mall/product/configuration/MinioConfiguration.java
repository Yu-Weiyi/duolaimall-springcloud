package com.cskaoyan.mall.product.configuration;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.configuration
 * @name MinioConfiguration
 * @description
 * @since 2024-09-22 18:12
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfiguration {

    String endpointUrl;
    String accessKey;
    String secreKey;
    String bucketName;

    @Bean
    public MinioClient minioClient(){

        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpointUrl)
                .credentials(accessKey, secreKey)
                .build();
        return minioClient;
    }
}
