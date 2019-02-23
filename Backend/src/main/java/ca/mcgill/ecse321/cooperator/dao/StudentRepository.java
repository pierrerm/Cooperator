package ca.mcgill.ecse321.cooperator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.cooperator.model.Student;

@RepositoryRestResource(collectionResourceRel = "participants", path = "participants")
public interface StudentRepository extends CrudRepository<Student, String> {

	Student findStudentByUserId(int id);

	public default List<Student> findStudentsWithError() {
//		System.out.println("Hello");
//		EntityManager em = CooperatorApplication.factory.createEntityManager();
//		em.getTransaction().begin();
//
//		Query q = em.createNativeQuery("SELECT * FROM Student", Student.class);
//		List<Student> students = q.getResultList();
//
//		System.out.println("Hello");
//
//		for (Student s : students) {
//			System.out.println(s.getFirstName());
//		}
//
//		em.getTransaction().commit();
//		em.close();
//
//		return students;
		return null;
	}
}
