# Full-Stack Package Repository

Spring Boot application for managing `.rep` packages and metadata for the Repsy language.

## Features
- Upload and download of `.rep` and `meta.json` files
- Pluggable storage strategies: file-system and MinIO (object-storage)
- PostgreSQL database for tracking metadata
- Dockerized setup with docker-compose
- Uses storage libraries from a Maven repo

## Endpoints
- POST `/{packageName}/{version}` (multipart: package.rep, meta.json)
- GET `/{packageName}/{version}/{fileName}`

## Configuration

`application.properties`:
```
storage.strategy=file-system
```

## Docker

```
docker-compose up --build
```
