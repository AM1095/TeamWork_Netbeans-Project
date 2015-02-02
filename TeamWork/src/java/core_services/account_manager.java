/* 
This servlet will handle only ajax related requests
about accounts
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


public class account_manager extends HttpServlet{
    
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response
    ) throws IOException, ServletException {
        
        String action;
        String first_name,first_name_no_spaces;
        String last_name,last_name_no_spaces;
        String username,username_no_spaces;
        String password,password_no_spaces;
        String confirm_password;
        String email,email_no_spaces;
        int user_id;
        boolean error;
        String error_message;
        Connection con;
        ResultSet rs;
        
        action = request.getParameter("action");
        
        first_name = last_name = username = password = email = confirm_password = "empty";
        user_id = -1;
        error = false;
        error_message = "no_error";
        con = null;
        rs = null;        
        
        response.setContentType ("text/plain");
        PrintWriter writer = response.getWriter();
       
        HttpSession session = request.getSession(false);
        //handle request ====================================================================
        if(session != null){
            try{
                con = dataSrc.getConnection();


                if(action.equals("delete_account")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    query_manager.deleteUser(con, user_id);
                }
                else if(action.equals("update_first_name")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    first_name = request.getParameter("first_name");
                    query_manager.updateFirstName(con, first_name, user_id);
                    session.setAttribute("first_name",first_name);
                }    
                else if(action.equals("update_last_name")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    last_name = request.getParameter("last_name");
                    query_manager.updateLastName(con, last_name, user_id);
                    session.setAttribute("last_name",last_name);
                }
                else if(action.equals("update_email")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    email = request.getParameter("email");
                    query_manager.updateEmail(con, email, user_id);
                    session.setAttribute("email",email);
                }
                else if(action.equals("update_username")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    username = request.getParameter("username");

                    //check if an account with this username already exists
                    rs = query_manager.getUserByUsername(con, username);
                    if(!rs.next()){
                        query_manager.updateUsername(con, username, user_id);
                        session.setAttribute("username",username);
                    }
                    else{
                        error = true;
                        error_message = "This username aleady exists";
                    }
                }
                else if(action.equals("update_password")){
                    user_id = Integer.parseInt(request.getParameter("user_id"));
                    password = request.getParameter("password");
                    query_manager.updatePassword(con, password, user_id);
                }
                else{
                    error = true;
                    error_message = "No Action Specified";
                }
            } catch (SQLException e) {
                System.err.println("Error at log in" + e);
                error = true;
                error_message = "sql exception : " + e;
            } finally{
                    try {
                        if(con != null) con.close();
                    } catch (SQLException e) {
                            System.err.println("Error at closing connection" + e);
                            error = true;
                            error_message = "sql exception 2 : " + e;
                    }
            } 
        }
        else{
            error = true;
            error_message = "no session";
        }
        //end of handling request 
        if(error){
           writer.println("Error : " + error_message);
        }
        else{
           writer.println("Request completed successfully");
        }
        
    }//end of do post
    
}
