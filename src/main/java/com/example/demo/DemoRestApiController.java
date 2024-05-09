package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entry.EredmenyEntry;
import com.example.demo.entry.VersenyEntry;
import com.example.demo.entry.VersenyzoEntry;
import com.example.demo.repository.EredmenyRepository;
import com.example.demo.repository.VersenyRepository;
import com.example.demo.repository.VersenyzoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class DemoRestApiController {
    @Autowired
    private VersenyRepository versenyRepository;

    @Autowired
    private VersenyzoRepository versenyzoRepository;

    @Autowired
    private EredmenyRepository eredmenyRepository;

    @GetMapping("/getRunners")
    public List<VersenyzoEntry> getRunners() {
        return versenyzoRepository.findAll();
    }

    @PostMapping("/addRunner")
    public VersenyzoEntry addRunner(@RequestBody VersenyzoEntry newRunner) {
        VersenyzoEntry v = versenyzoRepository.save(newRunner);
        return v;
    }

    @GetMapping("/getRaceRunners/{ID}")
    public List<Map<String, Object>> getRaceRunners(@PathVariable Long ID) {
        List<EredmenyEntry> eredmenyek = eredmenyRepository.findAllByVerseny_VersenyId(ID);
        List<Map<String, Object>> response = new ArrayList<>();

        for (EredmenyEntry eredmeny : eredmenyek) {
            Map<String, Object> runner = new HashMap<>();
            runner.put("nev", eredmeny.getVersenyzo().getNev());
            runner.put("ido", eredmeny.getIdo());
            response.add(runner);
        }

        return response;
    }

    @PostMapping("/updateRace")
    public VersenyEntry updateRace(@RequestBody VersenyEntry updatedRace) {
        VersenyEntry race = versenyRepository.findById(updatedRace.getVersenyId()).orElseThrow();
        race.setNev(updatedRace.getNev());
        race.setTavolsag(updatedRace.getTavolsag());
        VersenyEntry v = versenyRepository.save(race);
        return v;
    }

    @PostMapping("/addResult")
    public EredmenyEntry addResult(@RequestBody Map<String, Long> body) {
        Long versenyId = body.get("versenyId");
        Long versenyzoId = body.get("versenyzoId");
        int ido = body.get("ido").intValue();

        Optional<VersenyEntry> verseny = versenyRepository.findById(versenyId);
        Optional<VersenyzoEntry> versenyzo = versenyzoRepository.findById(versenyzoId);

        if (verseny.isPresent() && versenyzo.isPresent()) {
            EredmenyEntry newEredmeny = new EredmenyEntry();
            newEredmeny.setVerseny(verseny.get());
            newEredmeny.setVersenyzo(versenyzo.get());
            newEredmeny.setIdo(ido);
            return eredmenyRepository.save(newEredmeny);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A megadott versenyId vagy versenyzoId-val nem található verseny vagy versenyző.");
        }
    }

    @GetMapping("/getAverageTime/{VERSENYID}")
    public double getAverageTime(@PathVariable Long VERSENYID) {
        List<EredmenyEntry> eredmenyek = eredmenyRepository.findAllByVerseny_VersenyId(VERSENYID);

        if (eredmenyek.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A megadott versenyId-val nem található eredmény.");
        }

        double osszesIdo = 0.0;
        for (EredmenyEntry eredmeny : eredmenyek) {
            osszesIdo += eredmeny.getIdo();
        }

        return osszesIdo / eredmenyek.size();
    }
}
