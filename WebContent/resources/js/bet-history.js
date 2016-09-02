$(document).ready(function() {
			$("#" + $("#currentPage").val() + "_li").addClass("active");
		});
		
		$(".page-link").click(function() {
			$("#currentPage").val($(this).text());
			$("#betHistoryForm").submit();
		});