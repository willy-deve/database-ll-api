package projetofinal.projetofinal.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String name;
    private String email;
    private String password;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created_on;


    //Essa função vai ser executada assim que eu inserir uma informação
    //devido a a anotação prepersist;
    @PrePersist
    public void prepersist(){
        created_on = Instant.now();
    }

    @OneToMany(mappedBy = "user")
    private List<Message> messages;





}
