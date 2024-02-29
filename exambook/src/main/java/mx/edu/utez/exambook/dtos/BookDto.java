package mx.edu.utez.exambook.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.exambook.models.Book;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {

    private Long id;
    private String name;
    private String author;
    private LocalDate publicationDate;
    private String coverImage;
    @JsonIgnore
    private Boolean status;

    public Book getBooks(){
        return new Book(
                getId(),
                getName(),
                getAuthor(),
                getPublicationDate(),
                getCoverImage(),
                getStatus()
        );
    }

}
