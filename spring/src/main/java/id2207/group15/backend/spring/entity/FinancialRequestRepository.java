package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FinancialRequestRepository extends CrudRepository<FinancialRequest, Long> {

    Iterable<FinancialRequest> findAllByRequestingDepartment(String requestingDepartment);

    Iterable<FinancialRequest> findAllByLeaderId(Long leaderId);
}
