package spring_6_practica.Blogging.Platform.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class TagDTO {

    private Integer id;

    private String name;

    private Set<BlogPostDTO> blogPosts = new HashSet<>();
}
