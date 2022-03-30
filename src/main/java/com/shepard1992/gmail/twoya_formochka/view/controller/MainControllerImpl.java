package com.shepard1992.gmail.twoya_formochka.view.controller;

import com.shepard1992.gmail.twoya_formochka.view.controller.api.ViewController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainControllerImpl implements ViewController {

    @Override
    @GetMapping({"/index.html", "/"})
    public ModelAndView getView() {
        return new ModelAndView("index");
    }

}
