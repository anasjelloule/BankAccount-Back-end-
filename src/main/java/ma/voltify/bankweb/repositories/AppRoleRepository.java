package ma.voltify.bankweb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRolename(String rolename);
}
