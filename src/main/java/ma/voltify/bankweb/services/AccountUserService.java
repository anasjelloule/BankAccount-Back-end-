package ma.voltify.bankweb.services;

import java.util.List;

import exceptions.RoleNotFoundException;
import exceptions.UserNotFoundException;
import ma.voltify.bankweb.entities.AppRole;
import ma.voltify.bankweb.entities.AppUser;

public interface AccountUserService {
    AppUser addNewUser(AppUser user);

    AppRole addRole(AppRole role);

    AppRole getRole(String role) throws RoleNotFoundException;

    // List<AppRole> getRoles(String role) throws RoleNotFoundException;

    void addRoleToUser(String userName, String roleName)
            throws UserNotFoundException, RoleNotFoundException;

    AppUser loadUserByUserName(String userName) throws UserNotFoundException;

    List<AppUser> listusers();
}
