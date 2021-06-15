package com.nikitagru.photostream.services;

import com.nikitagru.photostream.entities.Post;
import com.nikitagru.photostream.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post, Long userId) {
        postRepository.save(post);
        postRepository.createUserPost(userId, post.getId());
    }
}
