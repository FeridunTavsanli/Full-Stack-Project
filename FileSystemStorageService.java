package com.repsy.filestorage;

import com.repsy.api.service.StorageStrategy;

import java.io.*;
import java.nio.file.*;

public class FileSystemStorageService implements StorageStrategy {

    private final Path root = Paths.get("fs_storage");

    public FileSystemStorageService() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(String path, InputStream data) {
        try {
            Files.copy(data, root.resolve(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream retrieve(String path) {
        try {
            return Files.newInputStream(root.resolve(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
