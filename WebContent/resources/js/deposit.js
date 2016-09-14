// Modal window deposit addition validation
$(function() {
	$("#span-card").hide();
	$("#span-cardNumber").hide();
	$("#span-summa").hide();
	$("#span-limit").hide();
});
$('input[name=card]').change(function(){
	$("#span-card").hide();
});
$("#deposit-form").submit(function() {
	if ($("#cardNumber").val() == "" && $("#summa").val() == "" && !$('input[name=card]:checked').val()) {
		$("#span-card").show();
		$("#span-cardNumber").show();
		$("#span-summa").show();
		$("#cardNumber").focus();
		return false;
	} else {
		$("#span-card").hide();
		$("#span-cardNumber").hide();
		$("#span-summa").hide();
		$("#span-limit").hide();
	}
	if (!$('input[name=card]:checked').val()) {
		$("#span-card").show();
		return false;
	}
	if ($("#cardNumber").val() == "") {
		$("#span-cardNumber").show();
		$("#lcardNumber").focus();
		return false;
	} else {
		$("#span-cardNumber").hide();
	}
	
	if ($("#summa").val() == "") {
		$("#span-summa").show();
		$("#summa").focus();
		return false;
	} else {
		$("#span-summa").hide();
	}
	if ($("#summa").val() != "") {
		if ($("#summa").val() < 1 || $("#summa").val() > 1000) {
			$("#span-summa").show();
			$("#summa").focus();
			return false;
		}
	}
	if (parseFloat($("#cardLimit").val()) < parseFloat($("#summa").val())) {
		$("#span-limit").show();
		$("#summa").focus();
		return false;
	}
	
});

// Hide span messages
function delSpan() {
	$("#span-card").hide();
	$("#span-summa").hide(); 
	$("#span-limit").hide();
}