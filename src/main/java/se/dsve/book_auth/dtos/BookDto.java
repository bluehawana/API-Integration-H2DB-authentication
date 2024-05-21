package se.dsve.book_auth.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    @Schema(description = "Bokens titel", example = "The Great Gatsby")
    private String title;

    @Schema(description = "Bokens författare", example = "F. Scott Fitzgerald")
    private String author;

    @Schema(description = "Bokens ISBN-nummer", example = "9780743273565")
    private String isbn;
}
