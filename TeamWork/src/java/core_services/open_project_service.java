/*
It will handle the ajax requests to open
the project page. It will send back a text
containing the link to the page after setting
the correct session attributes first
*/
package core_services;

import core_models.query_manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.sql.DataSource;


public class open_project_service extends HttpServlet{
    
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Boolean error = false;
        int user_id = -1;
        int project_id =-1;
        String project_id_str;
        String redirect_page;
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String action;
        
        redirect_page = "/TeamWork/log_out";
        
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        
        HttpSession session = request.getSession(false);
        
        //there is no session
        if(session == null){
            redirect_page = "/TeamWork/login_screen.jsp";
        }
        else{
            project_id = Integer.parseInt( request.getParameter("project_id") );
            action = request.getParameter("action");
            user_id = Integer.parseInt( (String)session.getAttribute("user_id") );
            
            try{
                con = dataSrc.getConnection();
                rs = query_manager.getProjectInfo( con , project_id );
                
                if(action.equals("get_user_project")){
                    if(rs.next ()){
                        project_id_str = Integer.toString(project_id);
                        session.setAttribute("project_id",project_id_str);
                        session.setAttribute("project_name",rs.getString ("name"));
                        session.setAttribute("project_members",rs.getString ("members"));
                        rs2 = query_manager.getUserInfo( con , user_id );
                        if(rs2.next()){
                            session.setAttribute("project_manager",rs2.getString ("first_name") + " " + rs2.getString ("last_name"));
                        }
                    } 
                    redirect_page = "/TeamWork/project_page_manager.jsp";
                }
                else if(action.equals("get_joined_project")){
                    if(rs.next ()){
                        project_id_str = Integer.toString(project_id);
                        session.setAttribute("project_id",project_id_str);
                        session.setAttribute("project_name",rs.getString ("name"));
                        session.setAttribute("project_members",rs.getString ("members"));
                        rs2 = query_manager.getUserInfo( con , Integer.parseInt( rs.getString("manager") ) );
                        if(rs2.next()){
                            session.setAttribute("project_manager",rs2.getString ("first_name") + " " + rs2.getString ("last_name"));
                        }
                    } 
                    redirect_page = "/TeamWork/project_page_member.jsp";
                }
            }catch (SQLException e) {
                System.err.println("Error at log in" + e);
                session.setAttribute("error_message","sql ecxeption 1 : " + e);
                redirect_page = "/TeamWork/error_page.jsp";
            }finally{
                    try {
                        if(rs != null) rs.close();
                        if(con != null) con.close();
                    } catch (SQLException e) {
                        System.err.println("Error at closing connection" + e);
                        session.setAttribute("error_message","sql ecxeption 2 : " + e);
                        redirect_page = "/TeamWork/error_page.jsp";
                    }
            } 
        }
        
        //send back the redirect link
        writer.println(redirect_page);
    }
    
}
