package id2207.group15.backend.spring.controller;


import id2207.group15.backend.spring.entity.Task;
import id2207.group15.backend.spring.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(path = "/submit")
    public String submit(
            Long id,
            String subject,
            String priority,
            String sender,
            Long application_id,
            Long subteam_id,
            String description
    ){
        try{
            taskRepository.save(new Task(
                    id,
                    subject,
                    priority,
                    sender,
                    application_id,
                    subteam_id,
                    description
            ));
        }catch (Exception e){
            return e.toString();
        }
        return "Succeed";
    }

    @GetMapping(path = "/get")
    public Iterable<Task> get(Long applicationId){
        return taskRepository.findAllByApplicationId(applicationId);
    }
}
