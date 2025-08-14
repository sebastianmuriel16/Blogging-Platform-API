package spring_6_practica.Blogging.Platform.services;

import org.springframework.http.ResponseEntity;
import spring_6_practica.Blogging.Platform.model.BlogPostDTO;

import java.util.List;

public interface BlogPostService {

    BlogPostDTO newBlosgPost(BlogPostDTO blogPostDTO);

    List<BlogPostDTO> listBlogPost(String term);

    BlogPostDTO updatedBlogPost(Integer id,BlogPostDTO blogPostDTO);

    void deleteBlogPost(Integer id);

    BlogPostDTO getBlogPost(Integer id);

}
