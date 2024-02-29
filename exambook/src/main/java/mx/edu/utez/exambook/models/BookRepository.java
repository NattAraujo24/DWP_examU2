package mx.edu.utez.exambook.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    @Modifying
    @Query(
            value = "UPDATE book SET status = :status WHERE id = :id",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    Optional<Book> findById(Long aLong);
    List<Book> findAllByStatus(Boolean status);
    Book getById(Long id);

    //busqueda
    List<Book> findAllByCoverImageIsNotNull();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByOrderByPublicationDateAsc();




}
