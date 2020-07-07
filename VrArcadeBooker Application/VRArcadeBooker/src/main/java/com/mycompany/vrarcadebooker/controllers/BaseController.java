
package com.mycompany.vrarcadebooker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base Controller which maps to the home and about pages.
 *
 * @author OB
 * @since 20200609
 */

@Controller
public class BaseController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
