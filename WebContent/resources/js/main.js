$(function() {
	$('.globe').on('click', function(){
		$('.icons').slideToggle();
	});
});

// Open page with totalizator conditions in new window
function newWindow(e) {
	var h = 500,
		w = 600;
	window.open(e, 'Login', 'scrollbars=1,height='+Math.min(h, screen.availHeight)+
		',width='+Math.min(w, screen.availWidth)+',left='+Math.max(0, (screen.availWidth - w)/2)+
		',top='+Math.max(0, (screen.availHeight - h)/2));
}

// Show/hide messages for logination modal window
$(function() {
	$("#span-login").hide();
	$("#span-password").hide();
});

// Simple logination modal window validation
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

// Hide <span> messages
function delSpan(span) {
	if (span == "login") { 
		$("#span-login").hide();
	}
	if (span == "password") { 
		$("#span-password").hide(); 
	}
}