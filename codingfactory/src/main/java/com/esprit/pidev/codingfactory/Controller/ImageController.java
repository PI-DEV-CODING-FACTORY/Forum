package com.esprit.pidev.codingfactory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.pidev.codingfactory.Service.ImageService;


@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    final String uploadPath="D:/images/";

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        return imageService.getImage(filename);
    }

   
}
