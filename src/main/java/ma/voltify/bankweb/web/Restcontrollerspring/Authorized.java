package ma.voltify.bankweb.web.Restcontrollerspring;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authorized")
public class Authorized {
    @GetMapping({ "/", "" })
    public ResponseEntity getaccounts() {
        return ResponseEntity.ok("Not autorized");
    }
}
