$(document).ready(function() {
    $(".oncl").click(function() {
        $("#tab1").attr("class", "nav-link active");
        $("#tab2").attr("class", "nav-link");
        $("#tab1").text($(this).text());
         $.getJSON("/storeroom","name="+$(this).text(), function(data) {
            var str;
            for (i in data) {
                str += "<tr>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td><p>" + data[i].size1 + "</p><br><p>" + data[i].size2 + "</p></td>" +
                    "<td> <button class=\"btn btn-primary btn-sm\">get</button> </td>" +
                    "</tr>";
            }
            $("#tbody").html(str);
        });
    });
    $("#tab1").click(function() {
        $("#tab1").attr("class", "nav-link active");
        $("#tab2").attr("class", "nav-link");
    });
    $("#tab2").click(function() {
        $.getJSON("/backpack", function(data) {
            var str;
            for (i in data) {
                str += "<tr>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td><p>" + data[i].size1 + "</p><br><p>" + data[i].size2 + "</p></td>" +
                    "<td> <button class=\"btn btn-primary btn-sm\">get</button> </td>" +
                    "</tr>";
            }
            $("#tbody").html(str);
        });
        $("#tab2").attr("class", "nav-link active");
        $("#tab1").attr("class", "nav-link");
    });
});