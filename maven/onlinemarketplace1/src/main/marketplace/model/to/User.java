package marketplace.model.to;

import java.io.Serializable;

/**
 * User Transfer Object.
 * 
 * @author Roman_Ten
 * 
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userID;
    private String fullName;
    private String billingAddress;
    private String email;
    private String login;
    private String password;
    private String phone;
    private String rePassword;

    /**
     * Get user_id for user.
     * 
     * @return the userID is USER_ID.
     */
    public int getUserID() {
	return userID;
    }

    /**
     * Set USER_ID.
     * 
     * @param userID
     *            the userID to set
     */
    public void setUserID(int userID) {
	this.userID = userID;
    }

    /**
     * Get full name for user.
     * 
     * @return the fullName is FULL_NAME.
     */
    public String getFullName() {
	return fullName;
    }

    /**
     * Set FULL_NAME.
     * 
     * @param fullName
     *            the fullName to set.
     */
    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    /**
     * Get billing address for user.
     * 
     * @return the billingAddress is BILLING_ADDRESS.
     */
    public String getBillingAddress() {
	return billingAddress;
    }

    /**
     * Set BILLING_ADDRESS.
     * 
     * @param billingAddress
     *            the billingAddress to set
     */
    public void setBillingAddress(String billingAddress) {
	this.billingAddress = billingAddress;
    }

    /**
     * Get e-mail.
     * 
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * Set e-mail.
     * 
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Get PHONE.
     * 
     * @return the phone is PHONE.
     */
    public String getPhone() {
	return phone;
    }

    /**
     * Set PHONE.
     * 
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
	this.phone = phone;
    }

    /**
     * Get login.
     * 
     * @return the login is LOGIN.
     */
    public String getLogin() {
	return login;
    }

    /**
     * Set LOGIN.
     * 
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
	this.login = login;
    }

    /**
     * get password
     * 
     * @return the password is PASSWORD.
     */
    public String getPassword() {
	return password;
    }

    /**
     * Set PASSWORD.
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Feel this user fields from another user.
     * 
     * @param anotherUser
     *            the another user.
     */
    public void feel(User anotherUser) {
	this.userID = anotherUser.userID;
	this.fullName = anotherUser.fullName;
	this.billingAddress = anotherUser.billingAddress;
	this.email = anotherUser.email;
	this.login = anotherUser.login;
	this.password = anotherUser.password;
	this.phone = anotherUser.phone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((billingAddress == null) ? 0 : billingAddress.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((fullName == null) ? 0 : fullName.hashCode());
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((phone == null) ? 0 : phone.hashCode());
	result = prime * result + userID;
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
	if (!(obj instanceof User)) {
	    return false;
	}
	User other = (User) obj;
	if (billingAddress == null) {
	    if (other.billingAddress != null) {
		return false;
	    }
	} else if (!billingAddress.equals(other.billingAddress)) {
	    return false;
	}
	if (email == null) {
	    if (other.email != null) {
		return false;
	    }
	} else if (!email.equals(other.email)) {
	    return false;
	}
	if (fullName == null) {
	    if (other.fullName != null) {
		return false;
	    }
	} else if (!fullName.equals(other.fullName)) {
	    return false;
	}
	if (login == null) {
	    if (other.login != null) {
		return false;
	    }
	} else if (!login.equals(other.login)) {
	    return false;
	}
	if (password == null) {
	    if (other.password != null) {
		return false;
	    }
	} else if (!password.equals(other.password)) {
	    return false;
	}
	if (phone == null) {
	    if (other.phone != null) {
		return false;
	    }
	} else if (!phone.equals(other.phone)) {
	    return false;
	}
	if (userID != other.userID) {
	    return false;
	}
	return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "User [userID=" + userID + ", fullName=" + fullName
		+ ", billingAddress=" + billingAddress + ", email=" + email
		+ ", phone=" + phone + ", login=" + login + ", password="
		+ password + ", rePassword=" + rePassword + "]";
    }

    /**
     * Set rePassword.
     * 
     * @param rePassword
     *            the rePassword to set
     */
    public void setRePassword(String rePassword) {
	this.rePassword = rePassword;
    }

    /**
     * Get RePassoword.
     * 
     * @return the rePassword
     */
    public String getRePassword() {
	return rePassword;
    }

}
