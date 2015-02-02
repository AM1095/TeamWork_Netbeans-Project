/*
This class contains all the possible 
queries that the system uses, so that
the servlets don't have to deal with
query syntax.
*/

package core_models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class query_manager {
        
   
    //returns a prepared statement to query the database for log in information
    public static ResultSet getLogInData(Connection con, String username, String password)
    throws SQLException{
        String query;
        PreparedStatement statement;
        ResultSet rs;
        
        query = "SELECT first_name, last_name, user_id, email FROM users WHERE username = ? AND password = ? ;";
        statement = con.prepareStatement(query);
        statement.setString(1,username);
        statement.setString(2,password);
                
        rs = statement.executeQuery();
        
        return rs;
    }
    
    public static void createProject(Connection con, String name, int manager_id , String description)
    throws SQLException{
        
        String query;
        PreparedStatement statement;
        Statement prestatement;
        ResultSet pre_rs;
        int new_proj_id;
        
        //create the date object for the deadline
        query = "SELECT MAX(project_id) FROM project ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_proj_id = -1;
        if(pre_rs.next()){
            new_proj_id = pre_rs.getInt(1) + 1;
        }
        
        query = "INSERT INTO project (project_id, name, manager, description, members) ";
        query += "VALUES ( ? , ? , ? , ? , ?) ;";
        statement = con.prepareStatement(query);
        statement.setInt(1,new_proj_id);
        statement.setString(2,name);
        statement.setInt(3, manager_id);
        statement.setString(4,description);
        statement.setInt(5,0);
        
        statement.executeUpdate();
        
    }
    
    public static void deleteProject(Connection con, int project_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM project WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        st.executeUpdate();
    
    }
    
    public static void createAssignment
        (Connection con, String name, String description, int day, int month,
        int year, int user_id, int project_id )
        throws SQLException{
        
        String query;
        PreparedStatement statement;
        Statement prestatement;
        ResultSet pre_rs;
        int new_date_id;
        int new_task_id;
        
        //create the date object for the deadline
        query = "SELECT MAX(deadline_id) FROM deadline ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_date_id = -1;
        if(pre_rs.next()){
            new_date_id = pre_rs.getInt(1) + 1;
        }
        
        query = "SELECT MAX(assignment_id) FROM assignment ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_task_id = -1;
        if(pre_rs.next()){
            new_task_id = pre_rs.getInt(1) + 1;
        }

        query = "INSERT INTO deadline (deadline_id, assignment_id, project_id, day, month, year) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? ) ;";
        statement = con.prepareStatement(query);
        statement.setInt(1,new_date_id);
        statement.setInt(2,new_task_id);
        statement.setInt(3,project_id);
        statement.setInt(4,day);
        statement.setInt(5,month);
        statement.setInt(6,year);
        
        statement.executeUpdate(); 
                
        //create the assignment and pass the date_id for the deadline
        query = "INSERT INTO assignment (assignment_id, name, description, deadline_id, project_id, user_id, started, finished) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? , ? ) ;";
        statement = con.prepareStatement(query);
        statement.setInt(1,new_task_id);
        statement.setString(2,name);
        statement.setString(3,description);
        statement.setInt(4, new_date_id);
        statement.setInt(5,project_id);
        statement.setInt(6,user_id);
        statement.setInt(7,0);
        statement.setInt(8,0);
               
        statement.executeUpdate();
                
    }
        
    public static void deleteAssignment(Connection con, int assignment_id )
    throws SQLException{
        
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM assignment WHERE assignment_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,assignment_id);
        
        st.executeUpdate();
       
    }
    
    public static void startAssignment(Connection con, int assignment_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE assignment SET started = 1 WHERE assignment_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,assignment_id);
        
        st.executeUpdate();
    }
    
    public static void finishAssignment(Connection con, int assignment_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE assignment SET finished = 1 WHERE assignment_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,assignment_id);
        
        st.executeUpdate();
    }
    
    public static ResultSet getPendingAssignments(Connection con, int project_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT assignment_id, name, description, deadline_id, user_id, started, finished FROM assignment WHERE project_id = ? AND finished = 0 ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static ResultSet getFinishedAssignments(Connection con, int project_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT assignment_id, name, description, deadline_id, user_id, started, finished FROM assignment WHERE project_id = ? AND finished = 1 ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static void createIssue(Connection con, String name, String description, int user_id, int project_id)
    throws SQLException{
        
        PreparedStatement st;
        Statement pre_st;
        ResultSet rs;
        String query;
        int issue_id;
        
        query = "SELECT MAX(issue_id) FROM issue ;";
        pre_st = con.createStatement();
        rs = pre_st.executeQuery(query);
        issue_id = -1;
        if(rs.next()){
            issue_id = rs.getInt(1) + 1;
        }
        
        query = "INSERT INTO issue (issue_id, name, user_id, project_id, description, resolved) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? ) ;";
        st = con.prepareStatement(query);
        st.setInt(1,issue_id);
        st.setString(2,name);
        st.setInt(3,user_id);
        st.setInt(4,project_id);
        st.setString(5,description);
        st.setInt(6,0);
        
        st.executeUpdate();
        
    }
    
    public static void deleteIssue(Connection con, int issue_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM issue WHERE issue_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,issue_id);
        st.executeUpdate();
        
    }
    
    public static void resolveIssue(Connection con,int issue_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE issue SET resolved = 1 WHERE issue_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,issue_id);
        st.executeUpdate();
    }
    
    public static ResultSet getResolvedIssues(Connection con, int project_id) throws SQLException{
        PreparedStatement st;
        String query;
        ResultSet rs;
        
        query = "SELECT issue_id, name, user_id, description, resolved FROM issue WHERE project_id = ? AND resolved = 1 ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static ResultSet getUnresolvedIssues(Connection con, int project_id) throws SQLException{
        PreparedStatement st;
        String query;
        ResultSet rs;
        
        query = "SELECT issue_id, name, user_id, description, resolved FROM issue WHERE project_id = ? AND resolved = 0 ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static void createMember(Connection con, int user_id, int project_id) throws SQLException {
        PreparedStatement st;
        ResultSet rs;
        String query;
        int members = -1;
        
        //create the member
        query = "INSERT INTO member (user_id, project_id) VALUES ( ? , ? )  ;";
        st = con.prepareStatement(query);
        st.setInt(1,user_id);
        st.setInt(2,project_id);
        st.executeUpdate();
        
        //get the current project members
        query = "SELECT members FROM project WHERE project_id= ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        rs = st.executeQuery(query);
        
        if(rs.next()){
            members = Integer.parseInt( rs.getString("members") ) + 1;
        }
        //add the new project members
        query = "UPDATE project SET members = ? WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,members);
        st.setInt(2,project_id);
        st.executeUpdate();
     
    }
    
    public static void deleteMember(Connection con, int user_id, int project_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        int members = -1;
        
        query = "DELETE FROM member WHERE user_id = ? AND project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,user_id);
        st.setInt(2,project_id);
        st.executeUpdate();
        
        //get the current project members
        query = "SELECT members FROM project WHERE project_id= ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        rs = st.executeQuery(query);
        
        if(rs.next ()){
            members = Integer.parseInt( rs.getString("members") ) - 1;
        }
        //add the new project members
        query = "UPDATE project SET members = ? WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,members);
        st.setInt(2,project_id);
        st.executeUpdate();
      
    }
    
    public static ResultSet getMembersIds(Connection con, int project_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT user_id FROM member WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();

        return rs;
    }
    
    public static void createMilestone(Connection con, int project_id, String name, String description, int estimated_cpt)
            throws SQLException{
        String query;
        PreparedStatement statement;
        Statement prestatement;
        ResultSet pre_rs;
        int new_ml_id;
        
        query = "SELECT MAX(milestone_id) FROM milestone;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_ml_id = -1;
        if(pre_rs.next()){
            new_ml_id = pre_rs.getInt(1) + 1;
        }
        
         //create the milestone
        query = "INSERT INTO milestone (milestone_id, name, description, project_id, estimated_cpt, started, reached, start_date, reached_date) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ;";
        statement = con.prepareStatement(query);
        statement.setInt(1,new_ml_id);
        statement.setString(2,name);
        statement.setString(3,description);
        statement.setInt(4,project_id);
        statement.setInt(5,estimated_cpt);
        statement.setInt(6,0);
        statement.setInt(7,0);
        statement.setInt(8,0);
        statement.setInt(9,0);
        
        statement.executeUpdate();
       
    }
    
    public static void deleteMilestone(Connection con, int milestone_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM milestone WHERE milestone_id = ?";
        st = con.prepareStatement(query);
        st.setInt(1,milestone_id);
        st.executeUpdate();
     
    }
    
    public static void reachMilestone(Connection con, int milestone_id, int project_id, int day, int month, int year) throws SQLException{
        PreparedStatement st;
        ResultSet pre_rs;
        ResultSet rs;
        Statement prestatement;
        String query;
        int new_date_id;
        int rs_day,rs_month,rs_year;
        int dif;
        
        query = "UPDATE milestone SET reached = 1 WHERE milestone_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,milestone_id);
        st.executeUpdate();
        
        query = "SELECT MAX(milestone_date_id) FROM milestone_date ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_date_id = -1;
        if(pre_rs.next()){
            new_date_id = pre_rs.getInt(1) + 1;
        }

        query = "INSERT INTO milestone_date (milestone_date_id, project_id, milestone_id, day, month, year) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? ) ;";
        st = con.prepareStatement(query);
        st.setInt(1,new_date_id);
        st.setInt(2,project_id);
        st.setInt(3,milestone_id);
        st.setInt(4,day);
        st.setInt(5,month);
        st.setInt(6,year);
        
        st.executeUpdate(); 
        
        query = "UPDATE milestone SET reached_date = ? WHERE milestone_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,new_date_id);
        st.setInt(2,milestone_id);
        st.executeUpdate();
        
    }
    
    public static void startMilestone(Connection con, int milestone_id, int project_id, int day, int month, int year ) 
    throws SQLException{
        PreparedStatement st;
        ResultSet pre_rs;
        ResultSet rs;
        Statement prestatement;
        String query;
        int new_date_id;
        int rs_day,rs_month,rs_year;
        int dif;
        
        query = "UPDATE milestone SET started = 1 WHERE milestone_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,milestone_id);
        st.executeUpdate();
        
        query = "SELECT MAX(milestone_date_id) FROM milestone_date ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_date_id = -1;
        if(pre_rs.next()){
            new_date_id = pre_rs.getInt(1) + 1;
        }

        query = "INSERT INTO milestone_date (milestone_date_id, project_id, milestone_id, day, month, year) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? ) ;";
        st = con.prepareStatement(query);
        st.setInt(1,new_date_id);
        st.setInt(2,project_id);
        st.setInt(3,milestone_id);
        st.setInt(4,day);
        st.setInt(5,month);
        st.setInt(6,year);
        
        st.executeUpdate(); 
        
        query = "UPDATE milestone SET start_date = ? WHERE milestone_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,new_date_id);
        st.setInt(2,milestone_id);
        st.executeUpdate();
    }
    
    public static ResultSet getMilestones(Connection con, int project_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT milestone_id, name, description, estimated_cpt, started, reached, start_date, reached_date FROM milestone WHERE project_id= ?  ";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static int createUser(Connection con, 
            String first_name, String last_name, String username, String password,
            String email) throws SQLException{
        String query;
        PreparedStatement statement;
        Statement prestatement;
        ResultSet pre_rs;
        int new_user_id;
        
        //create the date object for the milestone
        query = "SELECT MAX(user_id) FROM users ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_user_id = -1;
        if(pre_rs.next()){
            new_user_id = pre_rs.getInt(1) + 1;
        }
        
        //create the user
        query = "INSERT INTO users (first_name, last_name, username, password, email, user_id, connected) ";
        query += "VALUES ( ? , ? , ? , ? , ? , ? , ? ) ;";
        statement = con.prepareStatement(query);
        statement.setString(1,first_name);
        statement.setString(2,last_name);
        statement.setString(3, username);
        statement.setString(4,password);
        statement.setString(5,email);
        statement.setInt(6,new_user_id);
        statement.setInt(7,1);
        statement.executeUpdate();
        
        pre_rs.close();
        prestatement.close();
        statement.close();
        
        return new_user_id;
    }
    
    public static void deleteUser(Connection con, int user_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM users WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
       
        st.executeUpdate();
    }
    
    public static void connectUser(Connection con, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET connected = 1 WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
       
        st.executeUpdate();
    }
    
    public static void disconnectUser(Connection con, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET connected = 0 WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
       
        st.executeUpdate();
    }
    
    public static ResultSet getUserInfo(Connection con, int user_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT first_name, last_name, username, email, connected FROM users WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,user_id);
        rs = st.executeQuery();
        
        return rs;        
    }
    
    public static ResultSet getProjectInfo(Connection con, int project_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT name, description, members, manager FROM project WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,project_id);
        rs = st.executeQuery();
        
        return rs;        
    }
    
    public static ResultSet getManagerProjects(Connection con, String manager)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT project_id, name, description, members FROM project WHERE manager = ? ;";
        st = con.prepareStatement(query);
        st.setString(1,manager);
        rs = st.executeQuery();
        
        return rs;        
    }
   
    public static ResultSet getJoinedProjectIDs(Connection con, String user)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT project_id FROM member WHERE user_id = ?";
        st = con.prepareStatement(query);
        st.setString(1,user);
        rs = st.executeQuery();

        return rs;        
    }
    
    public static ResultSet getJoinedProject(Connection con, String project_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT manager, name, description, members FROM project WHERE project_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1,project_id);
        rs = st.executeQuery();

        return rs;        
    }
    
    
    public static ResultSet getUserByUsername(Connection con, String username)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT first_name, last_name, email, user_id FROM users WHERE username = ? ;";
        st = con.prepareStatement(query);
        st.setString(1,username);
        rs = st.executeQuery();

        return rs;        
    }
    
    public static String getUsername(Connection con, int user_id)
    throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query,username;
        
        username = "not initialized";
        query = "SELECT username FROM users WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1,user_id);
        rs = st.executeQuery();
        if(rs.next ()){
            username = rs.getString("username");
        }
        
        return username;
    }
    
    public static void updateFirstName(Connection con, String first_name, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET first_name = ? WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1, first_name);
        st.setInt(2, user_id);
       
        st.executeUpdate();

    }
    
    public static void updateLastName(Connection con, String last_name, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET last_name = ? WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1, last_name);
        st.setInt(2, user_id);
       
        st.executeUpdate();

    }
    
    public static void updateEmail(Connection con, String email, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET email = ? WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1, email);
        st.setInt(2, user_id);
       
        st.executeUpdate();

    }
    
    public static void updateUsername(Connection con, String username, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET username = ? WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1, username);
        st.setInt(2, user_id);
       
        st.executeUpdate();

    }
    
    public static void updatePassword(Connection con, String password, int user_id)
    throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "UPDATE users SET password = ? WHERE user_id = ? ;";
        st = con.prepareStatement(query);
        st.setString(1, password);
        st.setInt(2, user_id);
       
        st.executeUpdate();

    }
    
    public static String getMilestoneDateText(Connection con, int date_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        String date_text;
        
        date_text = "no-date"; 
        
        query = "SELECT day, month, year FROM milestone_date WHERE milestone_date_id= ?  ";
        st = con.prepareStatement(query);
        st.setInt(1, date_id);
       
        rs = st.executeQuery();
        
        if(rs.next ()){
            date_text = rs.getString("day") + "/" + rs.getString("month") + "/" + rs.getString("year") ;
        }
        
        return date_text;
    }
    
    public static String getDeadlineDateText(Connection con, int date_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        String date_text;
        
        date_text = "no-date"; 
        
        query = "SELECT day, month, year FROM milestone_date WHERE milestone_date_id= ?  ";
        st = con.prepareStatement(query);
        st.setInt(1, date_id);
       
        rs = st.executeQuery();
        
        if(rs.next ()){
            date_text = rs.getString("day") + "/" + rs.getString("month") + "/" + rs.getString("year") ;
        }
        
        return date_text;
    }
    
    public static void createNote(Connection con, int user_id, int project_id, String text)
    throws SQLException{
        String query;
        PreparedStatement st;
        Statement prestatement;
        ResultSet pre_rs;
        int new_note_id;
        
        //create the date object for the milestone
        query = "SELECT MAX(note_id) FROM note ;";
        prestatement = con.createStatement();
        pre_rs = prestatement.executeQuery(query);
        new_note_id = -1;
        if(pre_rs.next()){
            new_note_id = pre_rs.getInt(1) + 1;
        }
        
        query = "INSERT INTO note ( user_id, project_id, note_id, text) VALUES ( ? , ? , ? , ? ) " ;
        
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
        st.setInt(2, project_id);
        st.setInt(3,new_note_id);
        st.setString(4,text);
        
        st.executeUpdate(); 
    }
    public static void deleteNote(Connection con, int note_id) throws SQLException{
        String query;
        PreparedStatement st;
        
        query = "DELETE FROM note WHERE note_id = ?";
        st = con.prepareStatement(query);
        st.setInt(1,note_id);
        st.executeUpdate();
    }
    
    public static ResultSet getNotes(Connection con, int user_id, int project_id) throws SQLException{
        PreparedStatement st;
        ResultSet rs;
        String query;
        
        query = "SELECT note_id, text FROM note WHERE user_id = ? AND project_id = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
        st.setInt(2, project_id);
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static void sendInvitation(Connection con, int sender_id, int receiver_id, int project_id )
    throws SQLException{
        PreparedStatement st;
        Statement prestatement;
        String query;
        ResultSet rs;
        int inv_id;
        
        query = "SELECT MAX(invitation_id) FROM invitation ;";
        prestatement = con.createStatement();
        rs = prestatement.executeQuery(query);
        inv_id = -1;
        if(rs.next()){
            inv_id = rs.getInt(1) + 1;
        }
        
        query = "INSERT INTO invitation (invitation_id, sender, receiver, project_id) VALUES ( ? , ? , ? , ? )  ;";
        st = con.prepareStatement(query);
        st.setInt(1, inv_id);
        st.setInt(2, sender_id);
        st.setInt(3, receiver_id);
        st.setInt(4, project_id);
        st.executeUpdate();
    }
    
    public static ResultSet getInvitations(Connection con, int user_id) throws SQLException{
        PreparedStatement st;
        String query;
        ResultSet rs;
        
        query = "SELECT invitation_id, sender, project_id FROM invitation WHERE receiver = ? ;";
        st = con.prepareStatement(query);
        st.setInt(1, user_id);
        rs = st.executeQuery();
        
        return rs;
    }
    
    public static void acceptInvitation(Connection con, int user_id, int project_id) throws SQLException{
        PreparedStatement st;
        String query;
        
        query = "DELETE FROM invitation WHERE invitation_id = ? ;";
        st = con.prepareStatement(query);
        st.executeUpdate();
        
        query = "INSERT INTO member (user_id, project_id) VALUES ( ? , ? ) ;";
        st = con.prepareStatement(query);
        st.setInt(1,user_id);
        st.setInt(2,project_id);
        st.executeUpdate();
        
    }
    
    
}
