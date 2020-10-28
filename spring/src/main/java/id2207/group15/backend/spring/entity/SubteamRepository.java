package id2207.group15.backend.spring.entity;

import org.springframework.data.repository.CrudRepository;

public interface SubteamRepository extends CrudRepository<Subteam, Long> {

    Iterable<SubteamRepository> findAllByLeaderId(Long leaderId);
}
