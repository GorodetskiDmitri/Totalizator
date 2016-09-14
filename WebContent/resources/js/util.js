// Allow enter numbers only (for input with class="numOnly")
$('.numOnly').keypress(function(event) {
	if (event.which != 8 && event.which != 0) {
		if (event.which < 48 || event.which > 57) {
			return false;
		}
	}
});

// Allow enter only numbers and point (for input with class="decimalOnly")
$('.decimalOnly').keypress(function(event){
	if (event.which != 8 && event.which != 0) {
		if (event.which < 46 || event.which > 57) return false;
		if (event.which == 47) return false;
	}
	var val = event.target.value;
	if (event.which == 46) {
		return val.indexOf(".") < 0;
	}
});