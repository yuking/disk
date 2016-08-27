package yu.service;

import org.springframework.stereotype.Service;
import yu.domain.Authorities;
import yu.domain.Users;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UsersRepository usersRepository;

    @Resource
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public boolean checkUsername(String username) {
        return usersRepository.findByUsername(username) == null;
    }

    @Override
    public void addUser(String username, String password) {
        Users user = new Users(username, password);
        Authorities authorities = new Authorities(username);
        usersRepository.save(user);
        authoritiesRepository.save(authorities);
    }
}
