package midianet.sca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 80, nullable = false)
    private String name;

    @NotNull
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @NotNull
    @JsonIgnore
    @Column(length = 20 , nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;

}