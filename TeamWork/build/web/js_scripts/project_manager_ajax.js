$(document).ready(function(){
    
    $("#ajax_dialog").dialog({ autoOpen: false });
   
    function createMilestone(title,text,ecpt){
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'create_milestone', name:title, description:text, estimated_cpt:ecpt },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    function deleteMilestone(m_id){
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'delete_milestone', milestone_id:m_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function startMilestone(m_id){
        var date = new Date();
        var d_day = date.getDay();
        var d_month = date.getMonth();
        var d_year = date.getYear();
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'start_milestone', milestone_id:m_id, day:d_day, month:d_month , year:d_year },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function reachMilestone(m_id){
        var date = new Date();
        var d_day = date.getDay();
        var d_month = date.getMonth();
        var d_year = date.getYear();
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'reach_milestone', milestone_id:m_id, day:d_day, month:d_month , year:d_year },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error ");
            }  
                    
        });
    }
    
    function createTask(t_name,t_description,t_day,t_month,t_year,t_user){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'create_assignment', name:t_name,
                description:t_description, day:t_day, month:t_month , year:t_year, username:t_user },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function deleteTask(t_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'delete_assignment', assignment_id : t_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error ");
            }  
                    
        });
    }
    
    function createIssue(i_name, i_description){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'create_issue', name:i_name, description:i_description },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function resolveIssue(i_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'resolve_issue',issue_id:i_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function createNote(n_text){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'create_note', text:n_text },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function deleteNote(n_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'delete_note', note_id:n_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function sendInvitation(rcv_id){
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'invite_member', receiver_id:rcv_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function kickMember(m_id){
        $.ajax({
            type : "POST",
            data:{ user_type:'manager', action:'delete_member', member_id:m_id},
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getMilestones(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_milestones' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#milestone_history_tab").empty();
                        $("#milestone_history_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getPendingAssignments(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_pending_assignments' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#pending_as_history_tab").empty();
                        $("#pending_as_history_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getFinishedAssignments(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_finished_assignments' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#completed_as_history_tab").empty();
                        $("#completed_as_history_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getResolvedIssues(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_resolved_issues' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#resolved_issues_tab").empty();
                        $("#resolved_issues_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getUnresolvedIssues(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_unresolved_issues' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#unresolved_issues_tab").empty();
                        $("#unresolved_issues_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getNotes(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_notes' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#my_notes_tab").empty();
                        $("#my_notes_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    function getMembers(){
        $.ajax({
            type : "GET",
            data:{ user_type:'manager', action:'get_members' },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#members_tab").empty();
                        $("#members_tab").append(data);
                    }  ,
            fail: function(){
                    console.log("error");
            }  
                    
        });
    }
    
    getMilestones();
    getPendingAssignments();
    getFinishedAssignments();
    getResolvedIssues();
    getUnresolvedIssues();
    getNotes();
    getMembers();
    
    //listeners for ajax buttons=========================================================
    $(document).on('click', ".ml_delete_button", function() {
        var m_id = $(this).parent().attr("id");
        deleteMilestone(m_id);
    });
    
    $(document).on('click', ".ml_start_button", function() {
        var m_id = $(this).parent().attr("id");
        startMilestone(m_id);
    });
    
    $(document).on('click', ".ml_reach_button", function() {
        var m_id = $(this).parent().attr("id");
        reachMilestone(m_id);
    });
    
    $(document).on('click', ".as_delete_button", function() {
        var t_id = $(this).parent().attr("id");
        deleteTask(t_id);
    });
    
    $(document).on('click', ".is_resolve_button", function() {
        var i_id = $(this).parent().attr("id");
        resolveIssue(i_id);
    });
    
    
    $(document).on('click', ".delete_note_button", function() {
        var n_id = $(this).parent().attr("id");
        deleteNote(n_id);
    });
    
    $(document).on('click', ".kick_member_button", function() {
        var m_id = $(this).parent().attr("id");
        kickMember(m_id);
    });
    
    $("#new_milestone_btn").click(function(){
        var title = $("#milestone_name").val();
        var text = $("#milestone_description").val();
        var ecpt = $("#milestone_est").val();
        
        createMilestone(title,text,ecpt);
    });
    
    $("#new_assignment_btn").click(function(){
        var t_name = $("#assignment_name").val();
        var t_description = $("#assignment_description").val();
        var t_day = $("#as_day").val();
        var t_month = $("#as_month").val();
        var t_year = $("#as_year").val();
        var t_user = $("#as_username").val();
        
        createTask(t_name,t_description,t_day,t_month,t_year,t_user);
    });
    
   $("#new_issue_btn").click(function(){
      var i_name = $("#issue_name").val();
      var i_description = $("#issue_description").val();
      
      createIssue(i_name, i_description);
   });
    
   $("#save_note_btn").click(function(){
     var n_text =  $("#notes_description").val();
     
     createNote(n_text);
   });
   
   $("#send_invite_btn").click(function(){
      var rcv_id = $("#mem_username").val();
      
      sendInvitation(rcv_id); 
   });
   
   //update panels ==========================================
   $("#milestone_history_btn").click(function(){
       getMilestones();
   });
   
   $("#pending_as_history_btn").click(function(){
       getPendingAssignments();
   });
   
   $("#completed_as_history_btn").click(function(){
       getFinishedAssignments();
   });
   
   $("#unresolved_issues_btn").click(function(){
       getUnresolvedIssues();
   });
   
   $("#resolved_issues_btn").click(function(){
       getResolvedIssues();
   });
   
   $("#my_notes_btn").click(function(){
       getNotes();
   });
   
   $("#members_btn").click(function(){
       getMembers();
   });
   
});