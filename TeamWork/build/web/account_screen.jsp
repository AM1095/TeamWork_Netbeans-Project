<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TeamWork New Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="Css/login_screen_style.css" />
        <script src="jquery/jquery.js"></script>
        <script src="js_scripts/login_screen_manager.js"></script>
    </head>
    <body>
        <header>
            <div id="inline_img_1"></div>
            <div id="inline_img_2"></div>
        </header>
        <div id="main_container" class="big_padding">
                <p id="session_exists">${session_exists}</p>
                <div id="account_panel">
                    <p class="largeText">Create A New Account</p>
                    <form id="account_form" method="post" action="new_account">
                        <input type="hidden" name="action" value="create_account" />
                        <br/><br/>
                        <p>Email Address :</p>
                        <input type="text" name="email" /><br/>
                         <p>First Name :</p>
                        <input type="text" name="first_name" /><br/>
                         <p>Last Name :</p>
                        <input type="text" name="last_name" /><br/>
                        <p >Select Username : </p>
                        <input type="text" name="username" /><br/>
                        <p >Select Password : </p>
                        <input type="password" name="password" /> <br/>
                        <p >Retype Password : </p>
                        <input type="password" name="confirm_password" /><br/><br/>
                        <input id="create_account_button" type="submit" value="Create Account" />
                    </form>
                </div>
        </div>
        <footer>
            <p>Powered By John Argyroulis / WWW Technologies 2014-2015 / University of Thessaly</p>
        </footer>
    </body>
</html>
