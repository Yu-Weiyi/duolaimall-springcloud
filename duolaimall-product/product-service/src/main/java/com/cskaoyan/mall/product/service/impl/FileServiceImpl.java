package com.cskaoyan.mall.product.service.impl;

import com.cskaoyan.mall.product.configuration.MinioConfiguration;
import com.cskaoyan.mall.product.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name FileServiceImpl
 * @description
 * @since 2024-09-22 18:03
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    MinioConfiguration minioConfiguration;
    @Autowired
    MinioClient minioClient;

    private static final long partSize = -1;

    @Override
    public String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        String objectName = UUID.randomUUID().toString().replaceAll("-", "");
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioConfiguration.getBucketName())
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), partSize)
                        .object(objectName)
                        .build()
        );
        String url = minioConfiguration.getEndpointUrl() + "/" + minioConfiguration.getBucketName() + "/" + objectName;
        return url;
    }
}
