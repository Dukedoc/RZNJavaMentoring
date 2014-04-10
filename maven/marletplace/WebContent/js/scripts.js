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

// Sort a table.
function sort(_element) {
	var numChk = /^[0-9]*(.|,)?[0-9]*$/;
	var colSort = _element.innerHTML;
	var tr = _element.parentNode;
	var table = tr.parentNode;
	var th, colSortNum;
	var isNumber = false;

	for ( var i = 0; (th = tr.getElementsByTagName("th").item(i)); i++) {
		if (th.innerHTML == colSort) {
			colSortNum = i;
			if (th.prevsort == "y") {
				arrow = th.firstChild;
				_element.up = Number(!_element.up);
			} else {
				th.prevsort = "y";
				arrow = th.insertBefore(document.createElement("span"),
						th.firstChild);
				_element.up = 0;
			}
			arrow.innerHTML = _element.up ? "&#9660;" : "&#9650;";
		} else {
			if (th.prevsort == "y") {
				th.prevsort = "n";
				if (th.firstChild) {
					th.removeChild(th.firstChild);
				}

			}

		}
	}

	var rows = new Array();
	var numCount = 0;
	for ( var i = 1; i < table.rows.length; i++) {
		rows[i - 1] = new Array();
		rows[i - 1][0] = table.rows[i].getElementsByTagName("td").item(
				colSortNum).innerHTML;
		rows[i - 1][1] = table.rows[i];
		if (numChk.test(rows[i - 1][0])) {
			numCount++;
		}
	}
	if (numCount == (table.rows.length - 1)) {
		isNumber = true;
	}
	if (!_element.up) {
		if (isNumber) {
			rows.sort(compare);
		} else {
			rows.sort();
		}
	} else {
		if (isNumber) {
			rows.sort(compareDesc);
		} else {
			rows.reverse();
		}

	}
	for (i = 0; i < rows.length; i++) {
		table.appendChild(rows[i][1]);
	}

}
// Compare numeric of direct order
function compare(a, b) {
	return a[0] - b[0];
}
// Compares the numbers in reverse order.
function compareDesc(a, b) {
	return b[0] - a[0];
}
