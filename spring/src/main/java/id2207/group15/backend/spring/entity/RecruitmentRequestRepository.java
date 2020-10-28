package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

public interface RecruitmentRequestRepository extends CrudRepository<RecruitmentRequest, Long> {

    Iterable<RecruitmentRequest> findByContractType(String contractType);

    Iterable<RecruitmentRequest> findByRequestingDepartment(String requestingDepartment);

    Iterable<RecruitmentRequest> findAllByJobTitle(String jobTitle);

}
