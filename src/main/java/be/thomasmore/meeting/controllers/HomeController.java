package be.thomasmore.meeting.controllers;

import be.thomasmore.meeting.model.Artist;
import be.thomasmore.meeting.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Artist> artists = artistRepository.findAll();
        long artistCount = artistRepository.count();

        model.addAttribute("artists", artists);
        model.addAttribute("artistCount", artistCount);

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
