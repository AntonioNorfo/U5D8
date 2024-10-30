package antonio.u5d8.services;

import antonio.u5d8.entities.Autore;
import antonio.u5d8.entities.BlogPost;
import antonio.u5d8.exception.ResourceNotFoundException;
import antonio.u5d8.payloads.BlogPostPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {
    private List<BlogPost> blogPosts = new ArrayList<>();

    @Autowired
    private AutoreService autoreService;

    public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> getBlogPostById(UUID id) {
        return blogPosts.stream().filter(bp -> bp.getBlog_id().equals(id)).findFirst();
    }

    public BlogPost addBlogPost(BlogPostPayload payload) {
        Autore autore = autoreService.getAutoreById(payload.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato"));

        BlogPost blogPost = new BlogPost(
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getCategoria(),
                payload.getTempoLettura(),
                autore
        );
        blogPost.setBlog_id(UUID.randomUUID());
        blogPost.setCover("https://picsum.photos/200/300");
        blogPosts.add(blogPost);
        return blogPost;
    }

    public BlogPost updateBlogPost(UUID id, BlogPost updatedBlogPost) {
        BlogPost blogPost = getBlogPostById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost non trovato"));

        blogPost.setTitolo(updatedBlogPost.getTitolo());
        blogPost.setContenuto(updatedBlogPost.getContenuto());
        blogPost.setCategoria(updatedBlogPost.getCategoria());
        blogPost.setTempoLettura(updatedBlogPost.getTempoLettura());
        blogPost.setAutore(updatedBlogPost.getAutore());
        return blogPost;
    }

    public void deleteBlogPost(UUID id) {
        blogPosts.removeIf(bp -> bp.getBlog_id().equals(id));
    }
}
