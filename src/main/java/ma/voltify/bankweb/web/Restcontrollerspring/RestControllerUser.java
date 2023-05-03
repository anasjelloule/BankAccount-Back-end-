package ma.voltify.bankweb.web.Restcontrollerspring;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.AppUser;
import ma.voltify.bankweb.services.AccountUserService;

@RestController
@AllArgsConstructor
@RequestMapping({ "/users", "/users/" })
public class RestControllerUser {
    private AccountUserService userimpl;

    @GetMapping
    // @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<AppUser>> getuserlist(HttpServletResponse response) {
        return ResponseEntity.status(200).body(userimpl.listusers());
    }

    @GetMapping({ "/{username}", "/{username}/" })
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> getuserdetail(HttpServletResponse response,
            @PathVariable String username)
            throws UserNotFoundException {
        return ResponseEntity.status(200).body(userimpl.loadUserByUserName(username));
    }
}
