$(document).ready(function() {
	$("#" + $("#currentPage").val() + "_li").addClass("active");
});
		
$("#submitButton").click(function() {
	$("#currentPage").val("1");
});
		
$(".page-link").click(function() {
	$("#currentPage").val($(this).text());
	$("#searchForm").submit();
});
		
$(".userLink").click(function() {
	$(".btn-primary").prop('disabled', true);
	$(".btn-success").prop('disabled', false);
	$(".btn-danger").prop('disabled', false);
			
	$("input[name='userId']").val(this.id);
			
	if ($("#"+this.id+"_balance").text() == "0.0") {
		$(".btn-primary").prop('disabled', false);
	}
	if ($("#"+$("#userId").val()+"_betAllowFlag").val() == 0) {
		$(".btn-danger").prop('disabled', true);
	} else {
		$(".btn-success").prop('disabled', true);
	}
						
	$("#userLogin").text($(this).text());
	$("#userName").text($("#"+this.id+"_name").text());
	$("#userSurname").text($("#"+this.id+"_surname").text());
	$("#userBalance").text($("#"+this.id+"_balance").text());
	$("#userDateOfBirth").text($("#"+this.id+"_dateOfBirth").text());
	$("#userEmail").text($("#"+this.id+"_email").val());
	$("#userPassport").text($("#"+this.id+"_passport").val());
	$("#userAddress").text($("#"+this.id+"_address").val());
	$("#userPhone").text($("#"+this.id+"_phone").val());
	$("#userBetAllow").text($("#"+this.id+"_betAllow").text());
});