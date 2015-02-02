/*
This servlet will handle only the creation of a new account
*/
package core_services;

import core_models.query_manager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.annotation.Resource;

public class new_account_service extends HttpServlet{
    
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        
         
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
        HttpSession session;
        String connected;
        String redirect_page;
        
        action = request.getParameter("action");
        
        first_name = last_name = username = password = email = confirm_password = "empty";
        user_id = -1;
        error = false;
        error_message = "no_error";
        con = null;
        rs = null;   
        
        session = request.getSession();
        connected = (String)session.getAttribute("connected");
        redirect_page = "login_screen.jsp";
        
        // a new user tries to create account ================================================
        if(connected == null){
            try{
                con = dataSrc.getConnection();

                //the user wants to create a new account
                if(action.equals("create_account")){
                    first_name = request.getParameter("first_name");
                    last_name  = request.getParameter("last_name");
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    confirm_password = request.getParameter("confirm_password");
                    email = request.getParameter("email");

                    //let's get rid of all the spaces
                    first_name_no_spaces = first_name.replaceAll("//s","");
                    last_name_no_spaces = last_name.replaceAll("//s","");
                    username_no_spaces = username.replaceAll("//s","");
                    password_no_spaces = password.replaceAll("//s","");
                    email_no_spaces = email.replaceAll("//s","");

                    //if i didn't get empty fields
                    if(
                        !first_name_no_spaces.equals("") &&
                        !last_name_no_spaces.equals("") &&
                        !username_no_spaces.equals("") &&
                        !password_no_spaces.equals("") &&
                        !email_no_spaces.equals("") ) {

                        //check if an account with this username already exists
                        rs = query_manager.getUserByUsername(con, username);
                        if(!rs.next()){
                            //check if passwords match
                            if(password.equals(confirm_password)){
                                user_id = query_manager.createUser(con,first_name,last_name,username,password,email);
                            }
                            else{
                                error = true;
                                error_message = "Passwords don't match";
                            }
                        }
                        else{
                            error = true;
                            error_message = "An account with this username already exists";
                        }
                    }
                    else{
                        error = true;
                        error_message = "Please fill all the fields";
                    }      
                }
                else{
                    error = true;
                    error_message = "No action specified";
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

            if(error){
                redirect_page = "account_screen_error.jsp";
                session.setAttribute("error_message",error_message);
            }
            else{
                session.setAttribute("session_exists","true");
                session.setAttribute("first_name", first_name);
                session.setAttribute("last_name", last_name);
                session.setAttribute("email", email);
                session.setAttribute("username", username);
                session.setAttribute("user_id",Integer.toString(user_id));
                session.setAttribute("error_message","no-error");
                session.setAttribute("connected","true");
                
                redirect_page = "dashboard.jsp";
            }
        }
        //a user tries to create account while the session is active
        else{
            redirect_page = "dashboard.jsp";
        }
        //redirect the user after the request is handled =============================================
        response.sendRedirect(redirect_page);
        
    }
} 
    
