package com.thehecklers.sburrestdemo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Transactional
public class CDService {
    private final CDRepository cdRepository;

    public CDService(CDRepository cdRepository) {
        this.cdRepository = cdRepository;
    }

    @PostConstruct
    private void init() {
        cdRepository.save(new CD("Thriller", "Michael Jackson", 1982));
        cdRepository.save(new CD("The Dark Side of the Moon", "Pink Floyd", 1973));
        cdRepository.save(new CD("Abbey Road", "The Beatles", 1969));
        cdRepository.save(new CD("Nevermind", "Nirvana", 1991));
    }

    public Iterable<CD> findAll() {
        return cdRepository.findAll();
    }

    public Optional<CD> findById(Long id) {
        return cdRepository.findById(id);
    }

    public CD save(CD cd) {
        return cdRepository.save(cd);
    }

    public void deleteById(Long id) {
        cdRepository.deleteById(id);
    }
}
