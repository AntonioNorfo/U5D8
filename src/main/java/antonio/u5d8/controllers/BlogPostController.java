package antonio.u5d8.controllers;

import antonio.u5d8.entities.BlogPost;
import antonio.u5d8.payloads.BlogPostPayload;
import antonio.u5d8.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
        return (Page<BlogPost>) blogPostService.getAllBlogPosts(pageable);
    }

    @GetMapping("/{id}")
    public Optional<BlogPost> getBlogPostById(@PathVariable UUID id) {
        return blogPostService.getBlogPostById(id);
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPostPayload payload) {
        return blogPostService.addBlogPost(payload);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable UUID id, @RequestBody BlogPost updatedBlogPost) {
        return blogPostService.updateBlogPost(id, updatedBlogPost);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable UUID id) {
        blogPostService.deleteBlogPost(id);
    }
}
