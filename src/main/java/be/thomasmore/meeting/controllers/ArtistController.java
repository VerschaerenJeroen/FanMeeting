package be.thomasmore.meeting.controllers;

import be.thomasmore.meeting.model.Artist;
import be.thomasmore.meeting.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model,
                                @PathVariable(required = false) Integer id) {
        long artistCount = artistRepository.count();
        model.addAttribute("artistcount", artistCount);

        if (id == null) {
            model.addAttribute("selection",false);
            return "artistdetails";
        }

        Optional<Artist> optionalArtist = artistRepository.findById(id);

        if (optionalArtist.isPresent()) {
            model.addAttribute("artist", artistRepository.findById(id).get());
        }
        return "artistdetails";
    }
}
