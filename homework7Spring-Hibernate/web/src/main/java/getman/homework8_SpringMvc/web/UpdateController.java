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
public class UpdateController {
    @Autowired
    ClientService clientService;

    @GetMapping("/update")
    public ModelAndView getUpdatePage(){
        return new ModelAndView("updateClient");
    }

    @PostMapping("/update")
    public ModelAndView postUpdate(Client client, User user){

        clientService.updateClient(client,user);

        return new ModelAndView("index");
    }

}
