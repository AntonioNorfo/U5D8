package antonio.u5d8.repositories;

import antonio.u5d8.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, UUID> {
}
