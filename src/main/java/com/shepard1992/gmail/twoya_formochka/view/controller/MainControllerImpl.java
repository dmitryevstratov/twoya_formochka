package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.MainController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainControllerImpl implements MainController {

    @Override
    @GetMapping({"/index.html", "/"})
    public ModelAndView getMainPage() {
        return new ModelAndView("index");
    }

}
