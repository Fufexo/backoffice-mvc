package pe.edu.cibertec.backoffice_mvc_s.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmEliminarDto;
import pe.edu.cibertec.backoffice_mvc_s.entity.Film;
import pe.edu.cibertec.backoffice_mvc_s.entity.Language;
import pe.edu.cibertec.backoffice_mvc_s.repository.FilmRepository;
import pe.edu.cibertec.backoffice_mvc_s.repository.LanguageRepository;
import pe.edu.cibertec.backoffice_mvc_s.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<FilmDto> getAllFilms() {

        List<FilmDto> films = new ArrayList<>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(), film.getTitle(), film.getLanguage().getName(), film.getRentalRate());
            films.add(filmDto);
        });

        return films;
    }


    @Override
    public FilmDetailDto getFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate()
                )
        ).orElse(null);
    }

    @Override
    public void saveFilm(FilmDetailDto filmDetailDto) {
        if (filmDetailDto.filmId() == null) {
            Film newFilm = new Film();
            newFilm.setTitle(filmDetailDto.title());
            newFilm.setDescription(filmDetailDto.description());
            newFilm.setReleaseYear(filmDetailDto.releaseYear());
            Optional<Language> optionalLanguage = languageRepository.findById(filmDetailDto.languageId());
            if (optionalLanguage.isPresent()) {
                newFilm.setLanguage(optionalLanguage.get());
            } else {
                throw new RuntimeException("Idioma no encontrado");
            }
            newFilm.setRentalDuration(filmDetailDto.rentalDuration());
            newFilm.setRentalRate(filmDetailDto.rentalRate());
            newFilm.setLength(filmDetailDto.length());
            newFilm.setReplacementCost(filmDetailDto.replacementCost());
            newFilm.setRating(filmDetailDto.rating());
            newFilm.setSpecialFeatures(filmDetailDto.specialFeatures());
            newFilm.setLastUpdate(new Date());

            filmRepository.save(newFilm);
        } else {

            Optional<Film> optionalFilm = filmRepository.findById(filmDetailDto.filmId());
            Optional<Language> optionalLanguage = languageRepository.findById(filmDetailDto.languageId());

            if (optionalFilm.isPresent() && optionalLanguage.isPresent()) {
                Film film = optionalFilm.get();
                Language language = optionalLanguage.get();

                film.setTitle(filmDetailDto.title());
                film.setDescription(filmDetailDto.description());
                film.setReleaseYear(filmDetailDto.releaseYear());
                film.setLanguage(language);
                film.setRentalDuration(filmDetailDto.rentalDuration());
                film.setRentalRate(filmDetailDto.rentalRate());
                film.setLength(filmDetailDto.length());
                film.setReplacementCost(filmDetailDto.replacementCost());
                film.setRating(filmDetailDto.rating());
                film.setSpecialFeatures(filmDetailDto.specialFeatures());

                film.setLastUpdate(new Date());

                filmRepository.save(film);
            } else {
                throw new RuntimeException("Película o idioma no encontrados");
            }
        }
    }

    @Override
    public List<Language> getAllLanguages() {
        return (List<Language>) languageRepository.findAll();
    }

    @Override
    public void deleteFilm(FilmEliminarDto filmEliminarDto) {
        filmRepository.deleteById(filmEliminarDto.filmId());
    }

}
