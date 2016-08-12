$(function() {
	$('.globe').on('click', function(){
		$('.icons').slideToggle();
	});
});

// Идентифицируем элемент HTML, для которого необходимо инициализировать виджет "Bootstrap datetimepicker"
$(function () {
	try {
  		$('#datetimepicker').datetimepicker({pickTime:false, language: 'ru'});
	} catch(de) {
		console.log(de)
	}
});

// Открываем страницу в новом окне
function newWindow(e) {
	var h = 500,
		w = 600;
	window.open(e, 'Login', 'scrollbars=1,height='+Math.min(h, screen.availHeight)+
		',width='+Math.min(w, screen.availWidth)+',left='+Math.max(0, (screen.availWidth - w)/2)+
		',top='+Math.max(0, (screen.availHeight - h)/2));
}

// Простая валидация формы регистрации (все ли поля формы заполнены)
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
	if ($("#password").val() != $("#confirmPassword").val()) {
		alert($("#passwordValid").val());
        return false;
	}
	if (!$("#agree").prop("checked")) { 
        alert($("#checkboxValid").val());
        return false;
	}
});


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