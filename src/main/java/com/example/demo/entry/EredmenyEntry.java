package com.example.demo.entry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EredmenyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eredmenyId;
    @ManyToOne
    private VersenyEntry verseny;
    @ManyToOne
    private VersenyzoEntry versenyzo;
    private int ido;

    public Long getErednemyId(){
        return eredmenyId;
    }
    public void setEredmenyID(Long id){
        this.eredmenyId = id;
    }

    public VersenyEntry getVerseny(){
        return verseny;
    }
    public void setVerseny(VersenyEntry verseny){
        this.verseny = verseny;
    }

    public VersenyzoEntry getVersenyzo(){
        return versenyzo;
    }
    public void setVersenyzo(VersenyzoEntry versenyzo){
        this.versenyzo = versenyzo;
    }

    public int getIdo(){
        return ido;
    }
    public void setIdo(int ido){
        this.ido = ido;
    }
}
