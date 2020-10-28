package id2207.group15.backend.spring.controller;

import id2207.group15.backend.spring.entity.Application;
import id2207.group15.backend.spring.entity.ApplicationRepository;
import id2207.group15.backend.spring.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/app")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping(path = "/submit")
    public String submit(
            Long id,
            Long CRId,
            String department,
            Long leader_id,
            String description
    )
    {
        try{
            applicationRepository.save(new Application(
                    id,
                    CRId,
                    department,
                    leader_id,
                    description
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

    @DeleteMapping(path = "/delete")
    public boolean delete(Long id, Long leader_id){
        if (applicationRepository.findById(id).orElse(null).getLeaderId().equals(leader_id)) {
            applicationRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    @GetMapping(path = "/all")
    public Iterable<Application> all(){
        return applicationRepository.findAll();
    }

}
