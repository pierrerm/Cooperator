package ca.mcgill.ecse321.cooperator.dto;

public class FormTypesDto {

	private int AForms;
	private int CEForms;
	private int SEForms;
	private int TWRForms;

	public FormTypesDto() {

	}

	public FormTypesDto(int AForms, int CEForms, int SEForms,
			int TWRForms) {
		this.AForms = AForms;
		this.CEForms = CEForms;
		this.SEForms = SEForms;
		this.TWRForms = TWRForms;
	}

	public int getNoForms() {
		return AForms;
	}

	public int getOneForm() {
		return CEForms;
	}

	public int getTwoForms() {
		return SEForms;
	}

	public int getThreeForms() {
		return TWRForms;
	}
}