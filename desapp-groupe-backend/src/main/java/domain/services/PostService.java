package domain.services;

import domain.Post;
import domain.User;
import domain.repositories.PostRepository;
import domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(){}

    public PostService(PostRepository postRepository,UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Post> posts(Integer id) {
        User user = userRepository.findById(id);
        List<Post> list = postRepository.findByUser(user);
        return list.stream().sorted().collect(Collectors.toList());
    }

    @Transactional
    public void createPost(Post post) {
        Post newPost = new Post(post.getPublisher(),post.getDate(),post.getContent(),post.getWallOwner());
        postRepository.save(newPost);
    }
}
