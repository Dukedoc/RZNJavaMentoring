// Marks the field in red.
function markErrorField(_element) {
	var allLabels = document.getElementsByTagName("LABEL");
	if (_element) {
		for ( var i = 0; i < allLabels.length; i++) {
			if (allLabels[i].htmlFor == _element.name) {
				allLabels[i].style.color = "red";
			}
		}
	} else {
		for ( var i = 0; i < allLabels.length; i++) {
			allLabels[i].style.color = "black";
		}
	}
}