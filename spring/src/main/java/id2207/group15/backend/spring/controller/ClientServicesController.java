package id2207.group15.backend.spring.controller;

import id2207.group15.backend.spring.entity.ClientRequest;
import id2207.group15.backend.spring.entity.ClientRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping(path = "/CS")
public class ClientServicesController {

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @PostMapping(path = "/submit")
    public String submit(Long id, String client_name, String event_type, String from, String to, double expected_budget, int expected_attendees, String preferences, String description, String food_and_drinks, String filming_and_photos, String music, String poster_and_artwork, String computer_and_related_issues, String other_needs){
        try{
            clientRequestRepository.save(new ClientRequest(id,client_name,event_type,Date.valueOf(from),Date.valueOf(to),expected_budget,expected_attendees,preferences,description,food_and_drinks,filming_and_photos,music,poster_and_artwork,computer_and_related_issues,other_needs));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

    @GetMapping(path = "/all")
    public Iterable<ClientRequest> all(){
        return clientRequestRepository.findAll();
    }

}
