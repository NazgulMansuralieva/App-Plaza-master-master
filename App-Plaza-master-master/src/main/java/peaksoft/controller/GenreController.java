package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Application;
import peaksoft.model.Genre;
import peaksoft.service.impl.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/add")
    public String addGenre(Model model){
        model.addAttribute("genre",  new Genre());
        return "genres/save";
    }
    @PostMapping("/save")
    public String saveGenre(@ModelAttribute("genre")Genre genre){
        genreService.save(genre);
        return "redirect:find-all";
    }
    @GetMapping("/find-all")
    public String getAllGenre(Model model){
        model.addAttribute("genreList",genreService.findAll());
        return "genres/get-all";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        Genre genre=genreService.findById(id);
        model.addAttribute("genre",genre);
        return "genres/update";
    }
    @PostMapping("{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("genre") Genre genre) {
        genreService.update(id,genre);
        return "redirect:find-all";
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        genreService.deleteById(id);
        return "redirect:find-all";
    }
}
