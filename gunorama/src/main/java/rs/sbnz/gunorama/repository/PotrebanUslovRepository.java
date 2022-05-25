package rs.sbnz.gunorama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.sbnz.gunorama.model.PotrebanUslov;

@Repository
public interface PotrebanUslovRepository extends JpaRepository<PotrebanUslov, Integer> {
}
