package spring_6_practica.Blogging.Platform.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import spring_6_practica.Blogging.Platform.entities.BlogPost;
import spring_6_practica.Blogging.Platform.entities.Tag;
import spring_6_practica.Blogging.Platform.exception.CustomException;
import spring_6_practica.Blogging.Platform.mapper.BlogPostMapper;
import spring_6_practica.Blogging.Platform.mapper.TagMapper;
import spring_6_practica.Blogging.Platform.model.BlogPostDTO;
import spring_6_practica.Blogging.Platform.model.TagDTO;
import spring_6_practica.Blogging.Platform.repositories.BlogPostRepositorie;
import spring_6_practica.Blogging.Platform.repositories.TagRepositorie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostMapper blogPostMapper;
    private final TagMapper tagMapper;
    private final BlogPostRepositorie blogPostRepositorie;
    private final TagRepositorie tagRepositorie;

    @Override
    public BlogPostDTO newBlosgPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostMapper.toEntity(blogPostDTO);

        Set<Tag> tags = blogPostDTO.getTags().stream()
                .map(tagDTO ->{
                    return tagRepositorie.findByName(tagDTO.getName())
                            .orElseGet(() -> {
                                Tag tag = tagMapper.toEntity(tagDTO);
                                return tagRepositorie.save(tag);
                            });
                })
                .collect(Collectors.toSet());

        blogPost.setTags(tags);
        blogPostRepositorie.save(blogPost);
        return blogPostMapper.toDTO(blogPost);
    }

    @Override
    public List<BlogPostDTO> listBlogPost(String term) {

        List<BlogPost> posts = StringUtils.hasText(term)
                ? blogPostRepositorie.searchByTerm(term)
                : blogPostRepositorie.findAll();

        return posts.stream()
                .map(blogPostMapper::toDTO)
                .toList();
    }

    @Override
    public BlogPostDTO updatedBlogPost(Integer id,BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostRepositorie.findById(id)
                .orElseThrow(() -> new CustomException(("No blog post found with id " + id),404));

        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());
        blogPost.setCategory(blogPostDTO.getCategory());

        Set<Tag> tags = blogPostDTO.getTags().stream()
                .map(tagDTO ->{
                    return tagRepositorie.findByName(tagDTO.getName())
                            .orElseGet(() -> {
                                Tag tag = tagMapper.toEntity(tagDTO);
                                return tagRepositorie.save(tag);
                            });
                })
                .collect(Collectors.toSet());

        blogPost.setTags(tags);
        blogPostRepositorie.save(blogPost);
        return blogPostMapper.toDTO(blogPost);
    }

    @Override
    public void deleteBlogPost(Integer id) {
        BlogPost blogPost = blogPostRepositorie.findById(id)
                .orElseThrow(() -> new CustomException(("No blog post found with id " + id),404));
         blogPostRepositorie.delete(blogPost);
    }

    @Override
    public BlogPostDTO getBlogPost(Integer id) {

        BlogPost blogPost = blogPostRepositorie.findById(id)
                .orElseThrow(() -> new CustomException(("No blog post found with id " + id),404));

        return blogPostMapper.toDTO(blogPost);

    }
}
