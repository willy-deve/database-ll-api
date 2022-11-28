package projetofinal.projetofinal.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projetofinal.projetofinal.dto.MessageDTO;
import projetofinal.projetofinal.dto.UserDTO;
import projetofinal.projetofinal.entities.User;
import projetofinal.projetofinal.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Apagado com sucesso");
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO user){
        UserDTO dto = service.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getUser_id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable("id") Long id){
        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }
}
