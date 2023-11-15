package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Application;
import peaksoft.model.Genre;
import peaksoft.service.impl.ApplicationService;
import peaksoft.service.impl.GenreService;

import java.util.List;

@Controller
@RequestMapping("/apps")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final GenreService genreService;

    public ApplicationController(ApplicationService applicationService, GenreService genreService) {
        this.applicationService = applicationService;
        this.genreService = genreService;
    }
    @GetMapping("/add")
    public String addApplication(Model model){
        model.addAttribute("app",  new Application());
        return "apps/save";
    }
    @PostMapping("/save")
    public String saveApplication(@ModelAttribute("app")Application application){
        applicationService.save(application);
        return "redirect:find-all";
    }
    @GetMapping("/find-all")
    public String getAllApplication(Model model){
        model.addAttribute("applicationList",applicationService.findAll());
        return "apps/get-all";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id,Model model){
        Application application=applicationService.findById(id);
        model.addAttribute("app",application);
        return "apps/update";
    }
    @PostMapping("{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("app") Application application) {
        applicationService.update(id, application);
        return "redirect:find-all";
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        applicationService.deleteById(id);
        return "redirect:find-all";
    }
    @ModelAttribute("genreList")
    public List<Genre>getGenres(){
        return genreService.findAll();
    }
}
