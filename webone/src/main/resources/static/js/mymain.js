$(document).ready(function() {
    $(".oncl").click(function() {
        $("#tab1").attr("class", "nav-link active");
        $("#tab2").attr("class", "nav-link");
        $("#tab1").text($(this).text());
        $("#tab1").attr("data-room_id",$(this).attr("data-room_id"));
        $.getJSON("/storeroom", "name=" + $(this).text(), function(data) {
            var str;
            for (i in data) {
                str += "<tr id=" + data[i].id + ">" +
                    "<td>" + data[i].name + "</td>" +
                    "<td><p>" + data[i].size1 + "</p><br><p>" + data[i].size2 + "</p></td>" +
                    "<td> <button onclick=\"take(" + data[i].id + ")\" class=\"btn btn-primary btn-sm\" >拿取</button> </td>" +
                    "</tr>";
            }
            if (data.length == null) {
                str += "<tr><td>空</td><td></td><td></td></tr>";
            }
            $("#tbody").html(str);
        });
    });
    $("#tab1").click(function() {
        $("#tab1").attr("class", "nav-link active");
        $("#tab2").attr("class", "nav-link");
        $.getJSON("/storeroom", "name=" + $(this).text(), function(data) {
            var str;
            for (i in data) {
                str += "<tr id=" + data[i].id + ">" +
                    "<td>" + data[i].name + "</td>" +
                    "<td><p>" + data[i].size1 + "</p><br><p>" + data[i].size2 + "</p></td>" +
                    "<td> <button onclick=\"take(" + data[i].id + ")\" class=\"btn btn-primary btn-sm\" >拿取</button> </td>" +
                    "</tr>";
            }
            if (data.length <= 0) {
                str += "<tr><td>空</td><td></td><td></td></tr>";
            }
            $("#tbody").html(str);
        });
    });
    $("#tab2").click(function() {
        $.getJSON("/backpack", function(data) {
            var str;
            for (i in data) {
                str += "<tr id=" + data[i].id + ">" +
                    "<td>" + data[i].name + "</td>" +
                    "<td><p>" + data[i].size1 + "</p><br><p>" + data[i].size2 + "</p></td>" +
                    "<td> <button onclick=\"bring(" + data[i].id + ")\" class=\"btn btn-primary btn-sm\" >放回</button> </td>" +
                    "</tr>";
            }
            if (data.length <= 0) {
                str += "<tr><td>空</td><td></td><td></td></tr>";
            }
            $("#tbody").html(str);
        });
        $("#tab2").attr("class", "nav-link active");
        $("#tab1").attr("class", "nav-link");
    });
});

function take(r) {
    $("#" + r).remove();
    $.post("/take", { "id": r }, function(status) {
        if (status == "success") {
            // $("#" + r).remove(); 获取不到参数r
        }
    });
}
//失败提示失败并刷新页面
function bring(r) {
    $("#" + r).remove();
    var t=$("#tab1").attr("data-room_id");
    $.post("/bring", { "id": r,"roomid":t }, function(status) {
        if (status == "success") {
            // $("#" + r).remove();
        }
    });
}