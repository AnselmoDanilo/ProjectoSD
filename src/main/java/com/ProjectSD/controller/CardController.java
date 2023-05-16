package com.ProjectSD.controller;

import com.ProjectSD.model.Card;
import com.ProjectSD.repository.CardRepository;
import com.ProjectSD.service.CardService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;

    @Autowired
    private CardRepository repository;

    @GetMapping
    public List<Document> findAll() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Document findById(@PathVariable String id) {
        Document card = repository.findById(id);
        return card;
    }
    @GetMapping("/id_card/{id_card}")
    public Document findByIdCard(@PathVariable("id_card") String id_card){
        Document card = repository.findByIdCard(id_card);
     
        return card;
    }
    
    @GetMapping("/engname/{name}")
    public Document findByEngName(@PathVariable("name") String engName) {
        Document cards = repository.findByEngName(engName);
        return cards;
    }

    @GetMapping("/portname/{name}")
    public List<Document> findByPortName(@PathVariable("name") String portName) {
        List<Document> cards = repository.findByPortName(portName);
        return cards;
    }

    @PostMapping(path = "/postcard")
    public ResponseEntity<String> create(@RequestBody Card card) {
        Document doc = new Document();
  
        
   
        doc.append("id_card", repository.getLastCardId())
                .append("id_user", 1)
                .append("eng_name", card.getEngName())
                .append("eng_desc", card.getEngDesc())
                .append("port_name", card.getPortName())
                .append("port_desc", card.getPortDesc())
                .append("nota", card.getNota());
        repository.save(doc);
        return ResponseEntity.status(HttpStatus.CREATED).body("Card created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Card card) {
        Document doc = new Document();
        doc.append("id_card", card.getId())
                .append("id_user", card.getUserId())
                .append("eng_name", card.getEngName())
                .append("eng_desc", card.getEngDesc())
                .append("port_name", card.getPortName())
                .append("port_desc", card.getPortDesc())
                .append("nota", card.getNota());
        repository.update(id, doc);
        return ResponseEntity.ok("Card updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        repository.delete(id);
        return ResponseEntity.ok("Card deleted successfully!");
    }
}


/*
dificuldade para lembrar

1- Esqueci Totalmente - Muito Fraco
2- Lembrei pouco      - Fraco
3- Quase acertei      - Medio
4- Acertei quase tudo - Forte
5- Acertei Totalmente - Muito Forte

*/

/*
GET /cards: retorna uma lista de todos os cards.
GET /cards/{id}: retorna um card pelo seu id.
GET /cards/id_card/{id_card}: retorna um card pelo seu id_card.
GET /cards/engname/{name}: retorna um card pelo seu nome em inglês.
GET /cards/portname/{name}: retorna uma lista de cards pelo seu nome em português.
POST /cards/postcard: cria um novo card.
PUT /cards/{id}: atualiza um card existente pelo seu id.
DELETE /cards/{id}: deleta um card pelo seu id.
*/