package projetofinal.projetofinal.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projetofinal.projetofinal.dto.MessageDTO;
import projetofinal.projetofinal.dto.MessageGetDTO;
import projetofinal.projetofinal.entities.Message;
import projetofinal.projetofinal.service.MessageService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping("/all")
    public ResponseEntity<List<MessageGetDTO>> findAll(){
        List<MessageGetDTO> dtoList = service.findAll();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable("id") Long id){
        MessageDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().body("Apagado com sucesso");
    }

    @PostMapping
    public ResponseEntity<MessageDTO> insert(@RequestBody MessageDTO message){
        MessageDTO dto = service.save(message);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getMessage_id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> insert(@RequestBody MessageDTO dto, @PathVariable Long id){
        MessageDTO dtoR = service.update(dto, id);
        return ResponseEntity.ok().body(dtoR);
    }



}
