/**
 * 
 */
package marketplace.controller.enumerations;

/**
 * Result of validation.
 * 
 * @author Roman_Ten
 * 
 */
public enum UserResultValidation {
    EMAIL("Incorrect e-mail."), LOGIN("Your Login is empty."), ADDRESS(
	    "Your Billing Address is empty."), PHONE(
	    "Your phone must be in format \"(111) 222-33-44\"."), FULL_NAME(
	    "Your Full Name is empty."), LOGIN_EQUALS_PASSWORD(
	    "Your Login must be different from Full Name."), PASSWORDS_DO_NOT_MATCH(
	    "You password confirmation does not equal to your Password."), PASSWORD_NOT_VALID(
	    "Password must be longer than 6 characters.");

    private String message;

    private UserResultValidation(String message) {
	this.message = message;
    }

    public Object getMessage() {
	return message;
    }

}
