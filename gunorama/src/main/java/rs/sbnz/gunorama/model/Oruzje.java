package rs.sbnz.gunorama.model;

import rs.sbnz.gunorama.model.enums.MehanizamHranjenja;
import rs.sbnz.gunorama.model.enums.MehanizamOkidanja;

import java.util.HashSet;
import java.util.Set;

public class Oruzje extends BaseEntity {

    private String naziv;

    private Set<Kalibar> dozvoljeniKalibri = new HashSet<>();

    private MehanizamHranjenja mehanizamHranjenja;

    private MehanizamOkidanja mehanizamOkidanja;
}
