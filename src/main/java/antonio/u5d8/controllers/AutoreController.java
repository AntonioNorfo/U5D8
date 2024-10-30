package antonio.u5d8.controllers;

import antonio.u5d8.entities.Autore;
import antonio.u5d8.payloads.AutorePayload;
import antonio.u5d8.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Autore> getAllAutori() {
        return autoreService.getAllAutori();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Optional<Autore> getAutoreById(@PathVariable UUID id) {
        return autoreService.getAutoreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 Created
    public Autore createAutore(@RequestBody AutorePayload autore) {
        return autoreService.addAutore(autore);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public Autore updateAutore(@PathVariable UUID id, @RequestBody Autore updatedAutore) {
        return autoreService.updateAutore(id, updatedAutore);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void deleteAutore(@PathVariable UUID id) {
        autoreService.deleteAutore(id);
    }
}

