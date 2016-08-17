// Allow enter numbers only
$('#cardNumber').keypress(function(event) {
	if (event.which != 8) {
		if (event.which < 48 || event.which > 57) {
			alert("sssss");
			return false;
		}
	}
});

//Allow enter only numbers and point
$('#summa').keypress(function(event){
	if (event.which != 8) {
		if (event.which < 46 || event.which > 57) return false;
		if (event.which == 47) return false;
	}
});