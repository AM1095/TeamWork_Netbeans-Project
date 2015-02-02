package core_services;

import core_models.query_manager;
import java.io.IOException;
import java.sql.Connection;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.sql.SQLException; 

public class logout_service extends HttpServlet{
    
    //get the database resource here
    @Resource(name="jdbc/teamwork_database")
    private DataSource dataSrc;
    
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response
    ) throws IOException, ServletException {
        HttpSession session;
        Connection con = null;
        
        session = request.getSession(false);
        
        if(session != null){
            try{
                con = dataSrc.getConnection();
                query_manager.disconnectUser( con ,Integer.parseInt((String)session.getAttribute("user_id")) );
            }catch(SQLException e){
                System.out.println("Error in log out : " + e);
            }
            finally{
                try{
                    if(con != null){
                        con.close();
                    }
                }catch(SQLException e){
                    System.out.println("Error 2 in log out : " + e);
                }
            }
            
            session.invalidate ();
        }
        
        response.sendRedirect("login_screen.jsp");
        
    }
}
