package com.sr.chatpanel.rest.site;

import com.sr.chatpanel.models.Site;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.rest.exceptions.EntityNotFound;
import com.sr.chatpanel.services.AuthenticationService;
import com.sr.chatpanel.services.SiteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sites")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class SiteController {
    private final SiteService siteService;
    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public ResponseEntity<Object> getMany(@AuthenticationPrincipal User user) {
        System.out.println(user.getEmail());
        return new ResponseEntity<>(siteService.getMany(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Site getSite(@PathVariable int id) throws EntityNotFound {
        return siteService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Site> createSite(@Valid @RequestBody Site site) {
        site.setUser(authenticationService.getCurrentUser());
        return new ResponseEntity<>(siteService.add(site), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable int id, @Valid @RequestBody Site site) throws EntityNotFound {
        site.setUser(authenticationService.getCurrentUser());
        return new ResponseEntity<>(siteService.update(id, site), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws EntityNotFound {
        siteService.delete(id);
    }
}
