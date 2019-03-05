package ca.mcgill.ecse321.cooperator.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Semester;

public interface CoopRepository extends CrudRepository<Coop, String> {

	Coop findCoopByCoopId(int jobId);

	public default boolean isInSemester(Coop coop, Semester semester, int year) {
		int coopYear = 0;
		Semester coopSemester = coop.getSemester();

		if (coopSemester != semester)
			return false;

		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(coop.getStartDate());
		int yearStart = calendarStart.get(Calendar.YEAR);
		int monthStart = calendarStart.get(Calendar.MONTH);

		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(coop.getEndDate());
		int yearEnd = calendarEnd.get(Calendar.YEAR);
		int monthEnd = calendarEnd.get(Calendar.MONTH);

		if (yearStart == yearEnd)
			coopYear = yearStart;
		else if (monthStart >= 10 && monthStart < 2 && coopSemester == Semester.Winter)
			coopYear = yearEnd;
		else if (monthEnd >= 10 && monthEnd < 2 && coopSemester == Semester.Fall)
			coopYear = yearStart;
		else
			coopYear = yearStart;

		return (semester == coopSemester && year == coopYear);
	}
	
	public default boolean isPriorToTerm(String term, Semester semester, Date startDate, Date endDate) {
		String limitSemester;
		int limitYear, i, year = Integer.MAX_VALUE;

		for(i = 0; i < term.length(); i++) {
			char c = term.charAt(i);
			if (c >= '0'&& c <= '9') break;
		}
		
		limitSemester = term.substring(0, i-1).replaceAll("\\s","").toLowerCase();
		limitYear = Integer.parseInt(term.substring(i).replaceAll("\\s","").toLowerCase());
		
		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(startDate);
		int yearStart = calendarStart.get(Calendar.YEAR);

		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.setTime(endDate);
		int yearEnd = calendarEnd.get(Calendar.YEAR);
		
		if (yearStart == yearEnd) {
			year = yearStart;
		} else if (yearStart == yearEnd - 1) {
			if (semester == Semester.Fall) {
				year = yearStart;
			} else if (semester == Semester.Winter) {
				year = yearEnd;
			} else {
				year = yearStart;
			}
		}
		
		if (year < limitYear) return true;
		else if (year == limitYear){
			switch(limitSemester.charAt(0)) {
			case 'w': return false;
			case 's': if (semester == Semester.Winter) return true;
				      else return false;
			case 'f': if (semester == Semester.Fall) return false;
					  else return true;
			default: return false;
			}
		}
		return false;
	}
}
