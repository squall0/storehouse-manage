function tab1(data) {
	var str = data;
	// 空库处理
	$("#table").html(str);
}
function tab2(data) {
	var str = data;
	// 空库处理
	$("#table").html(data);
}
$(document).ready(function() {
	$(".oncl").click(function() {
		$("#tab1").attr("class", "nav-link active");
		$("#tab2").attr("class", "nav-link");
		$("#tab1").text($(this).text());
		$("#tab1").attr("data-room_id", $(this).attr("data-room_id"));
		$.get("/storeroom", "name=" + $(this).text(), function(data) {
			tab1(data);
		});
	});
	$("#tab1").click(function() {
		$("#tab1").attr("class", "nav-link active");
		$("#tab2").attr("class", "nav-link");
		$.get("/storeroom", "name=" + $(this).text(), function(data) {
			tab1(data);
		});
	});
	$("#tab2").click(function() {
		$("#tab2").attr("class", "nav-link active");
		$("#tab1").attr("class", "nav-link");
		$.get("/backpack", function(data) {
			tab2(data);
		});
	});
	// $( ".col-3" ).hide( "slide", 1000 );
});
function take(r) {
	$("#" + r).remove();
	$.post("/take", {
		"id" : r
	}, function(status) {
		if (status == "success") {
			// $("#" + r).remove(); 获取不到参数r
		}
	});
}
// 失败提示失败并刷新页面
function bring(r) {
	$("#" + r).remove();
	var t = $("#tab1").attr("data-room_id");
	$.post("/bring", {
		"id" : r,
		"roomid" : t
	}, function(status) {
		if (status == "success") {
			// $("#" + r).remove();
		}
	});
}