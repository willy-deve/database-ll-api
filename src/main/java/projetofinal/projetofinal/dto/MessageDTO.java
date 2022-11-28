package projetofinal.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projetofinal.projetofinal.entities.Message;
import projetofinal.projetofinal.entities.User;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private Long message_id;

    private String description;
    private String detail;

    private UserDTO userDTO;

    public MessageDTO(Message entity){
        this.message_id = entity.getMessage_id();
        this.description = entity.getDescription();
        this.detail = entity.getDetail();
    }

    public MessageDTO(Message entity, UserDTO userDTO){
        this(entity);
        this.userDTO = userDTO;
    }
}
