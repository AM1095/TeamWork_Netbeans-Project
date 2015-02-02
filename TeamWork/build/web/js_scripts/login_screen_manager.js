$(document).ready(function(){
    
    //initialize content ===================
    var windowWidth = $(window).width();
    var windowHeight = $(window).height();
    var footerHeight = 135;
    
    if(windowHeight < 800 ){
        $("#main_container").removeClass("big_padding");
        $("#main_container").addClass("small_padding");
        footerHeight = -30;
    }
    
    var main_screen_width = windowWidth;
    var main_screen_height = windowHeight - footerHeight;
    
    if(windowHeight < 800 ){
        $("#main_container").height(900);
    }
    else{
        $("#main_container").height(main_screen_height);
    }
    
    var sess_exists = $("#session_exists").text();
    var state = "true";
    $("#session_exists").remove();
    if(sess_exists == state){
        alert("Please log out first. Another user has an active session on this client. You will not be able to view your own pages otherwise");
    }
    
    //on window resize =======================================
    $(window).resize(function (){
        windowWidth = $(window).width();
        windowHeight = $(window).height();
        main_screen_height = windowHeight - footerHeight;
        if(windowHeight < 800 ){
            $("#main_container").height(900);
        }
        else{
            $("#main_container").height(main_screen_height);
        }
        
        if(windowHeight < 800 ){
            $("#main_container").removeClass("big_padding");
            $("#main_container").addClass("small_padding");
        }
        else{
            $("#main_container").removeClass("small_padding");
            $("#main_container").addClass("big_padding");
        }
        
    
    });
    
});


