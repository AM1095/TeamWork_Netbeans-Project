$(document).ready(function(){
    
    var winWidth;
    var winHeight;
    
    function initialize(){
        winWidth = $(window).width();
        winHeight = $(window).height();
        $("#subHeaderContainer").width(winWidth);
        $("#subHeaderContainer").height(winHeight - 45);
        var subWidth = $("#subHeaderContainer").width();
        var lp_width = $("#left_panel").width();
        
       $("#monitors_screen").width(subWidth - lp_width);
       $("#monitors_screen").height(winHeight - 45);
       $("#left_panel").height(winHeight - 45 );
       $("#hide_scrollbar").height(winHeight - 45);
       $("#hide_scrollbar").width(subWidth - lp_width + 20); 
       
    } 
    
    initialize();
    
    $("#issues_monitor").hide();
    $("#notes_monitor").hide();
    $("#members_monitor").hide();
   
    $("#milestone_history_tab").show();
    $("#create_milestone_tab").hide();
    $("#pending_as_history_tab").show();
    $("#completed_as_history_tab").hide();
    $("#create_assignment_tab").hide();
    $("#unresolved_issues_tab").show();
    $("#resolved_issues_tab").hide();
    $("#post_issue_tab").hide();
    $("#my_notes_tab").show();
    $("#create_note_tab").hide();
    $("#members_tab").show();
    $("#invite_tab").hide();
        
    $("#members_btn").removeClass("monitor_tab_first");
    $("#members_btn").addClass("monitor_tab_first_selected");
        
    $("#invite_btn").removeClass("monitor_tab_selected");
    $("#invite_btn").addClass("monitor_tab");
        
    $("#my_notes_btn").removeClass("monitor_tab_first");
    $("#my_notes_btn").addClass("monitor_tab_first_selected");
        
    $("#create_note_btn").removeClass("monitor_tab_selected");
    $("#create_note_btn").addClass("monitor_tab");    
    
    $("#unresolved_issues_btn").removeClass("monitor_tab_first");
    $("#unresolved_issues_btn").addClass("monitor_tab_first_selected");
    
    $("#resolved_issues_btn").removeClass("monitor_tab_selected");
    $("#resolved_issues_btn").addClass("monitor_tab");
    
    $("#post_issue_btn").removeClass("monitor_tab_selected");
    $("#post_issue_btn").addClass("monitor_tab");
        
    $("#milestone_history_btn").removeClass("monitor_tab_first");
    $("#milestone_history_btn").addClass("monitor_tab_first_selected");
        
    $("#create_milestone_btn").removeClass("monitor_tab_selected");
    $("#create_milestone_btn").addClass("monitor_tab");
    
    $("#pending_as_history_btn").removeClass("monitor_tab_first");
    $("#pending_as_history_btn").addClass("monitor_tab_first_selected");
    
    $("#completed_as_history_btn").removeClass("monitor_tab_selected");
    $("#completed_as_history_btn").addClass("monitor_tab");
    
    $("#create_assignment_btn").removeClass("monitor_tab_selected");
    $("#create_assignment_btn").addClass("monitor_tab");
    
    
    $(window).resize(function (){
        initialize();
    });
   
    //click listenters ============================================================
    $("#open_milestones_btn").click(function(){
       $("#milestones_monitor").toggle();
    });
    
    $("#open_assignments_btn").click(function(){
       $("#assignments_monitor").toggle();
    });
    
    $("#open_issues_btn").click(function(){
       $("#issues_monitor").toggle();
    });
    
    $("#open_notes_btn").click(function(){
       $("#notes_monitor").toggle();
    });
    
    $("#open_members_btn").click(function(){
       $("#members_monitor").toggle();
    });
    
    $("#open_ideas_btn").click(function(){
       $("#ideas_monitor").toggle();
    });
    
    $("#milestone_history_btn").click(function(){
        $("#milestone_history_tab").show();
        $("#create_milestone_tab").hide();
        
        $("#milestone_history_btn").removeClass("monitor_tab_first");
        $("#milestone_history_btn").addClass("monitor_tab_first_selected");
        
        $("#create_milestone_btn").removeClass("monitor_tab_selected");
        $("#create_milestone_btn").addClass("monitor_tab");
        
    });
    
    $("#create_milestone_btn").click(function(){
        $("#milestone_history_tab").hide();
        $("#create_milestone_tab").show();
        
        $("#milestone_history_btn").removeClass("monitor_tab_first_selected");
        $("#milestone_history_btn").addClass("monitor_tab_first");
        
        $("#create_milestone_btn").removeClass("monitor_tab");
        $("#create_milestone_btn").addClass("monitor_tab_selected");
        
    });
    
    $("#pending_as_history_btn").click(function(){
        $("#pending_as_history_tab").show();
        $("#completed_as_history_tab").hide();
        $("#create_assignment_tab").hide();
        
        $("#pending_as_history_btn").removeClass("monitor_tab_first");
        $("#pending_as_history_btn").addClass("monitor_tab_first_selected");
    
        $("#completed_as_history_btn").removeClass("monitor_tab_selected");
        $("#completed_as_history_btn").addClass("monitor_tab");
    
        $("#create_assignment_btn").removeClass("monitor_tab_selected");
        $("#create_assignment_btn").addClass("monitor_tab");
        
    });
    
    $("#completed_as_history_btn").click(function(){
        $("#pending_as_history_tab").hide();
        $("#completed_as_history_tab").show();
        $("#create_assignment_tab").hide();
        
        $("#pending_as_history_btn").removeClass("monitor_tab_first_selected");
        $("#pending_as_history_btn").addClass("monitor_tab_first");
    
        $("#completed_as_history_btn").removeClass("monitor_tab");
        $("#completed_as_history_btn").addClass("monitor_tab_selected");
    
        $("#create_assignment_btn").removeClass("monitor_tab_selected");
        $("#create_assignment_btn").addClass("monitor_tab");
        
    });
    
    $("#create_assignment_btn").click(function(){
        $("#pending_as_history_tab").hide();
        $("#completed_as_history_tab").hide();
        $("#create_assignment_tab").show();
        
        $("#pending_as_history_btn").removeClass("monitor_tab_first_selected");
        $("#pending_as_history_btn").addClass("monitor_tab_first");
    
        $("#completed_as_history_btn").removeClass("monitor_tab_selected");
        $("#completed_as_history_btn").addClass("monitor_tab");
    
        $("#create_assignment_btn").removeClass("monitor_tab");
        $("#create_assignment_btn").addClass("monitor_tab_selected");
        
    });
    
    $("#unresolved_issues_btn").click(function(){
        $("#unresolved_issues_tab").show();
        $("#resolved_issues_tab").hide();
        $("#post_issue_tab").hide();
        
        $("#unresolved_issues_btn").removeClass("monitor_tab_first");
        $("#unresolved_issues_btn").addClass("monitor_tab_first_selected");
    
        $("#resolved_issues_btn").removeClass("monitor_tab_selected");
        $("#resolved_issues_btn").addClass("monitor_tab");
    
        $("#post_issue_btn").removeClass("monitor_tab_selected");
        $("#post_issue_btn").addClass("monitor_tab");
        
    });
    
    $("#resolved_issues_btn").click(function(){
        $("#unresolved_issues_tab").hide();
        $("#resolved_issues_tab").show();
        $("#post_issue_tab").hide();
        
        $("#unresolved_issues_btn").removeClass("monitor_tab_first_selected");
        $("#unresolved_issues_btn").addClass("monitor_tab_first");
    
        $("#resolved_issues_btn").removeClass("monitor_tab");
        $("#resolved_issues_btn").addClass("monitor_tab_selected");
    
        $("#post_issue_btn").removeClass("monitor_tab_selected");
        $("#post_issue_btn").addClass("monitor_tab");
        
    });
    
    $("#post_issue_btn").click(function(){
        $("#unresolved_issues_tab").hide();
        $("#resolved_issues_tab").hide();
        $("#post_issue_tab").show();
        
        $("#unresolved_issues_btn").removeClass("monitor_tab_first_selected");
        $("#unresolved_issues_btn").addClass("monitor_tab_first");
    
        $("#resolved_issues_btn").removeClass("monitor_tab_selected");
        $("#resolved_issues_btn").addClass("monitor_tab");
    
        $("#post_issue_btn").removeClass("monitor_tab");
        $("#post_issue_btn").addClass("monitor_tab_selected");
        
    });
    
    $("#my_notes_btn").click(function(){
        $("#my_notes_tab").show();
        $("#create_note_tab").hide();
        
        $("#my_notes_btn").removeClass("monitor_tab_first");
        $("#my_notes_btn").addClass("monitor_tab_first_selected");
        
        $("#create_note_btn").removeClass("monitor_tab_selected");
        $("#create_note_btn").addClass("monitor_tab");
        
    });
    
    $("#create_note_btn").click(function(){
        $("#my_notes_tab").hide();
        $("#create_note_tab").show();
        
        $("#my_notes_btn").removeClass("monitor_tab_first_selected");
        $("#my_notes_btn").addClass("monitor_tab_first");
        
        $("#create_note_btn").removeClass("monitor_tab");
        $("#create_note_btn").addClass("monitor_tab_selected");    
    });
    
    $("#members_btn").click(function(){
        $("#members_tab").show();
        $("#invite_tab").hide();
        
        $("#members_btn").removeClass("monitor_tab_first");
        $("#members_btn").addClass("monitor_tab_first_selected");
        
        $("#invite_btn").removeClass("monitor_tab_selected");
        $("#invite_btn").addClass("monitor_tab");
        
    });
    
    $("#invite_btn").click(function(){
        $("#members_tab").hide();
        $("#invite_tab").show();
        
        $("#members_btn").removeClass("monitor_tab_first_selected");
        $("#members_btn").addClass("monitor_tab_first");
        
        $("#invite_btn").removeClass("monitor_tab");
        $("#invite_btn").addClass("monitor_tab_selected");
        
    });
    
});