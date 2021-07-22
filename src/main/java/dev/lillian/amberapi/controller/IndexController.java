package dev.lillian.amberapi.controller;

import dev.lillian.amberapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public final class IndexController {
    private final ImageService imageService;
    private final String imageUrl;

    @Autowired
    public IndexController(ImageService imageService, @Value("${image-url}") String imageUrl) {
        this.imageService = imageService;
        this.imageUrl = imageUrl;
    }

    @GetMapping("")
    public ResponseEntity<String> index() {
        String name = imageService.getRandomImageName();
        if (name == null) {
            return ResponseEntity.badRequest().body("No images found!");
        }

        return ResponseEntity.ok(imageUrl + name);
    }
}
