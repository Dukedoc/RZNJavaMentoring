addEvent(window, 'load', windowLoad);
 
/* Кроссбраузерное добавление обработчика события */ 
function addEvent(obj, evType, fn){ 
 if (obj.addEventListener) {
  obj.addEventListener(evType, fn, false);
 } else if (obj.attachEvent) {
  obj.attachEvent('on' + evType, fn);
 }
}
 
/* Получаем родительскую форму для элемента */
function getParentForm(obj) {
 while ((obj = obj.parentNode)) {
  if (obj.nodeName == 'FORM') {
   break;
  }
 }
 return obj;
}
 
/* Ищем все submit-кнопки с классом link и заменяем их на ссылки */ 
function windowLoad() {
 var buttons = document.getElementsByTagName('input');
 for (var i = 0; i < buttons.length ; i++) {
  if (buttons[i].getAttribute('type') == 'submit' && buttons[i].className == 'link') {
   var link = document.createElement('a');
   link.appendChild(document.createTextNode(buttons[i].getAttribute('value')));
   link.setAttribute('href', '#');
   addEvent(link, 'click', linkClick);
 
   var parent = buttons[i].parentNode;
   parent.removeChild(buttons[i]);
   parent.appendChild(link);
  }
 }
}
 
/* Отправляем форму по нажатию на ссылку */
function linkClick(e) {
 var e = window.event || e;
 var target = e.target || e.srcElement;
 var parentForm = getParentForm(target);
 parentForm.submit();
 
 if (window.event) { e.returnValue = false; } else { e.preventDefault(); }
}

// Validate form.
function validateForm(_form) {
	var errMess = "";
	var login = _form.elements["login"].value;	
	var password = _form.elements["password"].value;
	var checkPassword = /^[\w\.!@#$%^&]{6,}$/;
	var checkString = /^[\w_]+[\w\s\.,!?]*$/;
	
	markErrorField(false);
	if (!checkString.test(login)) {
		errMess += "Login is not correct. \n";
		markErrorField(_form.elements["login"]);
	}
	if (!checkPassword.test(password)) {
		errMess += "Password must be longer then 6 symbols.\n";
		markErrorField(_form.elements["password"]);
	}
	if (errMess != "") {
		alert(errMess);
		return false;
	} else {
		return true;
	}

}
