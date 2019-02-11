package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Coop;

public interface CoopRepository extends CrudRepository<Coop, String>{

	Coop findCoopByJobId(int jobId);
}
