/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (http://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function() {
    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});

var isBusiness = false;
$(document).ready(function() {
    $('#biz').click(function() {
        if(isFinance){
            isFinance = false;
            isBusiness = true;
        }else{
            isBusiness = !isBusiness;
            $('#loginFormDiv').slideToggle("slow");
        }

        if(isBusiness){
            isFinance = false;
            $("#biz").css("background-color", "#555");
            $("#biz").css("border-radius", "10px");
            $("#biz").css("color", "white");
            $("#fin").css("color", "#337ab7");
            $("#fin").css("background-color", "white");
        }else{
            $("#biz").css("color", "#337ab7");
            $("#biz").css("background-color", "white");
        }
        
    });
});

var isFinance = false;
$(document).ready(function() {
    $('#fin').click(function() {
        if(isBusiness){
            isBusiness = false;
            $("#biz").css("color", "#337ab7");
            $("#biz").css("background-color", "white");
            isFinance = true;
        }else{
            isFinance = !isFinance;
            $('#loginFormDiv').slideToggle("slow");
        }
        if(isFinance){
            isBusiness = false;
            $("#fin").css("background-color", "#555");
            $("#fin").css("border-radius", "10px");
            $("#fin").css("color", "white");
            $("#biz").css("color", "#337ab7");
        }else{
            $("#fin").css("color", "#337ab7");
            $("#fin").css("background-color", "white");
        }
    });
});