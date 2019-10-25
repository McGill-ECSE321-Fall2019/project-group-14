package ca.mcgill.ecse321.tutoringsystem.controller;

import ca.mcgill.ecse321.tutoringsystem.dto.ApplicationDto;
import ca.mcgill.ecse321.tutoringsystem.dto.ManagerDto;
import ca.mcgill.ecse321.tutoringsystem.model.*;

public class DtoConverter {

	private static ApplicationDto convertToDto(Application a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Application!");
		}
		ApplicationDto applicationDto = new ApplicationDto(a.getIsExistingUser(), a.getName(), a.getEmail(), a.getCourses(), a.getApplicationId());
		return applicationDto;
	}
	
	public static ManagerDto convertToDto(Manager m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		ManagerDto managerDto = new ManagerDto(m.getName(), m.getEmail(), m.getPassword());
		return managerDto;
	}
}
