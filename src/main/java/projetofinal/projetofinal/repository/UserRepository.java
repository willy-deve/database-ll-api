package projetofinal.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetofinal.projetofinal.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
