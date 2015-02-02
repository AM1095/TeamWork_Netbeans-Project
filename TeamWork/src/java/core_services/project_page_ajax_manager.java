/*
This servlet will handle all the ajax requests
from the project pages
*/
package core_services;

import core_models.query_manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.annotation.Resource;

public class project_page_ajax_manager extends HttpServlet{
    
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String action;
        int user_id;
        Boolean error = false;
        String error_message = "no-error";
        HttpSession session;
        String user_type; //if it is manager or member
        String started,finished,resolved,reached,milestone_start_date,milestone_reached_date,deadline_text,connected,username;
        Integer project_id;
        
        action = request.getParameter("action");
        user_type = request.getParameter("user_type");
        
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        
        session = request.getSession(false);
        
        if(session != null){
            try {
                project_id = Integer.parseInt( (String)session.getAttribute("project_id") ); 
                user_id = Integer.parseInt( (String)session.getAttribute("user_id") );
                con = dataSrc.getConnection();
                
                if(action.equals("get_milestones")){
                    rs = query_manager.getMilestones( con , project_id );
                    while(rs.next ()){
                        if(rs.getString("started").equals("0") ){
                            started = "no";
                        }
                        else{
                            started = "yes";
                        }
                        if(rs.getString("reached").equals("0") ){
                            reached = "no";
                        }
                        else{
                            reached = "yes";
                        }
                        milestone_start_date = query_manager.getMilestoneDateText( con , Integer.parseInt( rs.getString("start_date")) );
                        milestone_reached_date = query_manager.getMilestoneDateText( con , Integer.parseInt( rs.getString("reached_date")) );
                        
                        if(user_type.equals("manager")){
                            writer.println("<div class='history_box' id='"+ rs.getString("milestone_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Estimated time for completion : "+ rs.getString("estimated_cpt") +" days</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Reached : "+ reached +"</p>"
                                    + "<p>Start date : "+ milestone_start_date +" </p>"
                                    + "<p>Reached date : "+ milestone_reached_date +"</p>"
                                    + "<button class='ml_delete_button small_button'>Delete</button>"
                                    + "<button class='ml_start_button small_button'>Start</button>"
                                    + "<button class='ml_reach_button small_button'>Reach</button>"
                                    + "</div>");
                        }
                        else{
                            writer.println("<div class='history_box' id='"+ rs.getString("milestone_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Estimated time for completion : "+ rs.getString("estimated_cpt") +" days</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Reached : "+ reached +"</p>"
                                    + "<p>Start date : "+ milestone_start_date +" </p>"
                                    + "<p>Reached date : "+ milestone_reached_date +"</p>"
                                    + "</div>");
                        }    
                    }
                }
                else if(action.equals("get_pending_assignments")){
                     rs = query_manager.getPendingAssignments(con , project_id);
                     
                     while(rs.next()){
                         if(rs.getString("started").equals("0") ){
                            started = "no";
                        }
                        else{
                            started = "yes";
                        }
                        if(rs.getString("finished").equals("0") ){
                            finished = "no";
                        }
                        else{
                            finished = "yes";
                        }
                        
                        deadline_text = query_manager.getDeadlineDateText( con , Integer.parseInt( rs.getString("deadline_id")) );
                        username = query_manager.getUsername( con , Integer.parseInt( rs.getString("user_id") ) );
                            
                        if(user_type.equals("manager")){
                            writer.println("<div class='history_box' id='"+ rs.getString("assignment_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Assigned to : "+ username +" </p>"
                                    + "<p>Deadline : "+ deadline_text +"</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Finished : "+ finished+"</p>"
                                    + "<button class='as_delete_button small_button'>Delete</button>"
                                    + "</div>");
                        }
                        else{
                            writer.println("<div class='history_box' id='"+ rs.getString("assignment_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Assigned to : "+ username +" </p>"
                                    + "<p>Deadline : "+ deadline_text +"</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Finished : "+ finished+"</p>"
                                    + "<button class='as_start_button small_button'>Start</button>"
                                    + "<button class='as_finish_button small_button'>Finish</button>"
                                    + "</div>");
                        }    
                     }
                }
                else if(action.equals("get_finished_assignments")){
                    rs = query_manager.getFinishedAssignments(con , project_id);
                     
                     while(rs.next()){
                         if(rs.getString("started").equals("0") ){
                            started = "no";
                        }
                        else{
                            started = "yes";
                        }
                        if(rs.getString("finished").equals("0") ){
                            finished = "no";
                        }
                        else{
                            finished = "yes";
                        }
                        
                        deadline_text = query_manager.getDeadlineDateText( con , Integer.parseInt( rs.getString("deadline_id")) );
                        username = query_manager.getUsername( con , Integer.parseInt( rs.getString("user_id") ) );
                        
                        if(user_type.equals("manager")){
                            writer.println("<div class='history_box' id='"+ rs.getString("assignment_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Assigned to : "+ username +" </p>"
                                    + "<p>Deadline : "+ deadline_text +"</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Finished : "+ finished+"</p>"
                                    + "<button class='as_delete_button small_button'>Delete</button>"
                                    + "</div>");
                        }
                        else{
                            writer.println("<div class='history_box' id='"+ rs.getString("assignment_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Assigned to : "+ rs.getString("user_id") +" </p>"
                                    + "<p>Deadline : "+ deadline_text +"</p>"
                                    + "<p>Started : "+ started +"</p>"
                                    + "<p>Finished : "+ finished+"</p>"
                                    + "</div>");
                        }    
                     }
                }
                else if(action.equals("get_resolved_issues")){
                    rs = query_manager.getResolvedIssues( con , project_id );
                    
                    while(rs.next()){
                        writer.println("<div class='history_box' id='"+ rs.getString("issue_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Posted by : "+ rs.getString("user_id") +" </p>"
                                    + "<p>Resolved : yes </p>"
                                    + "</div>");
                    }
                }
                else if(action.equals("get_unresolved_issues")){
                    rs = query_manager.getUnresolvedIssues( con , project_id );
                    
                    while(rs.next()){
                        writer.println("<div class='history_box' id='"+ rs.getString("issue_id") +"'>"
                                    + "<p>Name : "+ rs.getString("name") +" <p>"
                                    + "<p>Description : <br/>"+ rs.getString("description") +"</p>"
                                    + "<p>Posted by : "+ rs.getString("user_id") +" </p>"
                                    + "<p>Resolved : no </p>"
                                    + "<button class='is_resolve_button small_button'>Resolved</button>"
                                    + "</div>");
                    }
                }
                else if(action.equals("get_notes")){
                    rs = query_manager.getNotes( con , user_id , project_id );
                    while(rs.next()){
                        writer.println("<div class='history_box' id='"+ rs.getString("note_id") +"'>"
                                    + "<p>"+ rs.getString("text") +"<p>"
                                    + "<button class='delete_note_button small_button'>Delete</button>"
                                    + "</div>");
                    }
                }
                else if(action.equals("get_members")){
                    rs = query_manager.getMembersIds( con , project_id );
                    while(rs.next()){
                        rs2 = query_manager.getUserInfo( con , Integer.parseInt( rs.getString("user_id") ) );
                        while(rs2.next() ){
                            if(rs2.getString("connected").equals("0")){
                                connected = "no";
                            }
                            else{
                                connected = "yes";
                            }
                            if(user_type.equals("manager")){
                                writer.println("<div class='history_box' id='"+ rs.getString("user_id") +"'>"
                                        + "<p> First Name : "+ rs2.getString("first_name") +"<p>"
                                        + "<p> Last Name : "+ rs2.getString("last_name") +"<p>"
                                        + "<p> Username : "+ rs2.getString("username") +"<p>"
                                        + "<p> Connected : "+ connected +"<p>"
                                        + "<button class='kick_member_button small_button'>Remove</button>"
                                        + "</div>");
                            }
                            else{
                                writer.println("<div class='history_box' id='"+ rs.getString("user_id") +"'>"
                                        + "<p> First Name : "+ rs2.getString("first_name") +"<p>"
                                        + "<p> Last Name : "+ rs2.getString("last_name") +"<p>"
                                        + "<p> Username : "+ rs2.getString("username") +"<p>"
                                        + "<p> Connected : "+ connected +"<p>"
                                        + "</div>");
                            }
                        }
                    }
                }
                else if(action.equals("get_invitations")){
                    rs = query_manager.getInvitations( con , user_id );
                    String senderName;
                    
                    while(rs.next()){
                        senderName = query_manager.getUsername( con , Integer.parseInt( rs.getString("sender") ) );
                        rs2 = query_manager.getProjectInfo ( con ,Integer.parseInt( rs.getString("project_id") ));
                        
                        if(rs2.next ()){
                            writer.println("<div class='project_box' id='"+ rs.getString("invitation_id") +"' >"
                                    + "<p>You have an invitation from : "+ senderName +" to join the project : "+ rs2.getString("name") +" <p>"
                                    + "<button class='accept_inv_btn small_button'></button>"
                                    + "</div>");
                        }
                    }       
                }
                else{
                    error = true;
                    error_message = "no action specified";
                }

            } catch (SQLException e) {
                System.err.println("Error at log in" + e);
                error = true;
                error_message = "sql exception 1 : " + e;
            }finally{
                    try {
                        if(rs != null) rs.close();
                        if(con != null) con.close();
                    } catch (SQLException e) {
                        System.err.println("Error at closing connection" + e);
                        error = true;
                        error_message = "sql exception 2 : " + e;
                    }
            } 

            if(error){
                writer.println(error_message);
            } 
            
        }
        else{
                writer.println ("no session found");
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        Connection con = null;
        ResultSet rs = null;
        HttpSession session;
        int project_id,user_id;
        Boolean error = false;
        String error_message = "no-error";
        String action;
        String name,description;
        int estimated_cpt;
        
        session = request.getSession(false);
        
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        
        if(session != null){
            try {
                    action = request.getParameter("action");
                    user_id = Integer.parseInt( (String)session.getAttribute("user_id") );
                    project_id = Integer.parseInt( (String)session.getAttribute("project_id") ); 
                    con = dataSrc.getConnection();
                    
                    if(action.equals("create_milestone")){
                        name = request.getParameter("name");
                        description = request.getParameter("description");
                        estimated_cpt = Integer.parseInt( request.getParameter("estimated_cpt") );
                        
                        query_manager.createMilestone ( con , project_id , name, description, estimated_cpt );
                    }
                    else if(action.equals("delete_milestone")){
                        query_manager.deleteMilestone ( con , Integer.parseInt(request.getParameter("milestone_id")) );
                    }
                    else if(action.equals("start_milestone")){
                        query_manager.startMilestone( con , Integer.parseInt(request.getParameter("milestone_id")) , 
                                project_id ,
                                Integer.parseInt(request.getParameter("day")), 
                                Integer.parseInt(request.getParameter("month")),
                                Integer.parseInt(request.getParameter("year")));
                    }
                    else if(action.equals("reach_milestone")){
                        query_manager.reachMilestone ( con , 
                                Integer.parseInt(request.getParameter("milestone_id")),
                                project_id , 
                                Integer.parseInt(request.getParameter("day")),
                                Integer.parseInt(request.getParameter("month")) , 
                                Integer.parseInt(request.getParameter("year")));
                    }
                    else if(action.equals("create_assignment")){
                        rs = query_manager.getUserByUsername( con , request.getParameter("username") );
                        if(rs.next()){
                            query_manager.createAssignment( con , request.getParameter("name"),
                                    request.getParameter("description") , 
                                    Integer.parseInt(request.getParameter("day")), 
                                    Integer.parseInt(request.getParameter("month")),
                                    Integer.parseInt(request.getParameter("year")),
                                    Integer.parseInt(rs.getString("user_id")),
                                    project_id
                                );
                        }
                    }
                    else if(action.equals("delete_assignment")){
                        query_manager.deleteAssignment( con ,Integer.parseInt(request.getParameter("assignment_id")));
                    }
                    else if(action.equals("start_assignment")){
                        query_manager.startAssignment( con , Integer.parseInt(request.getParameter("assignment_id")));
                    }
                    else if(action.equals("finish_assignment")){
                        query_manager.finishAssignment( con , Integer.parseInt(request.getParameter("assignment_id")));
                    }
                    else if(action.equals("create_issue")){
                        query_manager.createIssue( con , 
                                request.getParameter("name") , 
                                request.getParameter("description") , user_id , project_id );
                    }
                    else if(action.equals("resolve_issue")){
                        query_manager.resolveIssue( con , Integer.parseInt(request.getParameter("issue_id")) );
                    }
                    else if(action.equals("create_note")){
                        query_manager.createNote( con , user_id , project_id , request.getParameter("text"));
                    }
                    else if(action.equals("delete_note")){
                        query_manager.deleteNote( con ,Integer.parseInt(request.getParameter("note_id")) );
                    }
                    else if(action.equals("invite_member")){
                        query_manager.sendInvitation(con , user_id , 
                                Integer.parseInt(request.getParameter("receiver_id")),
                                project_id);
                    }
                    else if(action.equals("delete_member")){
                        query_manager.deleteMember( con , Integer.parseInt(request.getParameter("member_id")) , 
                                project_id );
                    }
                    else{
                        error = true;
                        error_message = "no action specified";
                    }
                    
            } catch (SQLException e) {
                    System.err.println("Error at log in" + e);
                    error = true;
                    error_message = "sql exception 1 : " + e;
                }finally{
                        try {
                            if(rs != null) rs.close();
                            if(con != null) con.close();
                        } catch (SQLException e) {
                            System.err.println("Error at closing connection" + e);
                            error = true;
                            error_message = "sql exception 2 : " + e;
                        }
                } 

                if(error){
                    writer.println(error_message);
                }   
                else{
                    writer.println("request succesful");
                }
            }
        else{
                writer.println ("no session found");
        }
        
    }
}
    
