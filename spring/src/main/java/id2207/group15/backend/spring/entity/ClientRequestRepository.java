package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ClientRequestRepository extends CrudRepository<ClientRequest, Long> {

    Iterable<ClientRequest> findAllByClientName(String clientName);

}
