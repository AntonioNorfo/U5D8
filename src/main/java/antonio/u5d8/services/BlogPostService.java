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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> getBlogPostById(UUID id) {
        return blogPostRepository.findById(id);
    }

    @Transactional
    public BlogPost addBlogPost(BlogPostPayload payload) {

        Autore autore = autoreService.getAutoreById(payload.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato con ID: " + payload.getAutoreId()));


        BlogPost blogPost = new BlogPost();
        blogPost.setTitolo(payload.getTitolo());
        blogPost.setContenuto(payload.getContenuto());
        blogPost.setCategoria(payload.getCategoria());
        blogPost.setTempoLettura(payload.getTempoLettura());
        blogPost.setCover("https://picsum.photos/200/300");
        blogPost.setAutore(autore);

        return blogPostRepository.save(blogPost);
    }

    @Transactional
    public BlogPost updateBlogPost(UUID id, BlogPost updatedBlogPost) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost non trovato"));

        blogPost.setTitolo(updatedBlogPost.getTitolo());
        blogPost.setContenuto(updatedBlogPost.getContenuto());
        blogPost.setCategoria(updatedBlogPost.getCategoria());
        blogPost.setTempoLettura(updatedBlogPost.getTempoLettura());
        blogPost.setAutore(updatedBlogPost.getAutore());
        return blogPostRepository.save(blogPost);
    }

    @Transactional
    public void deleteBlogPost(UUID id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResourceNotFoundException("BlogPost non trovato");
        }
        blogPostRepository.deleteById(id);
    }
}
