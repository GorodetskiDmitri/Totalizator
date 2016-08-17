$(function() {
	$('.globe').on('click', function(){
		$('.icons').slideToggle();
	});
});

// Открываем страницу в новом окне
function newWindow(e) {
	var h = 500,
		w = 600;
	window.open(e, 'Login', 'scrollbars=1,height='+Math.min(h, screen.availHeight)+
		',width='+Math.min(w, screen.availWidth)+',left='+Math.max(0, (screen.availWidth - w)/2)+
		',top='+Math.max(0, (screen.availHeight - h)/2));
}

// Показать/скрыть сообщения в модальном окне логинации
$(function() {
	$("#span-login").hide();
	$("#span-password").hide();
});
$("#login-form").submit(function() {
	if ($("#login").val() == "" && $("#password").val() == "") {
		$("#span-login").show();
		$("#span-password").show();
		$("#login").focus();
	} else {
		$("#span-login").hide();
		$("#span-password").hide();
	}
	
	if ($("#login").val() == "") {
		$("#span-login").show();
		$("#login").focus();
		return false;
	} else {
		$("#span-login").hide();
	}
	
	if ($("#password").val() == "") {
		$("#span-password").show();
		$("#password").focus();
		return false;
	} else {
		$("#span-password").hide();
	}
})

function delSpan(span) {
	if (span == "login") { 
		$("#span-login").hide();
	}
	if (span == "password") { 
		$("#span-password").hide(); 
	}
}




// Проверка ставки перед отправкой
function betStatus() {
	if ($('#betValue').val() != null && $('#betValue').val() != "") {
		alert("Your BET is success. GOOD LUCK!!!");
		window.close();
	}
	else
		alert("Please, enter a value of your BET");
}

// Разрешаем вводить только цифры в поле размера ставки
$('#betValue').keypress(function(event){
    if (event.which < 48 || event.which > 57) return false;
});

// Коэффициент ставки
function go(coefficient) {
  	$('#btnBet').val("Coeff. " + coefficient + " Place BET");
  	$('#coefficient').val(coefficient);
}

// Нажатие на кнопку "Place Bet"
function bet() {
	$('#btnBet').val("Place BET");
	var coeff = $('#coefficient').val();
	if (coeff != null && coeff != "") {
		$('#coefficient').val("");
		newWindow("bet.html"); return false;
	} else {
		alert("Please, chose the event and outcome!");
    }
}