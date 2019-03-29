package midianet.sca.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 15, nullable = false, unique = true)
    private String name;

    @Column(length = 100)
    private String description;

}