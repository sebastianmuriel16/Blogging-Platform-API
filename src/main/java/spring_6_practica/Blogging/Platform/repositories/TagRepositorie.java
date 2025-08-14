package spring_6_practica.Blogging.Platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_6_practica.Blogging.Platform.entities.Tag;

import java.util.Optional;

public interface TagRepositorie extends JpaRepository<Tag,Integer> {

    Optional<Tag> findByName(String name);
}
