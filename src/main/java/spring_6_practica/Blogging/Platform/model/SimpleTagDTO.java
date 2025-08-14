package spring_6_practica.Blogging.Platform.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleTagDTO {
    private Integer id;
    private String name;
}
