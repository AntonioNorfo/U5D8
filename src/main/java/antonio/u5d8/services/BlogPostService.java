package antonio.u5d8.services;

import antonio.u5d8.entities.Autore;
import antonio.u5d8.entities.BlogPost;
import antonio.u5d8.exception.ResourceNotFoundException;
import antonio.u5d8.payloads.BlogPostPayload;
import antonio.u5d8.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    // Metodo di istanza che usa findAll
    public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> getBlogPostById(UUID id) {
        return blogPostRepository.findById(id);
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
        blogPost.setCover("https://picsum.photos/200/300");
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updateBlogPost(UUID id, BlogPost updatedBlogPost) {
        BlogPost blogPost = getBlogPostById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost non trovato"));

        blogPost.setTitolo(updatedBlogPost.getTitolo());
        blogPost.setContenuto(updatedBlogPost.getContenuto());
        blogPost.setCategoria(updatedBlogPost.getCategoria());
        blogPost.setTempoLettura(updatedBlogPost.getTempoLettura());
        blogPost.setAutore(updatedBlogPost.getAutore());
        return blogPostRepository.save(blogPost);
    }

    public void deleteBlogPost(UUID id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResourceNotFoundException("BlogPost non trovato");
        }
        blogPostRepository.deleteById(id);
    }
}
