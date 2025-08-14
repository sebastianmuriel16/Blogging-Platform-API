package spring_6_practica.Blogging.Platform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring_6_practica.Blogging.Platform.entities.BlogPost;
import spring_6_practica.Blogging.Platform.model.BlogPostDTO;
import spring_6_practica.Blogging.Platform.model.SimpleTagDTO;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface BlogPostMapper {

    BlogPost toEntity(BlogPostDTO blogPostDTO);

    @Mapping(target = "tags", source = "tags")
    BlogPostDTO toDTO(BlogPost blogPost);

}
