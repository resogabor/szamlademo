package hu.rate.java.tanf.szamla.controller;

import hu.rate.java.tanf.szamla.entity.SzamlaFej;
import hu.rate.java.tanf.szamla.entity.SzamlaTetel;
import hu.rate.java.tanf.szamla.repository.SzamlaRepository;
import hu.rate.java.tanf.szamla.repository.SzamlaTetelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by resog on 2017. 02. 20..
 */
@RestController
@RequestMapping("/szamla")
public class SzamlaAdatok {

    @Autowired
    SzamlaRepository szamlaRepository;
    @Autowired
    SzamlaTetelRepository szamlaTetelRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SzamlaFej> getAllSzamla(){
        return szamlaRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SzamlaFej getSzamla(@PathVariable("id") Integer id){
        return szamlaRepository.getOne(id);
    }

    @RequestMapping(value = "/tetel/all", method = RequestMethod.GET)
    public List<SzamlaTetel> getSzamlaTetel(){
        return szamlaTetelRepository.findAll();
    }

    @RequestMapping(value = "/szamla_tetel/{szamlaId}")
    public List<SzamlaTetel> getSzamlaTetelbySzamlaFej(@PathVariable("szamlaId") Integer szamlaId){
        return szamlaTetelRepository.getBySzamlaTetelId(szamlaId);
    }

    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    public Integer szamlaMentes(@RequestBody SzamlaFej szamla){
        SzamlaFej sz = szamlaRepository.saveAndFlush(szamla);
        return sz.getId();
    }

    @RequestMapping(value = "/delete/{szamlaId}", method = RequestMethod.DELETE)
    @Transactional
    public void szamlaTorles(@PathVariable("szamlaId") Integer szamlaId){
        szamlaTetelRepository.deleteBySzamlaTetelId(szamlaId);
        szamlaRepository.delete(szamlaId);
    }

    @RequestMapping(value = "/tetel/save", method = RequestMethod.POST)
    public void tetelMentes(@RequestBody SzamlaTetel tetel){
        szamlaTetelRepository.saveAndFlush(tetel);
    }

    @RequestMapping("tetel/delete/{tetelId}")
    @Transactional
    public void tetelTorles(@PathVariable("tetelId") Integer tetelId){
        szamlaTetelRepository.delete(tetelId);
    }
}
