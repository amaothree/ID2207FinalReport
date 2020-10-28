package id2207.group15.backend.spring.controller;


import id2207.group15.backend.spring.entity.FinancialRequest;
import id2207.group15.backend.spring.entity.FinancialRequestRepository;
import id2207.group15.backend.spring.entity.Subteam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/FR")
public class FinancialRequestController {

    @Autowired
    private FinancialRequestRepository financialRequestRepository;

    @PostMapping(path = "/submit")
    public String submit(
            Long id,
            String requesting_department,
            Long reference,
            double required_amount,
            String reason,
            Long leader_id
    ){
        try{
            financialRequestRepository.save(new FinancialRequest(
                    id,
                    requesting_department,
                    reference,
                    required_amount,
                    reason,
                    leader_id
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

    @DeleteMapping(path = "/delete")
    public boolean delete(Long id, Long leader_id){
        if (financialRequestRepository.findById(id).orElse(null).getLeaderId().equals(leader_id)) {
            financialRequestRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
