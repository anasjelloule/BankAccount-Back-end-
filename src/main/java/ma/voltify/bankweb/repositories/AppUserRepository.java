package ma.voltify.bankweb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

}
