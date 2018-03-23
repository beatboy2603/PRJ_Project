/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var lastClicked = null;
$(document).ready(function () {
    $(".fileContainer").click(function () {
        $(".fileContainer").css("background-color", "white");
        $(".fileContainer .fileButtonDiv").css("visibility", "hidden");
        $(".fileContainer").css("box-shadow", "0 8px 6px -3px white");
        var id = $(this).attr('id');
        if (id != lastClicked) {
            $("#" + id).css("background-color", "#6495ED");
            $("#" + id + " .fileButtonDiv").css("visibility", "visible");
            $("#" + id).css("box-shadow", "0 8px 6px -3px grey");
            lastClicked = id;
        } else {
            $("#" + id).css("background-color", "#white");
            $("#" + id + " .fileButtonDiv").css("visibility", "hidden");
            $("#" + id).css("box-shadow", "0 8px 6px -3px white");
            lastClicked = null;
        }
    });
});

$(document).ready(function () {
    $(".deleteButton").click(function () {
        $("#confirmDelete").css("visibility", "visible");
        var html = "<p>Confirm delete " + $(this).find('.fileName').attr('value') + "</p> <br/> ";
        html += "<div style='display:flex;flex-direction:row;justify-content:space-around;'>\n\
            <form class='deleteElement' action='./Delete' method='GET'>\n\
             <input type='hidden' name='fId' value='" + $(this).find('.fileId').attr('value') + "'/>\n\
 <input type='submit' value='Yes'/>\n\
 </form> ";
        html += "<form class='deleteElement'> <input id='no' type='submit' value='No'/> </form> </div>";
        $("#confirmDelete").html(html);
    });
});

$(document).ready(function () {
    $("#no").click(function () {
        $("#confirmDelete").css("visibility", "hidden");
    });
});