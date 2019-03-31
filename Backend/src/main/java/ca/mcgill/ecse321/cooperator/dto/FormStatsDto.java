package ca.mcgill.ecse321.cooperator.dto;

public class FormStatsDto {

	private int coopsWithNoForms;
	private int coopsWithOneForm;
	private int coopsWithTwoForms;
	private int coopsWithThreeForms;
	private int completedCoops;

	public FormStatsDto() {

	}

	public FormStatsDto(int coopsWithNoForms, int coopsWithOneForm, int coopsWithTwoForms,
			int coopsWithThreeForms, int completedCoops) {
		this.coopsWithNoForms = coopsWithNoForms;
		this.coopsWithOneForm = coopsWithOneForm;
		this.coopsWithTwoForms = coopsWithTwoForms;
		this.coopsWithThreeForms = coopsWithThreeForms;
		this.completedCoops = completedCoops;
	}

	public int getNoForms() {
		return coopsWithNoForms;
	}

	public int getOneForm() {
		return coopsWithOneForm;
	}

	public int getTwoForms() {
		return coopsWithTwoForms;
	}

	public int getThreeForms() {
		return coopsWithThreeForms;
	}
	
	public int completedCoop() {
		return completedCoops;
	}
}