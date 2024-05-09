package com.example.demo.entry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VersenyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long versenyId;
    private String nev;
    private int tavolsag;

    public Long getVersenyId(){
        return versenyId;
    }
    public void setVersenyID(Long id){
        this.versenyId = id;
    }

    public String getNev(){
        return nev;
    }
    public void setNev(String newNev){
        this.nev = newNev;
    }

    public int getTavolsag(){
        return tavolsag;
    }
    public void setTavolsag(int newTavolsag){
        this.tavolsag = newTavolsag;
    }
}
