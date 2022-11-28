package projetofinal.projetofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetofinal.projetofinal.dto.UserDTO;
import projetofinal.projetofinal.entities.User;
import projetofinal.projetofinal.exceptions.BadrequestException;
import projetofinal.projetofinal.exceptions.DatabaseException;
import projetofinal.projetofinal.exceptions.EntityNotFoundException;
import projetofinal.projetofinal.exceptions.InternalServerErrorException;
import projetofinal.projetofinal.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        try {
            List<User> userList = repository.findAll();
            return userList.stream().map(c -> new UserDTO(c)).collect(Collectors.toList());
        }catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");

        }
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
       try{
           User entity = repository.findById(id).get();
           return new UserDTO(entity);
       }catch (EntityNotFoundException e){
           throw new EntityNotFoundException("User não encontrado");
       }catch (BadrequestException e){
           throw new BadrequestException("Requisição invalida");
       }
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("User não encontrado");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação no banco de dados");
        }
    }

    public UserDTO save(UserDTO dto){
       try{
           User entity = new User();
           entity.setName(dto.getName());
           entity.setEmail(dto.getEmail());
           entity.setPassword(dto.getPassword());
           entity = repository.save(entity);
           return new UserDTO(entity);
       }catch (BadrequestException e){
           throw new BadrequestException("Requisição invalida");
       }
    }

    public UserDTO update(UserDTO dto, Long id){
        try {
            User entity = repository.findById(id).get();
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
            entity = repository.save(entity);
            return new UserDTO(entity);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Usuario não encontrado");
        }catch (BadrequestException e){
            throw new BadrequestException("Requisição invalida");
        }
    }
}
