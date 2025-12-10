package com.example.expense.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.sns.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import java.net.URI;

@Configuration
public class AwsConfig {
 @Value("${aws.region}") String region;
 @Value("${aws.endpointOverride:}") String endpoint;

 @Bean public DynamoDbClient dynamo() {
   var b=DynamoDbClient.builder().region(Region.of(region))
     .credentialsProvider(DefaultCredentialsProvider.create())
     .httpClientBuilder(UrlConnectionHttpClient.builder());
   if(!endpoint.isBlank()) b.endpointOverride(URI.create(endpoint));
   return b.build();
 }
 @Bean public S3Client s3() {
   var b=S3Client.builder().region(Region.of(region))
     .credentialsProvider(DefaultCredentialsProvider.create())
     .httpClientBuilder(UrlConnectionHttpClient.builder());
   if(!endpoint.isBlank()) b.endpointOverride(URI.create(endpoint));
   return b.build();
 }
 @Bean public SnsClient sns() {
   var b=SnsClient.builder().region(Region.of(region))
     .credentialsProvider(DefaultCredentialsProvider.create())
     .httpClientBuilder(UrlConnectionHttpClient.builder());
   if(!endpoint.isBlank()) b.endpointOverride(URI.create(endpoint));
   return b.build();
 }
}
