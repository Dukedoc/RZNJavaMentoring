/**
 * 
 */
package marketplace.model.to;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * AdvancedSearch Transfer Object.
 * 
 * @author Roman_Ten
 * 
 */
public class AdvancedSearchTO implements Serializable {

    private static final String DATE_MESSAGE_EXCEPTION = "Date will be in follow format: \"DD/MM/YYYY HH:MI\"";

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    private static final String DATE_TIME_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(20\\d\\d) ([01]?[0-9]|2[0-3]):[0-5][0-9]";

    private static final long serialVersionUID = -1505091737362534818L;

    private Integer itemID;
    private String title;
    private String description;
    private Float minPrice;
    private Float maxPrice;
    private boolean buyItNow;
    private Calendar startDate;
    private Calendar stopDate;
    private Integer bidderCount;

    /**
     * Get Item ID.
     * 
     * @return the itemID.
     */
    public Integer getItemID() {
	return itemID;
    }

    /**
     * Set Item ID.
     * 
     * @param itemID
     *            the itemID to set
     */
    public void setItemID(Integer itemID) {
	this.itemID = itemID;
    }

    /**
     * Get Title.
     * 
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Set Title.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Get Description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Set Description.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Get minimum price.
     * 
     * @return the minPrice
     */
    public Float getMinPrice() {
	return minPrice;
    }

    /**
     * Set minimum price.
     * 
     * @param minPrice
     *            the minPrice to set
     */
    public void setMinPrice(Float minPrice) {
	this.minPrice = minPrice;
    }

    /**
     * Get Maximum Price.
     * 
     * @return the maxPrice
     */
    public Float getMaxPrice() {
	return maxPrice;
    }

    /**
     * Set Maximum Price.
     * 
     * @param maxPrice
     *            the maxPrice to set
     */
    public void setMaxPrice(Float maxPrice) {
	this.maxPrice = maxPrice;
    }

    /**
     * Get Buy It Now.
     * 
     * @return the buyItNow
     */
    public boolean isBuyItNow() {
	return buyItNow;
    }

    /**
     * Set Buy It Now.
     * 
     * @param buyItNow
     *            the buyItNow to set
     */
    public void setBuyItNow(boolean buyItNow) {
	this.buyItNow = buyItNow;
    }

    /**
     * Get start date
     * 
     * @return the startDate
     */
    public Calendar getStartDate() {
	return startDate;
    }

    /**
     * Set Start Date.
     * 
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Calendar startDate) {
	this.startDate = startDate;
    }

    /**
     * Get Stop Date.
     * 
     * @return the stopDate
     */
    public Calendar getStopDate() {
	return stopDate;
    }

    /**
     * Set stop date.
     * 
     * @param stopDate
     *            the stopDate to set
     */
    public void setStopDate(Calendar stopDate) {
	this.stopDate = stopDate;
    }

    /**
     * Get String Start Date.
     * 
     * @return the start date of string.
     */
    public String getStartDateStr() {
	return calendarToString(startDate);
    }

    /**
     * Set String start date.
     * 
     * @param startDateString
     *            the string of start date.
     * @throws Exception
     *             if string format incorrect.
     */
    public void setStartDateStr(String startDateString) throws Exception {
	if (startDateString != null && !startDateString.equals("")
		&& !Pattern.matches(DATE_TIME_PATTERN, startDateString)) {
	    throw new Exception(DATE_MESSAGE_EXCEPTION);
	}
	this.startDate = stringToCalendar(startDateString);
    }

    /**
     * Get stop start date in string format.
     * 
     * @return the stop start date.
     */
    public String getStopDateStr() {
	return calendarToString(stopDate);

    }

    /**
     * Set stop date in string format.
     * 
     * @param dateString
     *            the date of string.
     * @throws Exception
     *             if string format is incorrect.
     */
    public void setStopDateStr(String dateString) throws Exception {
	if (dateString != null && !dateString.equals("")
		&& !Pattern.matches(DATE_TIME_PATTERN, dateString)) {
	    throw new Exception(DATE_MESSAGE_EXCEPTION);
	}
	setStopDate(stringToCalendar(dateString));
    }

    private Calendar stringToCalendar(String dateString) {
	Calendar calendar = null;
	if (dateString != null && !dateString.equals("")) {
	    try {
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat(DATE_PATTERN);
		date = formatter.parse(dateString);
		calendar = Calendar.getInstance();
		calendar.setTime(date);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	}
	return calendar;
    }

    private String calendarToString(Calendar calendar) {
	String dateString = null;
	if (calendar != null) {
	    Date date = calendar.getTime();
	    DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
	    dateString = formatter.format(date);
	}
	return dateString;
    }

    /**
     * Get Bidder Count.
     * 
     * @return the bidderCount
     */
    public Integer getBidderCount() {
	return bidderCount;
    }

    /**
     * Set bidder Count.
     * 
     * @param bidderCount
     *            the bidderCount to set
     */
    public void setBidderCount(Integer bidderCount) {
	this.bidderCount = bidderCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((bidderCount == null) ? 0 : bidderCount.hashCode());
	result = prime * result + (buyItNow ? 1231 : 1237);
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
	result = prime * result
		+ ((maxPrice == null) ? 0 : maxPrice.hashCode());
	result = prime * result
		+ ((minPrice == null) ? 0 : minPrice.hashCode());
	result = prime * result
		+ ((startDate == null) ? 0 : startDate.hashCode());
	result = prime * result
		+ ((stopDate == null) ? 0 : stopDate.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof AdvancedSearchTO)) {
	    return false;
	}
	AdvancedSearchTO other = (AdvancedSearchTO) obj;
	if (bidderCount == null) {
	    if (other.bidderCount != null) {
		return false;
	    }
	} else if (!bidderCount.equals(other.bidderCount)) {
	    return false;
	}
	if (buyItNow != other.buyItNow) {
	    return false;
	}
	if (description == null) {
	    if (other.description != null) {
		return false;
	    }
	} else if (!description.equals(other.description)) {
	    return false;
	}
	if (itemID == null) {
	    if (other.itemID != null) {
		return false;
	    }
	} else if (!itemID.equals(other.itemID)) {
	    return false;
	}
	if (maxPrice == null) {
	    if (other.maxPrice != null) {
		return false;
	    }
	} else if (!maxPrice.equals(other.maxPrice)) {
	    return false;
	}
	if (minPrice == null) {
	    if (other.minPrice != null) {
		return false;
	    }
	} else if (!minPrice.equals(other.minPrice)) {
	    return false;
	}
	if (startDate == null) {
	    if (other.startDate != null) {
		return false;
	    }
	} else if (!startDate.equals(other.startDate)) {
	    return false;
	}
	if (stopDate == null) {
	    if (other.stopDate != null) {
		return false;
	    }
	} else if (!stopDate.equals(other.stopDate)) {
	    return false;
	}
	if (title == null) {
	    if (other.title != null) {
		return false;
	    }
	} else if (!title.equals(other.title)) {
	    return false;
	}
	return true;
    }

    /**
     * Feel advancedSearch to from another advancedSearchTo.
     * 
     * @param anotherAdvancedSearch
     *            the another advancedSearchTO.
     */
    public void feel(AdvancedSearchTO anotherAdvancedSearch) {
	itemID = anotherAdvancedSearch.itemID;
	title = anotherAdvancedSearch.getTitle();
	description = anotherAdvancedSearch.getDescription();
	minPrice = anotherAdvancedSearch.getMinPrice();
	maxPrice = anotherAdvancedSearch.getMaxPrice();
	buyItNow = anotherAdvancedSearch.isBuyItNow();
	startDate = anotherAdvancedSearch.getStartDate();
	stopDate = anotherAdvancedSearch.getStopDate();
	bidderCount = anotherAdvancedSearch.getBidderCount();
    }

}
