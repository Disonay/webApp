package controller;

import data.dao.house.HibernateHouseDAO;
import data.dao.house.HouseDAO;
import data.dao.person.HibernatePersonDAO;
import data.dao.person.PersonDAO;
import data.entities.HouseEntity;
import data.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ApplicationController {
    private final HouseDAO houseDAO;
    private final PersonDAO personDAO;

    public ApplicationController(@Autowired HibernateHouseDAO houseDAO, @Autowired HibernatePersonDAO personDAO) {
        this.houseDAO = houseDAO;
        this.personDAO = personDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String helloForm() {
        return "helloNameForm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/default-hello")
    public String defaultHello(Model model) {
        model.addAttribute("name", "Сергей");
        return "helloName";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hello")
    public String dynamicHello(Model model, @ModelAttribute("name") String name) {
        model.addAttribute("name", name);
        return "helloName";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name")
    public String nameForm() {
        return "name";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/name")
    public String saveName(@ModelAttribute("name") String name, HttpSession httpSession, Model model) {
        httpSession.setAttribute("name", name);
        return "middlename";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/middle-name")
    public String middleNameForm() {
        return "middlename";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/middle-name")
    public String saveMiddleName(@ModelAttribute("middleName") String middleName, HttpSession httpSession) {
        httpSession.setAttribute("middleName", middleName);
        return "surname";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/surname")
    public String surnameForm() {
        return "surname";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/surname")
    public RedirectView saveSurname(@ModelAttribute("surname") String surname, HttpSession httpSession) {
        httpSession.setAttribute("surname", surname);

        RedirectView redirect = new RedirectView("/hello-fio");
        redirect.setExposeModelAttributes(false);

        return redirect;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello-fio")
    public String helloFio(Model model, HttpSession session) throws SQLException {
        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        String middleName = (String) session.getAttribute("middleName");

        PersonEntity personEntity = new PersonEntity(name, surname, middleName);
        model.addAttribute("person", personDAO.save(personEntity));

        return "helloFio";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/persons")
    public String allPersons(Model model) throws SQLException {
        model.addAttribute("persons", personDAO.getAll());

        return "persons";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/house")
    public String getHouseForm() {
        return "houseForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/house")
    public String addHouse(Model model,
                           @ModelAttribute("city") String city,
                           @ModelAttribute("street") String street,
                           @ModelAttribute("number") int number) throws SQLException {
        houseDAO.save(new HouseEntity(city, street, number));
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/residents")
    public String houseResidents(Model model) throws SQLException {
        model.addAttribute("houses", houseDAO.getAll());
        return "houseResidents";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/person-house")
    public String choosePersonHouse(Model model) throws SQLException {
        model.addAttribute("houses", houseDAO.getAll());
        model.addAttribute("persons", personDAO.getAll());

        return "personToHouse";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/person-house")
    public String addPersonToHouse(Model model,
                                   @ModelAttribute("house") int houseId,
                                   @ModelAttribute("persons") List<Integer> personsId) throws SQLException {
        for (Integer personId : personsId) {
            houseDAO.addPersonToHouse(houseId, personDAO.findById(personId));
        }

        return "index";
    }
}
