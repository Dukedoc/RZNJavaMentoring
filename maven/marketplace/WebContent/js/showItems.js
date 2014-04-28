// Validate form.
function submitForm(_form) {
	var chkFloat = /^[0-9]+(\.|,)?[0-9]*$/;
	var errMess = "";
	if (!chkFloat.test(_form.elements["bid"].value)) {
		errMess += "Bid must be float.\n";
		markErrorField(_form.elements["bid"]);
	}

	if (errMess != "") {
		alert(errMess);
		return false;
	} else {
		return true;
	}

}