package pe.edu.cibertec.backoffice_mvc_s.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmEliminarDto;
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
    public String detail(@PathVariable Integer id, Model model) {

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-detail";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model ){

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        List<Language> languages = maintenanceService.getAllLanguages();
        model.addAttribute("filmDetailDto", filmDetailDto);
        model.addAttribute("languages", languages);
        return "maintenance-edit";
    }

    @PostMapping("/save")
    public String saveFilm(FilmDetailDto filmDetailDto) {
        maintenanceService.saveFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/new")
    public String createFilm(Model model) {
        FilmDetailDto filmDetailDto = new FilmDetailDto(
                null, "", "", null, null, "", null, null, null, null, "", "", null);
        List<Language> languages = maintenanceService.getAllLanguages();

        model.addAttribute("filmDetailDto", filmDetailDto);
        model.addAttribute("languages", languages);
        return "maintenance-new"; // Nombre del archivo HTML para el formulario
    }

    @PostMapping("/delete")
    public String deleteFilm(@ModelAttribute FilmEliminarDto filmEliminarDto) {
        maintenanceService.deleteFilm(filmEliminarDto);
        return "redirect:/maintenance/start";
    }

}
