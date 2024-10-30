package antonio.u5d8.services;

import antonio.u5d8.entities.Autore;
import antonio.u5d8.payloads.AutorePayload;
import antonio.u5d8.repositories.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public List<Autore> getAllAutori() {
        return autoreRepository.findAll();
    }

    public Optional<Autore> getAutoreById(UUID id) {
        return autoreRepository.findById(id);
    }

    @Transactional
    public Autore addAutore(AutorePayload autore) {
        Autore newAutore = new Autore(autore.getNome(), autore.getCognome(), autore.getEmail(), autore.getDataNascita());
        newAutore.setAutore_id(UUID.randomUUID());
        newAutore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());
        return autoreRepository.save(newAutore);
    }

    @Transactional
    public Autore updateAutore(UUID id, Autore updatedAutore) {
        Autore autore = autoreRepository.findById(id).orElseThrow(() -> new RuntimeException("Autore non trovato"));
        autore.setNome(updatedAutore.getNome());
        autore.setCognome(updatedAutore.getCognome());
        autore.setEmail(updatedAutore.getEmail());
        autore.setDataNascita(updatedAutore.getDataNascita());
        return autoreRepository.save(autore);
    }

    @Transactional
    public void deleteAutore(UUID id) {
        if (!autoreRepository.existsById(id)) {
            throw new RuntimeException("Autore non trovato");
        }
        autoreRepository.deleteById(id);
    }
}
