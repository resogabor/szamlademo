package hu.rate.java.tanf.szamla.repository;

import hu.rate.java.tanf.szamla.entity.SzamlaTetel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by resog on 2017. 02. 20..
 */
public interface SzamlaTetelRepository extends JpaRepository<SzamlaTetel, Integer> {
    public List<SzamlaTetel> getBySzamlaTetelId(Integer szamlaTetelId);
    public void deleteBySzamlaTetelId(Integer szamlaTetelId);
}
