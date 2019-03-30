package midianet.sca;

import midianet.sca.model.Role;
import midianet.sca.model.User;
import midianet.sca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@SpringBootApplication
public class Application {

    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void loader() {
        var user = new User();
        user.setName("Marcos Fernando");
        user.setUsername("midianet");
        user.setPassword("123");
        user.setRoles(new HashSet<>());
        var role1 = new Role();
        role1.setName("ADMIN");
        role1.setDescription("Role Adm");
        user.getRoles().add(role1);
        var role2 = new Role();
        role2.setName("USER");
        role2.setDescription("Role User");
        user.getRoles().add(role2);
        //service.save(user);
    }

}