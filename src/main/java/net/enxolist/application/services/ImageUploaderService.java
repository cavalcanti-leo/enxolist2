package net.enxolist.application.services;


import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class ImageUploaderService {

    private final Storage storage;
    private final String bucketName;

    public ImageUploaderService(Storage storage,
                                @Value("${firebase.bucket-name}") String bucketName) {
        this.storage = storage;
        this.bucketName = bucketName;
    }

    public String upload(MultipartFile file, String folder) throws IOException {
        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        String objectName = (folder != null && !folder.isBlank() ? folder + "/" : "")
                + UUID.randomUUID() + extension;

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        String encoded = URLEncoder.encode(objectName, StandardCharsets.UTF_8);
        return "https://firebasestorage.googleapis.com/v0/b/"
                + bucketName
                + "/o/"
                + encoded
                + "?alt=media";
    }
}