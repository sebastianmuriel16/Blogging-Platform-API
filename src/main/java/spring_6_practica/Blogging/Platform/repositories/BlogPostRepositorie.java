package spring_6_practica.Blogging.Platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring_6_practica.Blogging.Platform.entities.BlogPost;

import java.util.List;

public interface BlogPostRepositorie extends JpaRepository<BlogPost,Integer> {


    @Query(
            """
                    SELECT b 
                    FROM BlogPost b
                    WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%'))
                          OR LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%'))
                          OR lOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))
                    """)
    List<BlogPost> searchByTerm(@Param("term") String term);

}
