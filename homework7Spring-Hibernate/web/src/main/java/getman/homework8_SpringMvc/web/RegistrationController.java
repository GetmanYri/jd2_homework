package getman.homework8_SpringMvc.web;


import getman.homework.ClientService;
import getman.homework.model.Client;


import getman.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    @Autowired
    ClientService clientService;

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(){
        return new ModelAndView("registrationClient");
    }

    @PostMapping("/registration")
    public ModelAndView postRegistration(Client client,User user){
        System.out.println(client);
        clientService.saveNewClient(client,user);
        System.out.println(client);

        return new ModelAndView("index");
    }



}
