package com.repsy.api.service;

import java.io.InputStream;

public interface StorageStrategy {
    void store(String path, InputStream data);
    InputStream retrieve(String path);
}
