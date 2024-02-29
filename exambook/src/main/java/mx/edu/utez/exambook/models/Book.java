package mx.edu.utez.exambook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="adoption")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    /*Nombre de libro, autor, fecha de publicación, imagen de portada (opcional)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "cover_image", nullable = true)
    private String coverImage;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status;
}
