package antonio.u5d8.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;

    private String titolo;
    private String contenuto;
    private String categoria;
    private LocalDate tempoLettura;
    private String cover;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autore_id", nullable = false)
    private Autore autore;
    private UUID blog_id;

    public BlogPost() {
    }

    public BlogPost(String titolo, String contenuto, String categoria, LocalDate tempoLettura, Autore autore) {
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.categoria = categoria;
        this.tempoLettura = tempoLettura;
        this.autore = autore;
    }

    public UUID getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(UUID blog_id) {
        this.blog_id = blog_id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getTempoLettura() {
        return tempoLettura;
    }

    public void setTempoLettura(LocalDate tempoLettura) {
        this.tempoLettura = tempoLettura;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }
}
