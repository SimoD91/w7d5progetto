package it.epicode.w7d5progetto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titolo;
    private String descrizione;
    private Date data;
    private String luogo;
    @Column(name = "numero_posti_disponibili")
    private int numeroPostiDisponibili;

    @ManyToMany
    @JoinTable(name = "utenti_lista")
    private List<Utente> utenti;

    public Evento(String titolo, String descrizione, Date data, String luogo, int numeroPostiDisponibili) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numeroPostiDisponibili = numeroPostiDisponibili;
        this.utenti = new ArrayList<>();
    }
    public Evento(){
    }
    public void addUtente(Utente utente){
        utenti.add(utente);
    }
}
