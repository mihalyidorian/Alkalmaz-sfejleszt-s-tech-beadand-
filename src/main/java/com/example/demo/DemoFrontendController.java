package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.EredmenyRepository;
import com.example.demo.repository.VersenyRepository;
import com.example.demo.entry.EredmenyEntry;
import com.example.demo.entry.VersenyEntry;


@Controller
public class DemoFrontendController {
    @Autowired
    private VersenyRepository versenyRepository;

    @Autowired
    private EredmenyRepository eredmenyRepository;

    @GetMapping("/verseny")
    public String verseny(Model model){
        List<VersenyEntry> versenyek = versenyRepository.findAll();
        model.addAttribute("versenyek", versenyek);
        return "verseny";
    }

    @PostMapping("/addVerseny")
    public String addVerseny(@RequestParam("nev") String nev, @RequestParam("tavolsag") String tav) {
        try {
            int tavolsag = Integer.parseInt(tav);
            if(nev.length()>0 && tavolsag > 0){
                VersenyEntry nv = new VersenyEntry();
                nv.setNev(nev);
                nv.setTavolsag(tavolsag);
                versenyRepository.save(nv);
            }
        } catch (Exception e) {
        }
        return "redirect:/verseny";
    }

    @GetMapping("/verseny/{id}")
    public String eredmenyek(@PathVariable Long id, Model model) {
        try {
            VersenyEntry verseny = versenyRepository.findById(id).orElseThrow(() -> new RuntimeException("Nem található ilyen verseny. Azonosító: " + id));
            List<EredmenyEntry> eredmenyek = eredmenyRepository.findAllByVerseny_VersenyId(id);

            model.addAttribute("versenyNev", verseny.getNev());
            model.addAttribute("eredmenyek", eredmenyek);
            return "eredmenyek";
        } catch (Exception e) {
            return "redirect:/verseny";
        }
    }
}
