package com.repsy.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PackageMetadata {
    @Id
    private String id;
    private String name;
    private String version;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String dependencies;
}
