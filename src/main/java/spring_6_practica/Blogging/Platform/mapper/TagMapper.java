package spring_6_practica.Blogging.Platform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring_6_practica.Blogging.Platform.entities.Tag;
import spring_6_practica.Blogging.Platform.model.SimpleTagDTO;
import spring_6_practica.Blogging.Platform.model.TagDTO;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagDTO tagDTO);

    Tag toEntity(SimpleTagDTO simpleTagDTO);

    @Mapping(target = "blogPosts", ignore = true)
    TagDTO toDTO(Tag tag);


    SimpleTagDTO toSimpleDTO(Tag tag);
}
