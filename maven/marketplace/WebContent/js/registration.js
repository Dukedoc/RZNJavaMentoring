// Validate form.
function submitForm(_form) {
	var checkPassword = /^[\w\.!@#$%^&]{6,}$/;
	var checkPhone = /^\(\d{3}\) \d{3}-\d{2}-\d{2}$/;
	var checkString = /^[\w_]+[\w\s\.,!?]*$/;
	var checkLogin = /^\w+$/;
	var checkEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	markErrorField(false);
	var errMess = "";
	if (!checkString.test(_form.elements["fullName"].value)) {
		errMess += "Your Full Name is empty.\n";
		markErrorField(_form.elements["fullName"]);
	}
	if (!checkString.test(_form.elements["address"].value)) {
		errMess += "Your Billing Address is empty.\n";
		markErrorField(_form.elements["address"]);
	}
	if (!checkPhone.test(_form.elements["phone"].value)) {
		errMess += "Your phone must be in format \"(111) 222-33-44\".\n";
		markErrorField(_form.elements["phone"]);
	}
	if (!checkEmail.test(_form.elements["email"].value)) {
		errMess += "Incorrect e-mail.\n";
		markErrorField(_form.elements["email"]);
	}
	if (!checkLogin.test(_form.elements["login"].value)) {
		errMess += "Your Login is empty.\n";
		markErrorField(_form.elements["login"]);
	} else if (_form.elements["login"].value == _form.elements["fullName"].value) {
		errMess += "Your Login must be different from Full Name. \n";
		markErrorField(_form.elements["login"]);
	}
	if (!_form.elements["password"].value) {
		errMess += "Your password is empty. \n";
		markErrorField(_form.elements["password"]);
	} else if (!_form.elements["rePassword"].value) {
		errMess += "You password confirmation is empty";
		markErrorField(_form.elements["rePassword"]);
	} else if (!checkPassword.test(_form.elements["password"].value)) {
		errMess += "Password must be longer than 6 characters \n";
		markErrorField(_form.elements["password"]);
	} else if (_form.elements["rePassword"].value != _form.elements["password"].value) {
		errMess += "You password confirmation does not equal to your Password. \n";
		markErrorField(_form.elements["password"]);
		markErrorField(_form.elements["rePassword"]);
	}

	if (errMess != "") {
		alert(errMess);
		return false
	} else {
		return true;
	}
}


