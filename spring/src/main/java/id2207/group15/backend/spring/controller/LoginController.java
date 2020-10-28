package id2207.group15.backend.spring.controller;

import id2207.group15.backend.spring.entity.ClientRequest;
import id2207.group15.backend.spring.entity.FinancialRequest;
import id2207.group15.backend.spring.entity.Staff;
import id2207.group15.backend.spring.entity.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {

    private final StaffRepository staffRepository;

    public LoginController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping(path = "/login")
    public Staff login(String username, String password){
        return staffRepository.findByUsernameAndPassword(username,password).orElse(null);
    }

    @GetMapping(path = "/staff/all")
    public Iterable<Staff> all(){
        return staffRepository.findAll();
    }

    @PostMapping(path = "/staff/submit")
    public String submit(Long id, String firstName, String lastName, Long subteamId,
                         String username, String password, String department,String title){
        try{
            staffRepository.save(new Staff(
                    id,
                    firstName,
                    lastName,
                    subteamId,
                    username,
                    password,
                    department,
                    title
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

}
