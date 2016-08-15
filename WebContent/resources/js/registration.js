// Identify HTML-element, for which we want to initialize widget "Bootstrap datetimepicker"
$(function () {
	$('#datetimepicker').datetimepicker({pickTime:false, language: 'ru'});
});

// Reset all fields on Registration form
$('#resetBtn').click(function() {
	$(".form-control").val("");
	$("#agree").attr('checked', false);
});

// Simple form validation (check fields)
$("#registration").submit(function() {
	var valid = true;
	$("form[name='registration']").find("input").not('[type="submit"]').each(function() {
		if ($(this).val() == "")
			valid = false;		
	});
	if (valid == false) {
		alert($("#inputValid").val());
		return false;
	}
	if ($("#regPassword").val() != $("#regConfirmPassword").val()) {
		alert($("#passwordValid").val());
		alert($("#regPassword").val() + " and " + $("#regConfirmPassword").val());
        return false;
	}
	if (!$("#agree").prop("checked")) { 
        alert($("#checkboxValid").val());
        return false;
	}
});