package it.epicode.w7d5progetto.service;

import it.epicode.w7d5progetto.exception.NotFoundException;
import it.epicode.w7d5progetto.model.Evento;
import it.epicode.w7d5progetto.model.BlogPostRequest;
import it.epicode.w7d5progetto.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<Evento> cercaTuttiIBlogPosts(Pageable pageable){
        return  blogPostRepository.findAll(pageable);
    }

    public Evento cercaPostPerId(int id) throws NotFoundException{
        return blogPostRepository.findById(id).
                orElseThrow(()->new NotFoundException("BlogPost con id="+id+" non trovato"));
    }

    public Evento salvaBlogPost(BlogPostRequest blogPostRequest) throws NotFoundException{
        Autore autore = autoreService.cercaAutorePerId(blogPostRequest.getIdAutore());

        Evento evento = new Evento(blogPostRequest.getContenuto(), blogPostRequest.getTitolo(),
                blogPostRequest.getCategoria(), blogPostRequest.getTempoLettura(), autore);

        return blogPostRepository.save(evento);

    }

    public Evento aggiornaBlogPost(int id, BlogPostRequest blogPostRequest) throws NotFoundException{
        Evento post = cercaPostPerId(id);

        Autore autore = autoreService.cercaAutorePerId(blogPostRequest.getIdAutore());

        post.setCategoria(blogPostRequest.getCategoria());
        post.setContenuto(blogPostRequest.getContenuto());
        post.setTitolo(blogPostRequest.getTitolo());
        post.setTempoLettura(blogPostRequest.getTempoLettura());
        post.setAutore(autore);

        return blogPostRepository.save(post);
    }

    public void cancellaBlogPost(int id) throws NotFoundException{
        Evento post = cercaPostPerId(id);
        blogPostRepository.delete(post);
    }

    public Evento uploadCover(int id, String url){
        Evento evento = cercaPostPerId(id);
        evento.setCover(url);
        return blogPostRepository.save(evento);
    }
}
