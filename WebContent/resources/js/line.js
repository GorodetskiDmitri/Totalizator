$(document).ready(function() {
	$("#link1").val("ooooo");
});

// Make bet modal window (view) 
$(function() {
	$("#span-summa1").hide();
	$("#span-summa2").hide();
});

$(".betLink").click(function() {
	var separateIndex = (this.id).indexOf("_");
	var idLine = (this.id).substr(0,separateIndex);
	var outcome = (this.id).substr(separateIndex+1);
			
	$("#startDate").text($("#"+idLine+"_startDate").text());
	$("#sport").text($("#"+idLine+"_sport").val().toUpperCase() + ". " + $("#"+idLine+"_competition").text());
	$("#event").text($("#"+idLine+"_event").text());
	$("[name=lineId]").val(idLine);
	if (outcome == "win") {
		$("#outcome").text($("#outcomeWin").val());
		$("[name=outcome]").val("1");
	}
	if (outcome == "draw") {
		$("#outcome").text($("#outcomeDraw").val());
		$("[name=outcome]").val("2");
	}
	if (outcome == "lose") {
		$("#outcome").text($("#outcomeLose").val());
		$("[name=outcome]").val("3");
	}
	$("#coefficient").text($("#"+this.id).text());
	$("#minBet").text($("#"+idLine+"_minBet").val());
	$("#maxBet").text($("#"+idLine+"_maxBet").val()); 
	$("#summa").val("");
}); 
	
// Hide span messages
function delSpan() {
	$("#span-summa1").hide();
	$("#span-summa2").hide();
}


//Make bet modal window (simple validation) 
$("#bet-form").submit(function() {
	if ($("#summa").val() == "") {
		$("#span-summa1").show();
		$("#summa").focus();
		return false;
	}
		
	if (parseFloat($("#summa").val()) > parseFloat($("#balance").val())) {
		$("#span-summa2").show();
		$("#summa").focus();
		return false;
	}
	
	if (parseFloat($("#summa").val()) < parseFloat($("#minBet").text()) 
		|| parseFloat($("#summa").val()) > parseFloat($("#maxBet").text())) {
		$("#span-summa1").show();
		$("#summa").focus();
		return false;
	}
});