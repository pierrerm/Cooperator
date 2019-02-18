package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.PDF;

public interface PDFRepository extends CrudRepository<PDF, String>{

	PDF findPDFByDocId(int id);
}
