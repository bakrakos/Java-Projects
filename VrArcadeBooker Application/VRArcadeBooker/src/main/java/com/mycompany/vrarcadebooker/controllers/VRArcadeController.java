
package com.mycompany.vrarcadebooker.controllers;

import com.mycompany.vrarcadebooker.entity.VrBooker;
import com.mycompany.vrarcadebooker.repositories.VRArcadeBookerRepository;
import com.mycompany.vrarcadebooker.services.VRSaveService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the member functionality of the site
 *
 * @since 20200609
 * @author OB
 */
@Controller
@RequestMapping("/vrbookings")
public class VRArcadeController {

    
    private final VRArcadeBookerRepository vrArcadeBookerRepository;

    public VRArcadeController(VRArcadeBookerRepository vrabr) {
        vrArcadeBookerRepository = vrabr;
    }
    
    /**
     * Mapping to exports the courts from database to a file
     *
     * @since 20191212
     * @author Amro Daas
     * 
     * @Modified to export VR Bookings to a file
     * @By OB
     * @Since 20200624
     */
    
    @RequestMapping("/export")
    public String exportVrBookings(HttpSession session, Model model) throws IOException {
        session.setAttribute("title", "Vr Bookings");
        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAll();
        model.addAttribute("vrbooker", vrbookers);
        VRSaveService.saveVrBookingsToFile(vrbookers);
        return "other/export";

    }

    /**
     * Page to allow user to view database information.
     *
     * @since 20200609
     * @author OB
     */
    
    @RequestMapping("/list")
    public String list(Model model) {

        //Get the information from the database.
        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAll();

        //Counts and displays number of bookings
        //model.addAttribute("countReflections", + vrArcadeBookerRepository.count() );        
        model.addAttribute("vrbookers", vrbookers);

        //send the user to the list page.        
        return "vrbookings/list";        
                           
    }
    
     /**
     * Page to add information to the database.
     *
     * @since 20200609
     * @author OB
     *
     */    
    @RequestMapping("/add")
    public String add(Model model) {
        
        //creating new vrbooker object
        VrBooker vrbooker = new VrBooker();
        
        //setting id to 0
        vrbooker.setId(0);
        model.addAttribute("vrbooker", vrbooker);

        //send the user to the add page.  
        return "vrbookings/add";    
    }
    
      /**
     * Page to allow user to submit the add to the database.
     *
     * @since 20200609
     * @author OB
     *
     */
    @RequestMapping("/addSubmit")
    public String addSubmit(Model model, @Valid @ModelAttribute("vrbooker") VrBooker vrbooker, BindingResult result) {

        //Statement if validation error happens
        if (result.hasErrors()) {
            System.out.println("Validation error");
            return "vrbookings/add";
        }
        //Saves the information to the database.
        vrArcadeBookerRepository.save(vrbooker);
        
        //reloads the list of information
        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAll();
        model.addAttribute("vrbookers", vrbookers);
        
        //Counts and displays number of bookings
        //model.addAttribute("countReflections", + ojtreflections.size());
        
        System.out.println("test");
        
        //send the user to the list page.
        return "vrbookings/list";
    }
    
     /**
     * Page to allow user to delete a booking in the database.
     *
     * @since 20200609
     * @author OB
     *
     */
    
    @RequestMapping("/delete")
    public String delete(Model model, HttpServletRequest request){
        
        //Changes String to Int
        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);

        //Deletes by Id
        vrArcadeBookerRepository.deleteById(id);
        
        //reloads the list of information
        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAll();
        model.addAttribute("vrbookers", vrbookers);
        
        //send the user to the list page.  
        return "vrbookings/list";
    }
    
    /**
     * Page to allow user to edit a booking in the database.
     *
     * @since 20200609
     * @author OB
     *
     */
    
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request){
        
        //Changes String to Int
        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        
        //Container object
        Optional<VrBooker> selectedBooker = vrArcadeBookerRepository.findById(id);
        
        if (selectedBooker == null) {
            return "index";
        } else {
            model.addAttribute("vrbooker", selectedBooker);
            //send the user to the add page.  
            return "vrbookings/add";
        }
    }
    
    /**
     * Page to allow user to find a booking in the database by date.
     *
     * @since 20200614
     * @author OB
     *
     */

    @RequestMapping("/find")
    public String find(Model model) {

        //Load the names into the model
        Set<String> dates = new HashSet();

        ArrayList<VrBooker> vrbookings = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAll();
        for (VrBooker current : vrbookings) {
            dates.add(current.getDateOfBooking());
        }

        model.addAttribute("dates", dates);
        return "vrbookings/find";
    }

    /**
     * Page to allow user to view bookings from find criteria 
     *
     * @since 20200614
     * @author OB
     * 
     */
    @RequestMapping("/findSubmit")
    public String findSubmit(Model model, @ModelAttribute("vrbooker") VrBooker vrbooker) {

        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) vrArcadeBookerRepository.findAllBydateOfBooking(vrbooker.getDateOfBooking());

        //Test
        System.out.println("Find Submit Test" + vrbooker.getDateOfBooking());

        model.addAttribute("vrbookers", vrbookers);

        //Shows amount of reflections in find
        //model.addAttribute("countReflections", + ojtreflections.size());
        //Put the name in the model as well so it can be shown on the list view
        model.addAttribute("findNameMessage", " (" + vrbooker.getDateOfBooking() + ")");

        //send the user to the list page.
        return "vrbookings/list";
    }

    
}
