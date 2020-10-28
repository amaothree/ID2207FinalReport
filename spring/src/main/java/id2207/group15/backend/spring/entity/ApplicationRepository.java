package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application,Long> {

    Iterable<Application> findAllByDepartment(String department);

    Iterable<Application> findAllByLeaderId(Long leaderId);

}
