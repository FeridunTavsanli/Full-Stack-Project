package com.repsy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.api.model.PackageMetadata;
import com.repsy.api.repository.PackageMetadataRepository;
import com.repsy.api.service.StorageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PackageController {

    @Autowired
    private StorageStrategy storageStrategy;

    @Autowired
    private PackageMetadataRepository repository;

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<String> upload(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestPart("package") MultipartFile pkg,
            @RequestPart("meta") MultipartFile meta) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PackageMetadata metadata = mapper.readValue(meta.getInputStream(), PackageMetadata.class);
            metadata.setId(UUID.randomUUID().toString());
            repository.save(metadata);
            storageStrategy.store(packageName + "/" + version + "/package.rep", pkg.getInputStream());
            storageStrategy.store(packageName + "/" + version + "/meta.json", meta.getInputStream());
            return ResponseEntity.ok("Uploaded");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid upload");
        }
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<byte[]> download(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName) {
        try {
            InputStream in = storageStrategy.retrieve(packageName + "/" + version + "/" + fileName);
            return ResponseEntity.ok(in.readAllBytes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
