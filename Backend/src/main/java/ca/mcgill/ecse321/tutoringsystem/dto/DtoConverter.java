package ca.mcgill.ecse321.tutoringsystem.dto;

import ca.mcgill.ecse321.tutoringsystem.model.*;

public class DtoConverter {

	private ApplicationDto convertToDto(Application a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Application!");
		}
		ApplicationDto applicationDto = new ApplicationDto(a.getIsExistingUser(), a.getName(), a.getEmail(), a.getCourses(), a.getApplicationId());
		return applicationDto;
	}
}
