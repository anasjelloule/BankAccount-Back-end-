package ma.voltify.bankweb.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exceptions.RoleNotFoundException;
import exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import ma.voltify.bankweb.entities.AppRole;
import ma.voltify.bankweb.entities.AppUser;
import ma.voltify.bankweb.repositories.AppRoleRepository;
import ma.voltify.bankweb.repositories.AppUserRepository;

@Service
@Transactional
@Data
@AllArgsConstructor
public class AccountUserServiceImpl implements AccountUserService {
    private AppUserRepository appuser;
    private AppRoleRepository approle;

    @Override
    public AppUser addNewUser(AppUser user) {
        return appuser.save(user);
    }

    @Override
    public AppRole addRole(AppRole role) {
        return approle.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName)
            throws UserNotFoundException, RoleNotFoundException {
        AppUser Userapp = appuser.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        AppRole roleapp = approle.findByRolename(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role Not found"));
        // Userapp.setAppRoles();
        Userapp.getApproles().add(roleapp);
        // appuser.findById(1L).orElseThrow(() -> new
        // UserNotFoundException("roleName"));
    }

    @Override
    public List<AppUser> listusers() {

        return appuser.findAll();
    }

    @Override
    public AppUser loadUserByUserName(String userName) throws UserNotFoundException {
        AppUser Userapp = appuser.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        return Userapp;
    }

    @Override
    public AppRole getRole(String role) throws RoleNotFoundException {
        return approle.findByRolename(role).orElseThrow(() -> new RoleNotFoundException("Role Not found"));
        // return null;
    }

}
