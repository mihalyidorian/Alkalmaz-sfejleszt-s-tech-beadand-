package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entry.EredmenyEntry;
import com.example.demo.entry.VersenyEntry;
import com.example.demo.entry.VersenyzoEntry;
import com.example.demo.repository.EredmenyRepository;
import com.example.demo.repository.VersenyRepository;
import com.example.demo.repository.VersenyzoRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EredmenyRepository eredmenyRepository;
    @Autowired
    private VersenyRepository versenyRepository;
    @Autowired
    private VersenyzoRepository versenyzoRepository;

    @Override
    public void run(String... args) throws Exception {
        VersenyEntry v1 = new VersenyEntry();
        v1.setNev("1. verseny");
        v1.setTavolsag(10);
        versenyRepository.save(v1);

        VersenyEntry v2 = new VersenyEntry();
        v2.setNev("2. verseny");
        v2.setTavolsag(2);
        versenyRepository.save(v2);

        VersenyzoEntry vz1 = new VersenyzoEntry();
        vz1.setNev("Dani");
        vz1.setKor(27);
        vz1.setIsFerfi(true);
        versenyzoRepository.save(vz1);

        VersenyzoEntry vz2 = new VersenyzoEntry();
        vz2.setNev("Viktor");
        vz2.setKor(43);
        vz2.setIsFerfi(true);
        versenyzoRepository.save(vz2);

        VersenyzoEntry vz3 = new VersenyzoEntry();
        vz3.setNev("Lili");
        vz3.setKor(20);
        vz3.setIsFerfi(false);
        versenyzoRepository.save(vz3);

        VersenyzoEntry vz4 = new VersenyzoEntry();
        vz4.setNev("Bendi");
        vz4.setKor(30);
        vz4.setIsFerfi(true);
        versenyzoRepository.save(vz4);

        EredmenyEntry e1 = new EredmenyEntry();
        e1.setIdo(19);
        e1.setVerseny(v1);
        e1.setVersenyzo(vz1);
        eredmenyRepository.save(e1);

        EredmenyEntry e2 = new EredmenyEntry();
        e2.setIdo(28);
        e2.setVerseny(v1);
        e2.setVersenyzo(vz2);
        eredmenyRepository.save(e2);

        EredmenyEntry e3 = new EredmenyEntry();
        e3.setIdo(20);
        e3.setVerseny(v1);
        e3.setVersenyzo(vz3);
        eredmenyRepository.save(e3);

        EredmenyEntry e4 = new EredmenyEntry();
        e4.setIdo(5);
        e4.setVerseny(v1);
        e4.setVersenyzo(vz4);
        eredmenyRepository.save(e4);

        EredmenyEntry e5 = new EredmenyEntry();
        e5.setIdo(38);
        e5.setVerseny(v2);
        e5.setVersenyzo(vz2);
        eredmenyRepository.save(e5);

        EredmenyEntry e6 = new EredmenyEntry();
        e6.setIdo(40);
        e6.setVerseny(v2);
        e6.setVersenyzo(vz3);
        eredmenyRepository.save(e6);
    }

}
