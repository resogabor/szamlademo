package hu.rate.java.tanf.szamla.repository;

import hu.rate.java.tanf.szamla.entity.SzamlaFej;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by resog on 2017. 02. 20..
 */
public interface SzamlaRepository extends JpaRepository<SzamlaFej, Integer> {
}
