package com.thehecklers.sburrestdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CDService {
    private List<CD> cds = new ArrayList<>();

    public CDService() {
        cds.addAll(List.of(
                new CD("Thriller", "Michael Jackson", 1982),
                new CD("The Dark Side of the Moon", "Pink Floyd", 1973),
                new CD("Abbey Road", "The Beatles", 1969),
                new CD("Nevermind", "Nirvana", 1991)
        ));
    }

    public Iterable<CD> findAll() {
        return cds;
    }

    public Optional<CD> findById(String id) {
        for (CD cd : cds) {
            if (cd.getId().equals(id)) {
                return Optional.of(cd);
            }
        }
        return Optional.empty();
    }

    public CD save(CD cd) {
        // Check if CD already exists (for PUT/update)
        int cdIndex = -1;
        for (CD existingCd : cds) {
            if (existingCd.getId().equals(cd.getId())) {
                cdIndex = cds.indexOf(existingCd);
                break;
            }
        }

        if (cdIndex != -1) {
            // Update existing CD
            cds.set(cdIndex, cd);
        } else {
            // Add new CD (for POST/create)
            cds.add(cd);
        }
        return cd;
    }

    public void deleteById(String id) {
        cds.removeIf(cd -> cd.getId().equals(id));
    }
}
