package com.esprit.pidev.codingfactory.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.esprit.pidev.codingfactory.Entity.Post;

public interface IPost {
    public List<Post> findAllPosts(int user_id);
    public List<Post> findAllPosts();
    public Post findPostById(int id);

    public void addPost(Post post);

    public void updatePostById(Post post, int id);

    public void deletePost(int id);

    List<String> findDistinctTechnologies();

    List<Post> findCommentsByPostId(int id);

    List<Post> getPostsByUser_id(int userId);

    public String saveImage(MultipartFile file) throws IOException;
}
