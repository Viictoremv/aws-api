package com.victor.testApi.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class BucketService {
    
    private S3Client s3Client;
    private final String bucketName = "aws-photos-victor-mendoza";

    public BucketService(){
        this.s3Client = S3Client.create();
    }

    public void uploadPhoto(MultipartFile profilePhoto, String fileName){
        try {
            PutObjectRequest putObject = PutObjectRequest
            .builder()
            .bucket(bucketName)
            .key(fileName + ".jpg")
            .acl(ObjectCannedACL.PUBLIC_READ)
            .build();
            s3Client.putObject(putObject, RequestBody.fromBytes(profilePhoto.getBytes()));
            System.out.println("Successfully placed " + " into bucket "+ bucketName);
        } catch (S3Exception e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
