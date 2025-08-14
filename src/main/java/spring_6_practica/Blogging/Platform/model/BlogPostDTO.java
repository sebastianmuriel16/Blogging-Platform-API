package spring_6_practica.Blogging.Platform.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class BlogPostDTO {

    private Integer id;

    @NotNull(message = "The title cannot be null")
    @NotBlank(message = "The title cannot be empty")
    private String title;

    @NotNull(message = "The content cannot be null")
    @NotBlank(message = "The content cannot be empty")
    private String content;

    @NotNull(message = "The category cannot be null")
    @NotBlank(message = "The category cannot be empty")
    private String category;

    @NotEmpty(message = "There must be at least one associated tag")
    private Set<SimpleTagDTO> tags = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
