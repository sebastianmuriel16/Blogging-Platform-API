package spring_6_practica.Blogging.Platform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring_6_practica.Blogging.Platform.model.BlogPostDTO;
import spring_6_practica.Blogging.Platform.services.BlogPostService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogPostController {

    private final static String BLOGPOST_PATH = "/api/v1/post";
    private final static String BLOGPOST_PATH_ID = BLOGPOST_PATH + "/{id}";
    private final BlogPostService blogPostService;


    @GetMapping(BLOGPOST_PATH_ID)
    public ResponseEntity<BlogPostDTO> getBlogPost(@PathVariable("id") Integer id){
        BlogPostDTO blogPost = blogPostService.getBlogPost(id);

        return ResponseEntity.ok(blogPost);
    }

    @DeleteMapping(BLOGPOST_PATH_ID)
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Integer id){
        blogPostService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(BLOGPOST_PATH_ID)
    public ResponseEntity<BlogPostDTO> updateBlogPost(@PathVariable("id") Integer id,@Valid @RequestBody BlogPostDTO blogPostDTO){
        BlogPostDTO upadatedBlogPost = blogPostService.updatedBlogPost(id,blogPostDTO);

        return ResponseEntity.ok(upadatedBlogPost);
    }

    @PostMapping(BLOGPOST_PATH)
    public ResponseEntity<BlogPostDTO> createBlogPost(@Valid @RequestBody BlogPostDTO blogPostDTO){
        BlogPostDTO savedBlogPost = blogPostService.newBlosgPost(blogPostDTO);

        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBlogPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedBlogPost);
    }

    @GetMapping(BLOGPOST_PATH)
    public List<BlogPostDTO> listPosts(@RequestParam(required = false) String term){
        return blogPostService.listBlogPost(term);
    }
}
