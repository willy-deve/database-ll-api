package projetofinal.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projetofinal.projetofinal.entities.User;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    private Long user_id;
    private String name;
    private String email;
    private String password;

    public UserDTO(User entity){
        this.user_id = entity.getUser_id();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }


}
