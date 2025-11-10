package sys.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sys.employee.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
   Optional<User> findByUsernameAndPassword(String username, String password);

   Optional<User> findByUsername(String username);
}
