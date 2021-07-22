package dev.lillian.amberapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@Service
public final class ImageService {
    private static final String[] ACCEPTABLE_EXTENSIONS = {
            ".jpg",
            ".jpeg",
            ".png",
            ".gif",
            ".mp4"
    };

    private final Random random = new Random();
    private final File imageDir;

    public ImageService(@Value("${image-path}") String imagePath) throws IOException {
        imageDir = new File(imagePath);
        Files.createDirectories(imageDir.toPath());
    }

    public String getRandomImageName() {
        File[] files = imageDir.listFiles((dir, name) -> {
            for (String acceptableExtension : ACCEPTABLE_EXTENSIONS) {
                if (name.endsWith(acceptableExtension)) {
                    return true;
                }
            }

            return false;
        });

        if (files == null || files.length == 0) {
            return null;
        }

        if (files.length == 1) {
            return files[0].getName();
        }

        return files[random.nextInt(files.length)].getName();
    }
}
