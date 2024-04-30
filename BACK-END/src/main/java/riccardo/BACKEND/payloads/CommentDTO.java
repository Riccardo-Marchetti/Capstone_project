package riccardo.BACKEND.payloads;


public record CommentDTO(String description,

                         int rating,

                         long idFilm,

                         long idUser
                         ) {
}
