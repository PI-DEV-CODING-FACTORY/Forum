package com.esprit.pidev.codingfactory.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.pidev.codingfactory.Entity.Post;
import com.esprit.pidev.codingfactory.Service.IPost;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private IPost postService;

    @GetMapping("/all/{userId}")
    public List<Post> findAllPosts(@PathVariable("userId") int userId) {
        return postService.findAllPosts(userId);
    }

    @GetMapping("/all")
    public List<Post> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/find/technology")
    public List<String> findDistinctTechnologies() {
        return postService.findDistinctTechnologies();
    }

    @GetMapping("/find/comment/{id}")
    public List<Post> findCommentsByPostId(@PathVariable("id") int id) {
        return postService.findCommentsByPostId(id);
    }

    @GetMapping("/find/{id}")
    public Post findPostById(@PathVariable("id") int id) {
        return postService.findPostById(id);
    }

    @PostMapping("/add")
    public void addPost(@RequestBody Post p) {
        postService.addPost(p);
    }

    @PutMapping("/update/{id}")
    public void updatePostById(@RequestBody Post p, @PathVariable("id") int id) {
        postService.updatePostById(p, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") int id) {
        postService.deletePost(id);
    }

    @PostMapping("/upload/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return postService.saveImage(file);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser_id(@PathVariable("userId") int userId) {
        return postService.getPostsByUser_id(userId);
    }
    
    @PostMapping("/report/{postId}")
    public void reportPost(@PathVariable("postId") int postId) {
        postService.reportPost(postId);
    }
    
    @PostMapping("/best-answer/{postId}/{commentId}")
    public void markAsBestAnswer(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId) {
        postService.markAsBestAnswer(postId, commentId);
    }
}
