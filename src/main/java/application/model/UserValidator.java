package application.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "login", "login.empty");
		//ValidationUtils.rejectIfEmpty(errors, "password,");
		if (((User)o).getPassword().length() < 2) {
			errors.rejectValue("password", "value.lessThan2");
		}
	}
}
