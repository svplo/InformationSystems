package constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class noteValidator implements ConstraintValidator<CheckYear, String> {

	String [] valid = {"Draft", "Submitted", "Accepted", "Published"};
    public void initialize(CheckYear constraintAnnotation) {
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        for(String s : valid){
        	if(object.equals(s)){
        		return true;
        	}
        }
        return false;
    }

}
