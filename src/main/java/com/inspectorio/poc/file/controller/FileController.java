package com.inspectorio.poc.file.controller;

import com.inspectorio.poc.file.model.FileMetadata;
import com.inspectorio.poc.file.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileStorageService.store(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getFileMetadata(@PathVariable Long id) {
        return ResponseEntity.ok(fileStorageService.getFile(id));
    }
}
