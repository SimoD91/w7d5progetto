package it.epicode.w7d5progetto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EventoRequest {
    @NotBlank(message = "Id utente obbligatorio")
    private int utente_id;
    @NotBlank(message = "Titolo obbligatorio")
    private String titolo;
    @NotBlank(message = "Descrizione obbligatoria")
    private String descrizione;
    @NotBlank(message = "Luogo obbligatorio")
    private String luogo;
    @NotBlank(message = "Data obbligatoria")
    private Date data;
    @NotBlank(message = "Posti disponibili obbligatori")
    private int numeroPostiDisponibili;
}
