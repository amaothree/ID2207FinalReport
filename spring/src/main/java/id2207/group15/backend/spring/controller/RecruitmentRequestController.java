package id2207.group15.backend.spring.controller;

import id2207.group15.backend.spring.entity.RecruitmentRequest;
import id2207.group15.backend.spring.entity.RecruitmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/RR")
public class RecruitmentRequestController {

    @Autowired
    private RecruitmentRequestRepository recruitmentRequestRepository;

    @PostMapping(path = "/submit")
    public String submit(
        Long id,
        String contractType,
        String requestingDepartment,
        double yearOfExperience,
        String jobTitle,
        String description
    )
    {
        try{
            recruitmentRequestRepository.save(new RecruitmentRequest(
                    id,
                    contractType,
                    requestingDepartment,
                    yearOfExperience,
                    jobTitle,
                    description
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

    @GetMapping(path = "/all")
    public Iterable<RecruitmentRequest> all(){
        return recruitmentRequestRepository.findAll();
    }

}
