package rs.sbnz.gunorama.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.sbnz.gunorama.model.Kalibar;
import rs.sbnz.gunorama.repository.KalibarRepository;

import java.util.List;

@Service
public class KalibarService {

    private final KalibarRepository kalibarRepository;


    @Autowired
    public KalibarService(KalibarRepository kalibarRepository) {
        this.kalibarRepository = kalibarRepository;
    }

    public List<Kalibar> dobaviSve() {
        return this.kalibarRepository.findAll();
    }
}
