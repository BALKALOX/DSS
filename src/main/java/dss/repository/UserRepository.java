package dss.repository;

import dss.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);
    User findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
