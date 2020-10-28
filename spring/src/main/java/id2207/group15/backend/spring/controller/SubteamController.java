package id2207.group15.backend.spring.controller;

import id2207.group15.backend.spring.entity.Subteam;
import id2207.group15.backend.spring.entity.SubteamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sub")
public class SubteamController {
    @Autowired
    private SubteamRepository subteamRepository;

    @PostMapping(path = "/submit")
    public String submit(
            Long id,
            String name,
            Long leader_id
    ){
        try{
            subteamRepository.save(new Subteam(
                    id,
                    name,
                    leader_id
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }
}
