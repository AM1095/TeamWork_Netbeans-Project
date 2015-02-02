/*
It will handle the ajax requests about projects
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


public class project_manager extends HttpServlet{
    
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String action;
        String user_id;
         
        action = request.getParameter("action");
        user_id = request.getParameter("user_id");
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        try {
            con = dataSrc.getConnection();

            if(action.equals("select_user_projects")){
                
                rs = query_manager.getManagerProjects (con,user_id);
                
                while(rs.next()){
                    out.println("<div class='project_box' id='"+ rs.getString("project_id") +"'>"
                            + "<p>Project : " + rs.getString("name") +"</p>"
                            + "<p>Members: "+ rs.getString("members") +"</p>"
                            + "<p>Description : "
                            + "<br/>" + rs.getString("description") + "</p>"
                            + "<button class='open_mp_button'>Open</button>"
                            + "<button class='delete_mp_button'>Delete</button>"
                            + "</div>");
                }
                
            }
            else if(action.equals("select_joined_projects")){
                
                rs = query_manager.getJoinedProjectIDs(con ,user_id );
                
                while(rs.next()){
                    rs2 = query_manager.getJoinedProject( con , rs.getString("project_id") );
                    if(rs2.next ()){
                        out.println("<div class='project_box' id='"+ rs.getString("project_id") +"' >"
                            + "<p>Project : " + rs2.getString("name") +"</p>"
                            + "<p>Members: "+ rs2.getString("members") +"</p>"
                            + "<p>Description : "
                            + "<br/>" + rs2.getString("description") + "</p>"
                            + "<button class='open_jp_button'>Open</button>"
                            + "<button class='leave_jp_button'>Leave</button>"
                            + "</div>");
                    }
                }
                
            }
            else{   
                out.println("action failed");
            }
        } catch (SQLException e) {
            System.err.println("Error at log in" + e);
            out.println("sql exception : " + e);
           
        }finally{
                try {
                    if(rs != null) rs.close();
                    if(con != null) con.close();
                } catch (SQLException e) {
                    System.err.println("Error at closing connection" + e);
                    out.println("sql exception 2 : " + e);
                }
        } 
        
        out.flush();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

        Connection con = null;
        ResultSet rs = null;
        Boolean error = false;
        String error_message = "no-error";
        String name = "empty";
        String manager = "empty";
        String description = "empty";
        String action = "no-action";
        int user_id;
        int project_id;
        int manager_id;
        
        action = request.getParameter("action");
        response.setContentType ("text/plain");
        PrintWriter writer = response.getWriter();
        
        try {
            con = dataSrc.getConnection();
            
            if(action.equals("new_project")){
                manager = request.getParameter("user_id");
                manager_id = Integer.parseInt(manager);
                description = request.getParameter("description");
                name = request.getParameter("name");
                
                query_manager.createProject(con, name, manager_id, description);
            }
            else if(action.equals("delete_project")){
                project_id = Integer.parseInt( request.getParameter("project_id") );
                query_manager.deleteProject(con, project_id);
            }
            else if(action.equals("unjoin_project")){
                project_id = Integer.parseInt( request.getParameter("project_id") );
                user_id = Integer.parseInt( request.getParameter("user_id") );
                query_manager.deleteMember(con , user_id , project_id );
            }
            else{
                error = true;
                error_message = "no action specified";
            }
           
        } catch (SQLException e) {
            System.err.println("Error at log in" + e);
            error = true;
            error_message = "sql_exception : " + e;
           
        }finally{
                try {
                    if(rs != null) rs.close();
                    if(con != null) con.close();
                } catch (SQLException e) {
                    System.err.println("Error at closing connection" + e);
                    error = true;
                    error_message = "sql_exception 2 : " + e;
                }
        } 
        
        if(error){
            writer.println("error -> " + error_message);
        }
        else{
            writer.println("action successful");
        }
        
    }
    
}
