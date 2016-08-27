package yu.service;


import org.springframework.data.repository.Repository;
import yu.domain.Users;

public interface UsersRepository extends Repository<Users, Long> {

    Users findByUsername(String username);

    Users save(Users user);
}
