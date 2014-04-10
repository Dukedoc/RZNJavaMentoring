/**
 * 
 */
package marketplace.model.exception;

import marketplace.controller.enumerations.ItemValidationFields;

/**
 * Exception for validation.
 * 
 * @author Roman_Ten
 * 
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = -1031970211959566315L;
    private ItemValidationFields field;

    /**
     * Constructor for ValidatorException.
     * 
     * @param field
     *            the field when throw exception.
     */
    public ValidatorException(ItemValidationFields field) {
	this.field = field;
    }

    /**
     * Get not valid field.
     * 
     * @return the field
     */
    public ItemValidationFields getField() {
	return field;
    }

}
