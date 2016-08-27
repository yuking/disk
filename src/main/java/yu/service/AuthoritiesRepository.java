package yu.service;

import org.springframework.data.repository.Repository;
import yu.domain.Authorities;

public interface AuthoritiesRepository extends Repository<Authorities, Long> {

    Authorities save(Authorities authorities);
}
