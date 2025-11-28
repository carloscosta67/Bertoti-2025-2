package com.thehecklers.sburrestdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cds")
public class CDController {
    private final CDService cdService;

    public CDController(CDService cdService) {
        this.cdService = cdService;
    }

    @GetMapping
    public Iterable<CD> getAllCds() {
        return cdService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CD> getCdById(@PathVariable Long id) {
        Optional<CD> cd = cdService.findById(id);
        return cd.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CD> createCd(@RequestBody CD cd) {
        CD savedCd = cdService.save(cd);
        return new ResponseEntity<>(savedCd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CD> updateCd(@PathVariable Long id, @RequestBody CD cd) {
        Optional<CD> existingCd = cdService.findById(id);

        if (existingCd.isPresent()) {
            cd.setId(id); // Garante que o ID do path seja usado para a atualização
            CD updatedCd = cdService.save(cd);
            return ResponseEntity.ok(updatedCd);
        } else {
            // Se não encontrado, cria um novo (convenção REST)
            CD savedCd = cdService.save(cd);
            return new ResponseEntity<>(savedCd, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCd(@PathVariable Long id) {
        cdService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
