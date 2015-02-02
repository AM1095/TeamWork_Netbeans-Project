$(document).ready(function(){
    $("#ajax_dialog").dialog({ autoOpen: false });
    
    function startTask(t_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'start_assignment', assignment_id : t_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function finishTask(t_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'finish_assignment', assignment_id : t_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function createIssue(i_name, i_description){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'create_issue', name:i_name, description:i_description },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function resolveIssue(i_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'resolve_issue',issue_id:i_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function createNote(n_text){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'create_note', text:n_text },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function deleteNote(n_id){
        
        $.ajax({
            type : "POST",
            data:{ user_type:'member', action:'delete_note', note_id:n_id },
            url: "/TeamWork/project_ajax",
            success: function(data){
                        $("#ajax_response_text").empty();
                        $("#ajax_response_text").append(data);
                        $("#ajax_dialog").dialog('open');
                    }  ,
            fail: function(){
                    console.log("error in getting projects");
            }  
                    
        });
    }
    
    function getMilestones(){
        $.ajax({
            type : "GET",
            data:{ user_type:'member', action:'get_milestones' },
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
            data:{ user_type:'member', action:'get_pending_assignments' },
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
            data:{ user_type:'member', action:'get_finished_assignments' },
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
            data:{ user_type:'member', action:'get_resolved_issues' },
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
            data:{ user_type:'member', action:'get_unresolved_issues' },
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
            data:{ user_type:'member', action:'get_notes' },
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
            data:{ user_type:'member', action:'get_members' },
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
    
    //click listeners ==================================================================
    $(document).on('click', ".as_start_button", function() {
        var t_id = $(this).parent().attr("id");
        startTask(t_id);
    });
    
    $(document).on('click', ".as_finish_button", function() {
        var t_id = $(this).parent().attr("id");
        finishTask(t_id);
    });
    
    $(document).on('click', ".is_resolve_button", function() {
        var i_id = $(this).parent().attr("id");
        resolveIssue(i_id);
    });
    
     $(document).on('click', ".new_issue_btn", function() {
      var i_name = $("#issue_name").val();
      var i_description = $("#issue_description").val();
      
      createIssue(i_name, i_description);
   });
    
   $(document).on('click', ".save_note_btn", function() {
     var n_text =  $("#notes_description").val();
     
     createNote(n_text);
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
