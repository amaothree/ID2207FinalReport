package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Iterable<Task> findAllByPriority(String priority);

    Iterable<Task> findAllBySender(String sender);

    Iterable<Task> findAllByApplicationId(Long applicationId);
    
    Iterable<Task> findAllBySubteamId(Long subteamId);

}
