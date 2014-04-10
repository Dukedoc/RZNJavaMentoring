/**
 * 
 */
package marketplace.controller.spring.validators;

import marketplace.model.to.Item;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for Edit Item form.
 * 
 * @author Roman_Ten
 * 
 */
public class EditItemValidator implements Validator {
    private static final String BID_INCREMENT_MUST_BE_THE_NULL_MESSAGE = "Bid increment must be the null";
    private static final String EDIT_ITEM_BID_INCREMENT_IS_NOT_NULL_ERROR_CODE = "editItem.bidIncrementIsNotNull";
    private static final String BID_INCREMENT_MUST_BE_A_NOT_NULL_MESSAGE = "Bid Increment must be a not null";
    private static final String BID_INCREMENT_IS_NULL_ERROR_CODE = "editItem.bidIncrementIsNull";
    private static final String BID_INCREMENT_FIELD = "bidIncrement";
    private static final String THE_TIME_LEFT_MUST_BE_A_NOT_NULL_MESSAGE = "The Time Left must be a not null";
    private static final String TIME_LEFT_IS_NULL_ERROR_CODE = "editItem.timeLeftIsNull";
    private static final String TIME_LEFT_STR_FIELD = "timeLeftStr";
    private static final String START_PRICE_MUST_BE_A_NOT_NULL_MESSAGE = "Start Price must be a not null";
    private static final String START_PRICE_MESSAGE = "Start price must be a not null";
    private static final String START_PRICE_ERROR_CODE = "editItem.startPriceIsNull";
    private static final String START_PRICE_FIELD = "startPrice";
    private static final String TITLE_MESSAGE = "The Title is empty";
    private static final String EDIT_ITEM_ERROR_CODE = "editItem.title";
    private static final String TITLE_FIELD = "title";
    private static final double DELTA_NULL = 0.001;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean supports(Class theClass) {
	return theClass.equals(Item.class);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object command, Errors errors) {
	Item item = (Item) command;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, TITLE_FIELD,
		EDIT_ITEM_ERROR_CODE, TITLE_MESSAGE);
	ValidationUtils.rejectIfEmpty(errors, START_PRICE_FIELD,
		START_PRICE_ERROR_CODE, START_PRICE_MESSAGE);
	if (item.getStartPrice() == 0) {
	    errors.rejectValue(START_PRICE_FIELD, START_PRICE_ERROR_CODE,
		    START_PRICE_MUST_BE_A_NOT_NULL_MESSAGE);
	}
	if (!item.isBuyItNow()) {
	    if (Math.abs(item.getTimeLeft()) < DELTA_NULL) {
		errors.rejectValue(TIME_LEFT_STR_FIELD,
			TIME_LEFT_IS_NULL_ERROR_CODE,
			THE_TIME_LEFT_MUST_BE_A_NOT_NULL_MESSAGE);
	    }
	    if (Math.abs(item.getBidIncrement()) < DELTA_NULL) {
		errors.rejectValue(BID_INCREMENT_FIELD,
			BID_INCREMENT_IS_NULL_ERROR_CODE,
			BID_INCREMENT_MUST_BE_A_NOT_NULL_MESSAGE);
	    }
	} else {
	    if (item.getBidIncrement() != 0) {
		errors.rejectValue(BID_INCREMENT_FIELD,
			EDIT_ITEM_BID_INCREMENT_IS_NOT_NULL_ERROR_CODE,
			BID_INCREMENT_MUST_BE_THE_NULL_MESSAGE);
	    }
	}

    }

}
