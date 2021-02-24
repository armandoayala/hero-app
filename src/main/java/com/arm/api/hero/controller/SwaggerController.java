package com.arm.api.hero.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/doc")
public class SwaggerController {

    @RequestMapping(value = {"/{type}", "/"}, method = RequestMethod.GET)
    public ModelAndView documentation(@PathVariable(name = "type", required = false)
                                              String type, ModelMap model) {

        try {
            model.addAttribute("attribute", "redirectWithRedirectPrefix");

            if (type == null || type.isEmpty()) {
                return new ModelAndView("redirect:/v2/api-docs", model);
            }

            if (type.toUpperCase().compareTo("UI") == 0) {
                return new ModelAndView("redirect:/swagger-ui.html", model);
            }

            if (type.toUpperCase().compareTo("JSON") == 0) {
                return new ModelAndView("redirect:/v2/api-docs", model);
            }

            return new ModelAndView("redirect:/v2/api-docs", model);

        } catch (Exception ex) {

            return null;
        }
    }

}
