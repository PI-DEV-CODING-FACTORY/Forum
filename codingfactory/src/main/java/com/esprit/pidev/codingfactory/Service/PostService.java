package com.esprit.pidev.codingfactory.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.pidev.codingfactory.Entity.Post;
import com.esprit.pidev.codingfactory.Repository.PostRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService implements IPost {

    PostRepository postRepository;
    final String uploadPath = "D:/images/";

    @Override
    public List<Post> findAllPosts(int user_id) {
        return postRepository.findAllPosts(user_id);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllPosts();
    }

    @Override
    public Post findPostById(int id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void updatePostById(Post post, int id) {
        postRepository.updatePostById(post, id);
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<String> findDistinctTechnologies() {
        return postRepository.findDistinctTechnologies();
    }

    @Override
    public List<Post> findCommentsByPostId(int id) {
        return postRepository.findCommentsByPostId(id);
    }

    @Override
    public List<Post> getPostsByUser_id(int userId) {
        return postRepository.getPostsByUser_id(userId);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Générez un nom unique pour le fichier
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Copiez le fichier dans le dossier d'upload
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        System.out.println(filePath);
        // Retournez le chemin relatif de l'image enregistrée
        return fileName.toString();
    }

    @Override
    public void reportPost(int postId) {
        Post post = postRepository.findById(postId).get();
        post.setReportCount(post.getReportCount() + 1);
        
        if (post.getReportCount() >= 5) {
            postRepository.deleteById(postId);
        } else {
            postRepository.save(post);
        }
    }

    @Override
    public void markAsBestAnswer(int postId, int commentId) {
        Post post = postRepository.findById(postId).get();
        post.setBestAnswerId(commentId);
        postRepository.save(post);
    }

}
