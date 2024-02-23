package it.epicode.w7d5progetto.service;

import it.epicode.w7d5progetto.exception.NotFoundException;
import it.epicode.w7d5progetto.model.Evento;
import it.epicode.w7d5progetto.model.EventoRequest;
import it.epicode.w7d5progetto.model.Utente;
import it.epicode.w7d5progetto.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Page<Evento> getAllEventi(Pageable pageable){
        return  eventoRepository.findAll(pageable);
    }

    public Evento cercaEventoPerId(int id) throws NotFoundException{
        return eventoRepository.findById(id).
                orElseThrow(()->new NotFoundException("Evento con id = "+ id + " non trovato"));
    }

    public Evento salvaEvento(EventoRequest eventoRequest) throws NotFoundException{

        Evento evento = new Evento(eventoRequest.getTitolo(), eventoRequest.getDescrizione(), eventoRequest.getData(), eventoRequest.getLuogo(), eventoRequest.getNumeroPostiDisponibili());
        return eventoRepository.save(evento);
    }

    public Evento aggiornaEvento(int id, EventoRequest eventoRequest) throws NotFoundException{
        Evento evento = cercaEventoPerId(id);

        Utente utente = utenteService.getUtenteById(eventoRequest.getUtente_id());

        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setData(eventoRequest.getData());
        evento.setNumeroPostiDisponibili(eventoRequest.getNumeroPostiDisponibili());

        return eventoRepository.save(evento);
    }

    public void cancellaEvento(int id) throws NotFoundException{
        Evento post = cercaEventoPerId(id);
        eventoRepository.delete(post);
    }

    public Evento aggiornaPartecipanti(EventoRequest eventoRequest, int id) throws NotFoundException{
        Evento evento = cercaEventoPerId(id);

        Utente utente = utenteService.getUtenteById(eventoRequest.getUtente_id());
        evento.addUtente(utente);
        return eventoRepository.save(evento);

    }
}
