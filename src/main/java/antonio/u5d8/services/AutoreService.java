package antonio.u5d8.services;

import antonio.u5d8.payloads.AutorePayload;
import antonio.u5d8.entities.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AutoreService {
    private List<Autore> autori = new ArrayList<>();

    public List<Autore> getAllAutori() {
        return autori;
    }

    public Optional<Autore> getAutoreById(UUID id) {
        return autori.stream().filter(a -> a.getAutore_id().equals(id)).findFirst();
    }

    public Autore addAutore(AutorePayload autore) {
        Autore newAutore = new Autore(autore.getNome(), autore.getCognome(), autore.getEmail(), autore.getDataNascita());
        newAutore.setAutore_id(UUID.randomUUID());
        newAutore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());
        autori.add(newAutore);
        return newAutore;
    }

    public Autore updateAutore(UUID id, Autore updatedAutore) {
        Autore autore = getAutoreById(id).orElseThrow(() -> new RuntimeException("Autore non trovato"));
        autore.setNome(updatedAutore.getNome());
        autore.setCognome(updatedAutore.getCognome());
        autore.setEmail(updatedAutore.getEmail());
        autore.setDataNascita(updatedAutore.getDataNascita());
        return autore;
    }

    public void deleteAutore(UUID id) {
        autori.removeIf(a -> a.getAutore_id().equals(id));
    }
}
