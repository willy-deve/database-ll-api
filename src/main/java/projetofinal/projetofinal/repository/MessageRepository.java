package projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetofinal.projetofinal.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
