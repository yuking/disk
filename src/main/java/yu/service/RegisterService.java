package yu.service;

public interface RegisterService {

    boolean checkUsername(String username);

    void addUser(String username, String password);

}
