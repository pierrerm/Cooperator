package ca.mcgill.ecse321.cooperator.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Semester;

public interface CoopRepository extends CrudRepository<Coop, String> {

	Coop findCoopByJobId(int jobId);
	
	public default boolean isInSemester(Coop coop, Semester semester, int year) {
		int coopYear = 0;
		Semester coopSemester = coop.getSemester();
		
		if(coopSemester != semester) return false;
		
		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(coop.getStartDate());
		int yearStart = calendarStart.get(Calendar.YEAR);
		int monthStart = calendarStart.get(Calendar.MONTH);
		
		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(coop.getEndDate());
		int yearEnd = calendarEnd.get(Calendar.YEAR);
		int monthEnd = calendarEnd.get(Calendar.MONTH);
		
		if (yearStart == yearEnd) coopYear = yearStart;
		else if(monthStart >= 10 && monthStart < 2 && coopSemester == Semester.Winter) coopYear = yearEnd;
		else if(monthEnd >= 10 && monthEnd < 2 && coopSemester == Semester.Fall) coopYear = yearStart;
		else coopYear = yearStart;
		
		return (semester == coopSemester && year == coopYear);
	}
}
