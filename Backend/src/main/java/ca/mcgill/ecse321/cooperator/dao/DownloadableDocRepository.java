package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.DownloadableDoc;

public interface DownloadableDocRepository extends CrudRepository<DownloadableDoc, String>{

	DownloadableDoc findDownloadableFormByDocId(int id);
}
