package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Genre;
import peaksoft.model.MailSender;
import peaksoft.service.impl.GenreService;
import peaksoft.service.impl.MailSenderService;
@Controller
@RequestMapping("/mailSenders")
public class MailSenderController {
    private final MailSenderService mailSenderService;

    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @GetMapping("/add")
    public String addMailSender(Model model){
        model.addAttribute("mail",  new MailSender());
        return "mailSender/save";
    }
    @PostMapping("/save")
    public String saveMailSender(@ModelAttribute("mail")MailSender mailSender){
        mailSenderService.save(mailSender);
        return "redirect:find-all";
    }
    @GetMapping("/find-all")
    public String getAllMailSender(Model model){
        model.addAttribute("mailSenderList",mailSenderService.findAll());
        return "mailSender/get-all";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        MailSender mailSender=mailSenderService.findById(id);
        model.addAttribute("mail",mailSender);
        return "mailSender/update";
    }
    @PostMapping("{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("mail") MailSender mailSender) {
        mailSenderService.update(id,mailSender);
        return "redirect:find-all";
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        mailSenderService.deleteById(id);
        return "redirect:find-all";
    }
}
