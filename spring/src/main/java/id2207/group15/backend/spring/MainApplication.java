package id2207.group15.backend.spring;

import id2207.group15.backend.spring.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(StaffRepository staffRepository,
                                  ClientRequestRepository clientRequestRepository,
                                  RecruitmentRequestRepository recruitmentRequestRepository,
                                  ApplicationRepository applicationRepository,
                                  TaskRepository taskRepository) {
        return (args) -> {
            staffRepository.save(new Staff("Yuehao","Sui","yuehao","1111111","IT","System Admin"));
            staffRepository.save(new Staff("Zhe","Wang","zhe","2222222","Services","HR Leader"));
            staffRepository.save(new Staff("Bruce","Black","alice","3333333","Production","Senior Customer Service"));
            recruitmentRequestRepository.save(new RecruitmentRequest(null,"Long Term","Service",3,"Developer","Need a Female"));
            clientRequestRepository.save(new ClientRequest("Alice","Party",Date.valueOf("2020-10-10"),Date.valueOf("2020-10-11"),18000,10,"Good Food","","","","","","",""));
            clientRequestRepository.save(new ClientRequest("Bob","Class",Date.valueOf("2020-11-01"),Date.valueOf("2020-11-10"),81000,100,"Big Room","A Class for kids","Sweets","Cartoon","Kids Song","Comic","Projector","Tables"));
            applicationRepository.save(new Application(null, 1L,"Services",2L,"Prepare"));
            applicationRepository.save(new Application(null, 2L,"Production",3L,"Take Photos"));
            taskRepository.save(new Task(null, "Demo Task 1", "HighPriority", "Alice", 7L, 1L, "Demo Description"));
            taskRepository.save(new Task(null, "Demo Task 2", "LowPriority", "Alice", 7L, 2L, "Demo Description2"));
            taskRepository.save(new Task(null, "Demo Task 3", "HighPriority", "Bob", 8L, 3L, "Demo Description3"));
            return;
        };
    }

}
