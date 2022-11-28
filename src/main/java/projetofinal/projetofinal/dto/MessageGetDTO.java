package projetofinal.projetofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projetofinal.projetofinal.entities.Message;
import projetofinal.projetofinal.entities.User;

@AllArgsConstructor
@NoArgsConstructor
public class MessageGetDTO extends MessageDTO{

    private User user;

    public MessageGetDTO(Message entity, User user) {
        super(entity, new UserDTO(user));

    }
}
