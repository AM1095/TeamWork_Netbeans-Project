<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TeamWork</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="Css/project_page_style.css"  />
        <link rel="stylesheet" type="text/css" href="Css/jquery-ui.min.css" />
        <script src="jquery/jquery.js"></script>
        <script src="jquery/jquery-ui.min.js"></script>
        <script src="js_scripts/expand_menu.js"></script>
        <script src="js_scripts/project_manager_ajax.js"></script>
        <script src="js_scripts/project_page.js"></script>
    </head>
    <body>
        <header>
           <div id="inline_img_1"></div>
           <div id="inline_img_2"></div>
             <ul id="top_right_options">
                <li>
                    <a id ="u_settings_expand" class="settings_button" href="#">${first_name}</a>
                    <ul id="user_settings" class="expandable_list">
                        <li class="expandable_list"><a class="topRightSubLink" href="/TeamWork/dashboard.jsp">Dashboard</a></li>
                        <li class="expandable_list"><a class="topRightSubLink" href="/TeamWork/log_out">Log Out</a></li>
                    </ul>
                </li>
         <!--       <li><a class="notifications_button" href="#">Notifications</a></li>
                <li><a class="chat_button" href="#">Team Chat</a></li> -->
            </ul>
        </header>
        <div id="subHeaderContainer">
            <div id="left_panel">
                <div id="profile_small_screen">
                    <p class="profile_text">Project : ${project_name} <br/><br/> Manager : ${project_manager}<br/><br/></p>
                </div> 
                <ul id="left_panel_list">
                    <li><a href="#" class="left_panel_btn" id="open_milestones_btn" >Milestones</a></li>
                    <li><a href="#" class="left_panel_btn" id="open_assignments_btn">Assignments</a></li>
                    <li><a href="#" class="left_panel_btn" id="open_issues_btn">Issues</a></li>
                    <li><a href="#" class="left_panel_btn" id="open_notes_btn">Notes</a></li>
                    <li><a href="#" class="left_panel_btn" id="open_members_btn">Members</a></li>
              <!--      <li><a href="#" class="left_panel_btn" id="open_ideas_btn">Ideas</a></li> -->
                </ul>
            </div>
            <div id="monitors_screen">
                <div id="hide_scrollbar">
                    <div id="milestones_monitor" class="monitor">
                        <p class="monitor_title">Milestones</p>
                        <ul class="monitor_tab_list">
                            <li><a class="monitor_tab_first" href="#" id="milestone_history_btn">Project Milestones</a></li>
                            <li><a class="monitor_tab" href="#" id="create_milestone_btn">Create Milestone</a></li>
                        </ul>
                        <div class="monitor_content">
                            <div id="milestone_history_tab" class="history">
                            </div>
                            <div id="create_milestone_tab">
                                <form>
                                    Choose a name for the milestone :<br/>
                                    <input type="text" id="milestone_name" /><br/><br/>
                                    Write a description :<br/>
                                    <textarea id="milestone_description" maxlength="250"></textarea><br/><br/>
                                    Estimated completion time (in days) : <br/>
                                    <input type="number" value="1" id="milestone_est" class="num_input"/><br/><br/>
                                    <button id="new_milestone_btn" class="button2">Create</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="assignments_monitor" class="monitor">
                        <p class="monitor_title">Assignments</p>
                        <ul class="monitor_tab_list">
                            <li><a class="monitor_tab_first" href="#" id="pending_as_history_btn">Pending</a></li>
                            <li><a class="monitor_tab" href="#" id="completed_as_history_btn">Completed</a></li>
                            <li><a class="monitor_tab" href="#" id="create_assignment_btn">Create Assignment</a></li>
                        </ul>
                        <div class="monitor_content">
                            <div id="pending_as_history_tab" class="history">
                            </div>
                            <div id="completed_as_history_tab" class="history">
                            </div>
                            <div id="create_assignment_tab">
                                <form>
                                    Choose a name for the task :<br/>
                                    <input type="text" id="assignment_name" /><br/><br/>
                                    Write a description :<br/>
                                    <textarea id="assignment_description" maxlength="250"></textarea><br/><br/>
                                    Insert a deadline : <br/>
                                    Day : <input type="number" value="1" id="as_day" class="num_input"/>
                                    Month : <input type="number" value="1" id="as_month" class="num_input"/>
                                    Year : <input type="number" value="2015" id="as_year" class="num_input"/><br/>
                                    Assign to user (username) : <br/>
                                    <input type="text" id="as_username" /><br/><br/>
                                    <button id="new_assignment_btn" class="button2">Create</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="issues_monitor" class="monitor">
                        <p class="monitor_title">Issues</p>
                        <ul class="monitor_tab_list">
                            <li><a class="monitor_tab_first" href="#" id="unresolved_issues_btn">Unresolved</a></li>
                            <li><a class="monitor_tab" href="#" id="resolved_issues_btn">Resolved</a></li>
                            <li><a class="monitor_tab" href="#" id="post_issue_btn">Post Issue</a></li>
                        </ul>
                        <div class="monitor_content">
                            <div id="unresolved_issues_tab" class="history">
                            </div>
                            <div id="resolved_issues_tab" class="history">
                            </div>
                            <div id="post_issue_tab">
                                <form>
                                    Choose a name for the issue:<br/>
                                    <input type="text" id="issue_name" /><br/><br/>
                                    Write a description :<br/>
                                    <textarea id="issue_description" maxlength="800" class="big_description"></textarea><br/><br/>
                                    <button id="new_issue_btn" class="button2">Create</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="notes_monitor" class="monitor">
                        <p class="monitor_title">Notes</p>
                        <ul class="monitor_tab_list">
                            <li><a class="monitor_tab_first" href="#" id="my_notes_btn">My Notes</a></li>
                            <li><a class="monitor_tab" href="#" id="create_note_btn">Create Note</a></li>
                        </ul>
                        <div class="monitor_content">
                            <div id="my_notes_tab" class="history">
                            </div>
                            <div id="create_note_tab">
                                <form>
                                    Write your note
                                    <textarea id="notes_description" maxlength="800" class="big_description"></textarea><br/><br/>
                                    <button id="save_note_btn" class="button2">Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="members_monitor" class="monitor">
                        <p class="monitor_title">Project Members : ${members}</p>
                        <ul class="monitor_tab_list">
                            <li><a class="monitor_tab_first" href="#" id="members_btn">Members</a></li>
                            <li><a class="monitor_tab" href="#" id="invite_btn">Invite</a></li>
                        </ul>
                        <div class="monitor_content">
                            <div id="members_tab" class="history">
                            </div>
                            <div id="invite_tab">
                                <form>
                                    Invite a person by writing their username : <br/>
                                    <input type="text" id="mem_username" /><br/><br/>
                                    <button id="send_invite_btn" class="button2">Invite</button>
                                </form>
                            </div>
                        </div>
                    </div>
                     <!--      <div id="ideas_monitor">
                        
                    </div> -->
                    <div id="ajax_dialog" title="TeamWork says : ">
                        <p id="ajax_response_text"></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
