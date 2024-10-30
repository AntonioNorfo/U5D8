package antonio.u5d8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostPayload {
    private String titolo;
    private String contenuto;
    private String categoria;
    private LocalDate tempoLettura;


    private UUID autoreId;
}
