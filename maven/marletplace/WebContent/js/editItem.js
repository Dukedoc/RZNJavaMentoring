// Validate form.
function submitForm(_form) {
	var chkString = /^.+$/;
	var chkFloat = /^[0-9]+(\.|,)?[0-9]*$/;
	var chkTimeLeft = /^[0-9]*(:[0-5][0-9])?$/;
	var errMess = "";

	markErrorField(false);

	if (!chkString.test(_form.elements["title"].value)) {
		errMess += "\"Title of item\" is not valid.\n";
		markErrorField(_form.elements["title"]);
	}
	if (!chkFloat.test(_form.elements["startPrice"].value)) {
		errMess += "\"Start Price\" must be float.\n";
		markErrorField(_form.elements["startPrice"]);
	}
	if (!document.getElementsByName("buyItNow")[0].checked) {
		if (!_form.timeLeft.value || _form.timeLeft.value == 0) {
			errMess += "Time left is empty.\n";
			markErrorField(_form.elements["timeLeft"]);
		} else {
			if (!chkTimeLeft.test(_form.elements["timeLeft"].value)) {
				errMess += "Time left is not valid.\n";
				markErrorField(_form.elements["timeLeft"]);
			}
		}
		if (!chkFloat.test(_form.elements["bidIncrement"].value)) {
			errMess += "Bid Increment is not valid";
			markErrorField(_form.elements["bidIncrement"]);
		}
	} else {
		if (!chkTimeLeft.test(_form.elements["timeLeft"].value)) {
			errMess += "Time left is not valid.\n";
			markErrorField(_form.elements["timeLeft"]);
		}
	}

	if (errMess != "") {
		alert(errMess);
		return false;
	} else {
		return true;
	}

}

// Enable/Disable check box Bid Increment
function isBuyItNow(_element) {
	if (_element.checked) {
		document.getElementsByName("bidIncrement")[0].disabled = "disabled";
	} else {
		document.getElementsByName("bidIncrement")[0].disabled = "";
	}
}