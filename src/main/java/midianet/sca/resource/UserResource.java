package midianet.sca.resource;

import midianet.sca.model.User;
import midianet.sca.repository.UserRepository;
import midianet.sca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> list(){
        return repository.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public User findbyId(@PathVariable(value = "id") Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User %d", id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody User user, HttpServletResponse response){
        user.setId(null);
        user = service.save(user);
        response.addHeader(HttpHeaders.LOCATION,String.format("/api/colaboradores/%d", user.getId()));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void put(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        service.save(user);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}