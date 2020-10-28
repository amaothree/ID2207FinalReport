package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StaffRepository extends CrudRepository<Staff, Long> {

    Optional<Staff> findByUsernameAndPassword(String username, String password);

}
