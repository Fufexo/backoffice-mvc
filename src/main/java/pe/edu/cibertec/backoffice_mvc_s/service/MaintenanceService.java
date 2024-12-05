package pe.edu.cibertec.backoffice_mvc_s.service;

import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmEliminarDto;
import pe.edu.cibertec.backoffice_mvc_s.entity.Language;


import java.util.List;

public interface MaintenanceService {

    List<FilmDto> getAllFilms();

    FilmDetailDto getFilmById(int id);

    void saveFilm(FilmDetailDto filmDetailDto);

    List<Language> getAllLanguages();

    void deleteFilm(FilmEliminarDto filmEliminarDto);
}
