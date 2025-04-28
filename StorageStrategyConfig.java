package com.repsy.api.config;

import com.repsy.api.service.StorageStrategy;
import com.repsy.filestorage.FileSystemStorageService;
import com.repsy.objectstorage.ObjectStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageStrategyConfig {

    @Bean
    public StorageStrategy storageStrategy(@Value("${storage.strategy}") String strategy) {
        return "object-storage".equals(strategy)
            ? new ObjectStorageService()
            : new FileSystemStorageService();
    }
}
