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
    public ResponseEntity<CD> getCdById(@PathVariable String id) {
        Optional<CD> cd = cdService.findById(id);
        return cd.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CD> createCd(@RequestBody CD cd) {
        // For POST, we assume a new CD, so we let the service handle ID generation
        CD newCd = new CD(cd.getTitle(), cd.getArtist(), cd.getReleaseYear());
        CD savedCd = cdService.save(newCd);
        return new ResponseEntity<>(savedCd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CD> updateCd(@PathVariable String id, @RequestBody CD cd) {
        Optional<CD> existingCd = cdService.findById(id);

        if (existingCd.isPresent()) {
            // Update the existing CD with new data
            cd.setId(id); // Ensure the ID from the path is used for the update
            CD updatedCd = cdService.save(cd);
            return ResponseEntity.ok(updatedCd);
        } else {
            // If not found, create a new one (Spring Boot REST convention)
            CD newCd = new CD(cd.getTitle(), cd.getArtist(), cd.getReleaseYear());
            CD savedCd = cdService.save(newCd);
            return new ResponseEntity<>(savedCd, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCd(@PathVariable String id) {
        cdService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
