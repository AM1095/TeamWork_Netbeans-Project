$(document).ready( function(){
    //initialize
    $("#user_settings").hide();
    $("#project_exp_list").hide();
    
    //functions
    $("#u_settings_expand").click( function(){
       
        $("#user_settings").slideToggle("fast");
    });

  
});