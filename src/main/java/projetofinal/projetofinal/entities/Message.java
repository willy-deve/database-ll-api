package projetofinal.projetofinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import projetofinal.projetofinal.dto.UserDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="tb_message")
public class Message  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;
    private String description;
    private String detail;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created_on;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updated_on;


    @PrePersist
    public void prePersist(){created_on = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){updated_on = Instant.now();
    }


    @ManyToOne
    @JoinColumn(name = "id_user_fk")
    private User user;


}
