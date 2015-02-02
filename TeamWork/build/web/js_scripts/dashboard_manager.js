$(document).ready(function(){
    
    var userID = $("#project_history").text();
    var winWidth;
    var winHeight;
    
    $("#projects_tab").show();
    $("#account_settings_tab").hide();
    $("#project_create_tab").hide();
    $("#invitations_tab").hide();
    $("#tutorial_tab").hide();

    $("#projects_btn").removeClass("block_link");
    $("#projects_btn").addClass("block_link_selected");

    $("#ac_settings_btn").removeClass("block_link_selected");
    $("#ac_settings_btn").addClass("block_link");

    $("#pr_create_btn").removeClass("block_link_selected");
    $("#pr_create_btn").addClass("block_link");

    $("#invitations_btn").removeClass("block_link_selected");
    $("#invitations_btn").addClass("block_link");
        
    $("#tutorial_btn").removeClass("block_link_selected");
    $("#tutorial_btn").addClass("block_link");
    
    //initialize values
    function initialize(){
        winWidth = $(window).width();
        winHeight = $(window).height();

        $("#body").width(winWidth);
        $("#body").height(winHeight);
        $("#header").width(winWidth);

        $("#projects_tab").width(winWidth - 250);
        $("#project_create_tab").width(winWidth - 250);
        $(".hide_scroll").width(winWidth - 220);
        $("#article_scroll").width(winWidth);
        $("#tutorial_article").width(winWidth - 330);
        //handle different screen sizes
        if(winWidth > 1400){
            $("#manager_projects_board").width(winWidth * 0.35);
            $("#member_projects_board").width(winWidth * 0.35);
            $("#welcome_text_container").width(winWidth - 380);
        }
        else{
            $("#manager_projects_board").width(500);
            $("#member_projects_board").width(500);
            $("#welcome_text_container").width(500);
        }
        if(winHeight > 880){
            $("#options").height(winHeight);
            $("#projects_tab").height(winHeight - 100);
            $("#tutorial_tab").height(winHeight - 100);
            $("#project_create_tab").height(winHeight - 100);
            $("#manager_projects_board").height(winHeight - 320);
            $("#member_projects_board").height(winHeight - 320);
            $("#project_history").height(winHeight - 400);
            $("#joined_project_history").height(winHeight - 400);
        }
        else{
            $("#options").height(900);
            $("#projects_tab").height(780);
            $("#tutorial_tab").height(780);
            $("#project_create_tab").height(780);
            $("#manager_projects_board").height(500);
            $("#member_projects_board").height(500);
            $("#project_history").height(420);
            $("#joined_project_history").height(420);
        } 
    }
    
    initialize();
    
    $(window).resize(function (){
        initialize();
    });
    
    // AJAX =============================================================================================
    function getInvitations(){
    
        $.ajax({
            type : "GET",
            data:{ action:'get_invitations', user_type:'manager'},
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#invitations_history").empty();
                        $("#invitations_history").append(data);
                        console.log("projects request success");
                        console.log("the data is: ");
                        console.log(data);
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    getInvitations();
    
    function getProjects(uid){
        $("#project_history").empty();
        $.trim(uid);
        $.ajax({
            type : "GET",
            data:{ user_id: uid, action:'select_user_projects'},
            url: "/TeamWork/manage_projects",
            success: function(data){
                        $("#project_history").append(data);
                        console.log("projects request success");
                        console.log("the data is: ");
                        console.log(data);
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    function getJoinedProjects(uid){
        $("#joined_project_history").empty();
        $.trim(uid);
        $.ajax({
            type : "GET",
            data:{ user_id: uid, action:'select_joined_projects'},
            url: "/TeamWork/manage_projects",
            success: function(data){
                        $("#joined_project_history").append(data);
                        console.log("joined projects request success");
                        console.log("the data is: ");
                        console.log(data);
                    }  ,
            fail: function(){
                    console.log("error in getting joined projects");
            }  
                    
        });
    }
    
    function newProject(uid,pr_name,pr_decription){
        $.trim(uid);
        $.trim(pr_name);
        
        $.ajax({
            type : "POST",
            data:{ user_id: uid, action:'new_project', name:pr_name, description:pr_decription },
            url: "/TeamWork/manage_projects",
            success: function(data){
                    console.log(data);
            }  ,
            fail: function(){
                    console.log("error in sending create project request");
            }  
                    
        });
    }
    
    function openProject(proj_id){
        $.ajax({
            type : "GET",
            url: "/TeamWork/open_project",
            data:{ project_id: proj_id, action:'get_user_project'},
            success: function(page){
                    console.log("request_successful");
                    $(location).attr('href',page);
            }  ,
            fail: function(){
                    console.log("error in sending create project request");
            }  
        });
    }
    
    function openJoinedProject(proj_id){
        $.ajax({
            type : "GET",
            url: "/TeamWork/open_project",
            data:{ project_id: proj_id, action:'get_joined_project'},
            success: function(page){
                    console.log("request_successful");
                    $(location).attr('href',page);
            }  ,
            fail: function(){
                    console.log("error in sending create project request");
            }  
        });
    }
    
    function deleteProject(proj_id){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_projects",
            data:{ project_id: proj_id, action:'delete_project'},
            success: function(){
                    console.log("request_successful");
            }  ,
            fail: function(){
                    console.log("error in sending create project request");
            }  
        });
        
        getProjects(userID);
    }
    
    function unjoinProject(proj_id){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_projects",
            data:{ project_id: proj_id, user_id: userID, action:'unjoin_project'},
            success: function(){
                    console.log("request_successful");
            }  ,
            fail: function(){
                    console.log("error in sending create project request");
            }  
        });
        
        getJoinedProjects(userID);
    }
    
    function updateFirstName(name){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_accounts",
            data:{action:"update_first_name", user_id:userID, first_name:name},
            success: function(data){
                    console.log(data);
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  ,
            fail: function(){
                    console.log("error in update request");
            }  
        });
    }
    
    function updateLastName(name){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_accounts",
            data:{action:"update_last_name", user_id:userID, last_name:name},
            success: function(data){
                    console.log(data);
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  ,
            fail: function(){
                    console.log("error in update request");
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  
        });
    }
    
    function updateUsername(name){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_accounts",
            data:{action:"update_username", user_id:userID, username:name},
            success: function(data){
                    console.log(data);
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  ,
            fail: function(){
                    console.log("error in update request");
            }  
        });
    }
    
    function updateEmail(mail){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_accounts",
            data:{action:"update_email", user_id:userID, email:mail},
            success: function(data){
                    console.log(data);
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  ,
            fail: function(){
                    console.log("error in update request");
            }  
        });
    }
    
    function updatePassword(pwd){
        $.ajax({
            type : "POST",
            url: "/TeamWork/manage_accounts",
            data:{action:"update_password", user_id:userID, password:pwd},
            success: function(data){
                    console.log(data);
                    $("#ajax_message").empty();
                    $("#ajax_message").append(data + "<br/>Reload the page to see changes");
                    $("#ajax_dialogue").dialog('open');
            }  ,
            fail: function(){
                    console.log("error in update request");
            }  
        });
    }
    
    //this delay is for getting the projects after a project creation
    function refreshAfterDelay(){
        getProjects(userID);
    }
    setTimeout(refreshAfterDelay,1000);
    
    getProjects(userID);
    getJoinedProjects(userID);
    
    //on_click ================================================================================================
    /*$("#log_out_btn").click(function (){
       log_out(); 
    });*/

    $("#change_first_name_dialogue").dialog({ autoOpen: false });
    $("#change_last_name_dialogue").dialog({ autoOpen: false });
    $("#change_username_dialogue").dialog({ autoOpen: false });
    $("#change_email_dialogue").dialog({ autoOpen: false });
    $("#change_password_dialogue").dialog({ autoOpen: false });
    $("#ajax_dialogue").dialog({ autoOpen: false });

    $("#changeFirstName_btn").click(function (){
        $("#change_first_name_dialogue").dialog('open');
    });
    
    $("#changeLastName_btn").click(function (){
        $("#change_last_name_dialogue").dialog('open');
    });
    
    $("#changeUsername_btn").click(function (){
        $("#change_username_dialogue").dialog('open');
    });
    
    $("#changeEmail_btn").click(function (){
        $("#change_email_dialogue").dialog('open');
    });
    
    $("#changePassword_btn").click(function (){
        $("#change_password_dialogue").dialog('open');
    });

    $(document).on('click', ".open_mp_button", function() {
        var proj_id = $(this).parent().attr("id");
        openProject(proj_id);
    });
    
    $(document).on('click', ".open_jp_button", function() {
        var proj_id = $(this).parent().attr("id");
        openJoinedProject(proj_id);
    });
    
    $(document).on('click', ".delete_mp_button", function() {
        var proj_id = $(this).parent().attr("id");
        deleteProject(proj_id);
    });
    
    $(document).on('click', ".leave_jp_button", function() {
        var proj_id = $(this).parent().attr("id");
        unjoinProject(proj_id);
    });
    
    $("#submit_fname_btn").click(function (){
       var name = $("#first_name_text").val();
       $("#change_first_name_dialogue").dialog('close');
       updateFirstName(name);
    });
    $("#submit_lname_btn").click(function (){
       var name = $("#last_name_text").val();
       $("#change_last_name_dialogue").dialog('close');
       updateLastName(name);
    });
    $("#submit_email_btn").click(function (){
       var email = $("#email_text").val();
       $("#change_email_dialogue").dialog('close');
       updateEmail(email);
    });
    $("#submit_username_btn").click(function (){
       var name = $("#username_text").val();
       $("#change_username_dialogue").dialog('close');
       updateUsername(name);
    });
    $("#submit_password_btn").click(function (){
       var pwd = $("#password_text").val();
       $("#change_password_dialogue").dialog('close');
       updatePassword(pwd);
    });
    
    //new project_button 
    $("#n_proj_btn").click(function (){
        var name = $("#n_proj_name").val();
        var description = $("#n_proj_description").val();
        
        newProject(userID,name,description);
        $("#projects_btn").click();
    });
    
    $("#projects_btn").click(function(){
     
        getProjects(userID);
        getJoinedProjects(userID);
        
        $("#projects_tab").show();
        $("#account_settings_tab").hide();
        $("#project_create_tab").hide();
        $("#invitations_tab").hide();
        $("#tutorial_tab").hide();
        
        $("#projects_btn").removeClass("block_link");
        $("#projects_btn").addClass("block_link_selected");
        
        $("#ac_settings_btn").removeClass("block_link_selected");
        $("#ac_settings_btn").addClass("block_link");
        
        $("#pr_create_btn").removeClass("block_link_selected");
        $("#pr_create_btn").addClass("block_link");
        
        $("#invitations_btn").removeClass("block_link_selected");
        $("#invitations_btn").addClass("block_link");
        
        $("#tutorial_btn").removeClass("block_link_selected");
        $("#tutorial_btn").addClass("block_link");
    });
    
    $("#ac_settings_btn").click(function(){
        $("#projects_tab").hide();
        $("#account_settings_tab").show();
        $("#project_create_tab").hide();
        $("#invitations_tab").hide();
        $("#tutorial_tab").hide();
        
        $("#projects_btn").removeClass("block_link_selected");
        $("#projects_btn").addClass("block_link");
        
        $("#ac_settings_btn").removeClass("block_link");
        $("#ac_settings_btn").addClass("block_link_selected");
        
        $("#pr_create_btn").removeClass("block_link_selected");
        $("#pr_create_btn").addClass("block_link");
        
        $("#invitations_btn").removeClass("block_link_selected");
        $("#invitations_btn").addClass("block_link");
        
        $("#tutorial_btn").removeClass("block_link_selected");
        $("#tutorial_btn").addClass("block_link");
    });
    
    $("#pr_create_btn").click(function(){
        $("#projects_tab").hide();
        $("#account_settings_tab").hide();
        $("#project_create_tab").show();
        $("#invitations_tab").hide();
        $("#tutorial_tab").hide();
        
        $("#projects_btn").removeClass("block_link_selected");
        $("#projects_btn").addClass("block_link");
        
        $("#ac_settings_btn").removeClass("block_link_selected");
        $("#ac_settings_btn").addClass("block_link");
        
        $("#pr_create_btn").removeClass("block_link");
        $("#pr_create_btn").addClass("block_link_selected");
        
        $("#invitations_btn").removeClass("block_link_selected");
        $("#invitations_btn").addClass("block_link");
        
        $("#tutorial_btn").removeClass("block_link_selected");
        $("#tutorial_btn").addClass("block_link");
    });

    $("#invitations_btn").click(function(){
        $("#projects_tab").hide();
        $("#account_settings_tab").hide();
        $("#project_create_tab").hide();
        $("#invitations_tab").show();
        $("#tutorial_tab").hide();
        
        $("#projects_btn").removeClass("block_link_selected");
        $("#projects_btn").addClass("block_link");
        
        $("#ac_settings_btn").removeClass("block_link_selected");
        $("#ac_settings_btn").addClass("block_link");
        
        $("#pr_create_btn").removeClass("block_link_selected");
        $("#pr_create_btn").addClass("block_link");
        
        $("#invitations_btn").removeClass("block_link");
        $("#invitations_btn").addClass("block_link_selected");
        
        $("#tutorial_btn").removeClass("block_link_selected");
        $("#tutorial_btn").addClass("block_link");
        
        getInvitations();
        
    });
    
    
    $("#tutorial_btn").click(function(){
        $("#projects_tab").hide();
        $("#account_settings_tab").hide();
        $("#project_create_tab").hide();
        $("#invitations_tab").hide();
        $("#tutorial_tab").show();
        
        $("#projects_btn").removeClass("block_link_selected");
        $("#projects_btn").addClass("block_link");
        
        $("#ac_settings_btn").removeClass("block_link_selected");
        $("#ac_settings_btn").addClass("block_link");
        
        $("#pr_create_btn").removeClass("block_link_selected");
        $("#pr_create_btn").addClass("block_link");
        
        $("#invitations_btn").removeClass("block_link_selected");
        $("#invitations_btn").addClass("block_link");
        
        $("#tutorial_btn").removeClass("block_link");
        $("#tutorial_btn").addClass("block_link_selected");
    });
    
});