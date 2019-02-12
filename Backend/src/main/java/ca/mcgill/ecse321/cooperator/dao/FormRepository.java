package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Form;

public interface FormRepository extends CrudRepository<Form, String>{

	Form findFormByFormId(String id);
}
