package be.thomasmore.meeting.controllers;


import be.thomasmore.meeting.model.Champion;
import be.thomasmore.meeting.repositories.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChampionController {

    @Autowired
    private ChampionRepository championRepository;

    @GetMapping({"/championlist"})
    public String championList(Model model) {
        Iterable<Champion> champions;

        champions = championRepository.findAll();
        model.addAttribute("champions", champions);
        return "championlist";
    }
}
