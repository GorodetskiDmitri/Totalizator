// Simple form validation for add_event.jsp
$("#add-event-form").submit(function() {
	if ($("#eventName").val() == "") {
		alert($("#eventNameNotValid").val());
		return false;
	}
	if ($("#eventDate").val().indexOf("M") >= 0 || $("#eventDate").val().indexOf("d") >= 0 || $("#eventDate").val().length != 16) {
		alert($("#dateTimeNotValid").val());
		return false;
	}	
});

// Simple form validation for registration.jsp
$("#registration-form").submit(function() {
	var valid = true;
	$("form[name='registration-form']").find("input").not('[type="submit"]').each(function() {
		if ($(this).val() == "")
			valid = false;		
	});
	if (valid == false) {
		alert($("#inputValid").val());
		return false;
	}
	if ($("#regPassword").val() != $("#regConfirmPassword").val()) {
		alert($("#passwordValid").val());
        return false;
	}
	if (!$("#agree").prop("checked")) { 
        alert($("#checkboxValid").val());
        return false;
	}
});


// Simple form validation for results_for_fix.jsp
$(".btn-fix").click(function() {
	var separateIndex = (this.id).indexOf("_");
	var idLine = (this.id).substr(0, separateIndex);
	
	if ($("#"+idLine+"_score1").val() && $("#"+idLine+"_score2").val()) {
		var score1 = parseInt($("#"+idLine+"_score1").val());
		var score2 = parseInt($("#"+idLine+"_score2").val());
		if (isNaN(score1) || isNaN(score2)) {
			alert($("#scoreNaN").val());
			return false;
		} else if (score1 < 0 || score2 < 0) {
			alert($("#scoreNegative").val());
			return false;
		}
	} else {
		alert($("#scoreEmpty").val());
		return false;
	}
});