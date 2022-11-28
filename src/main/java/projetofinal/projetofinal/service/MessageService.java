package projetofinal.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetofinal.projetofinal.dto.MessageDTO;
import projetofinal.projetofinal.dto.MessageGetDTO;
import projetofinal.projetofinal.dto.UserDTO;
import projetofinal.projetofinal.entities.Message;
import projetofinal.projetofinal.entities.User;
import projetofinal.projetofinal.exceptions.BadrequestException;
import projetofinal.projetofinal.exceptions.DatabaseException;
import projetofinal.projetofinal.exceptions.EntityNotFoundException;
import projetofinal.projetofinal.exceptions.InternalServerErrorException;
import projetofinal.projetofinal.repository.MessageRepository;
import projetofinal.projetofinal.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<MessageGetDTO> findAll(){
        try {
            List<Message> messageList = repository.findAll();
            return messageList.stream().map(c -> new MessageGetDTO(c, c.getUser())).collect(Collectors.toList());
        }catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");
        }
    }

    @Transactional(readOnly = true)
    public MessageGetDTO findById(Long id){
        try{
            Message entity = repository.findById(id).get();
            return new MessageGetDTO(entity, entity.getUser());
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Recado não encontrado");
        }catch (BadrequestException e){
            throw new BadrequestException("Requisição invalida");
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("ID não encontrado");
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação do banco de dados");
     }
    }

    public MessageDTO save(MessageDTO dto){
        try{
            Message entity = new Message();
            entity.setDescription(dto.getDescription());
            entity.setDetail(dto.getDetail());

            User user = userRepository.findById(dto.getUserDTO().getUser_id()).get();
            entity.setUser(user);
            entity = repository.save(entity);
            UserDTO userDTO = new UserDTO(user);
            return new MessageDTO(entity, userDTO);

        }catch (BadrequestException e){
            throw new BadrequestException("Requisição invalida");
        }
    }



    public MessageDTO update(MessageDTO dto, Long id){
        try {
            Message entity = repository.findById(id).get();
            entity.setDescription(dto.getDescription());
            entity.setDetail(dto.getDetail());

            User user = userRepository.findById(dto.getUserDTO().getUser_id()).get();
            entity = repository.save(entity);
            return new MessageDTO(entity, new UserDTO(user));

        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Recado não encontrado");
        }catch (BadrequestException e){
            throw new BadrequestException("Requisição invalida");
        }
    }


}
