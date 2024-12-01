package pe.edu.cibertec.backoffice_mvc_s.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.entity.Language;
import pe.edu.cibertec.backoffice_mvc_s.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/start")
    public String start(Model model ){

        List<FilmDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model ){

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        List<Language> languages = maintenanceService.getAllLanguages();
        model.addAttribute("filmDetailDto", filmDetailDto);
        model.addAttribute("languages", languages);
        return "maintenance-detail";
    }

    @PostMapping("/save")
    public String saveFilm(FilmDetailDto filmDetailDto) {
        maintenanceService.saveFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

}
