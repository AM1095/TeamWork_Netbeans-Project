/*
Log in service

When the user logs in to the system, the account 
information sent is checked for validity by this
service. If the account exists, the user is 
redirected to his dashboard, else they are 
redirected to an error page.
    
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


public class log_in_service extends HttpServlet{
    
    //get the database resource here
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response
    ) throws IOException, ServletException {
        
        String username;
        String password;
        String first_name;
        String last_name;
        String email;
        int user_id;
        Connection con = null;
        ResultSet rs = null;
        boolean error;
        String error_message;
        String username_no_spaces;
        String password_no_spaces;
        HttpSession session;
        String redirect_page;
        String connected; //this will be used to identify if there is someone already connected
        
        error = false;
        error_message = "no-error";
        session = request.getSession();
        connected = (String)session.getAttribute("connected");
        
        // a new user tries to log in to the system ================================================
        if(connected == null){
            session = request.getSession();
            session.setMaxInactiveInterval(172800); //2 days inactive max
            
            //Since the session is new, we handle the request 
            username = request.getParameter("username");
            password = request.getParameter("password");
            first_name = "unset";
            last_name = "unset"; 
            email = "unset";
            user_id = -1;
            username_no_spaces = username.replaceAll("//s","");
            password_no_spaces = password.replaceAll("//s","");

            //if i didn't get empty fields
            if(!(username_no_spaces.equals("") && password_no_spaces.equals("")) ){ 
                try {
                    con = dataSrc.getConnection();
                    rs = query_manager.getLogInData(con,username, password);
                    //if the account exists
                    if(rs.next()){
                        first_name = rs.getString("first_name");
                        last_name = rs.getString("last_name");
                        user_id = rs.getInt("user_id");
                        email = rs.getString("email");
                        query_manager.connectUser( con , user_id );
                    }
                    else{
                        error = true;
                        error_message = "Wrong Username Or Password";
                    }

                } catch (SQLException e) {
                    System.err.println("Error at log in" + e);
                    error = true;
                    error_message = "sqlexception : " + e;

                }finally{
                        try {
                            if(rs != null) rs.close();
                            if(con != null) con.close();
                        } catch (SQLException e) {
                            System.err.println("Error at closing connection" + e);
                            error = true;
                            error_message = "sqlexception 2 : " + e;
                        }
                } 
            }
            else{
                error = true;
                error_message = "Please fill all fields";
            }
            
            //in case of an error during request handling
            if(error){
                redirect_page = "login_screen_error.jsp";
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
            //end of handling the request ===============================================
        }
        //a user is still loged in
        else{
            redirect_page = "dashboard.jsp";
        }
        //redirect the user after the request is handled =============================================
        response.sendRedirect(redirect_page);
    }  
}
