<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error Page</title>
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
            <div id="center_container" >
                <div id="error_panel">
                    <p class="error_title">You've encountered an error :</p><br/>
                    <p class="error_message">${error_message}</p>
                </div>
            </div>
        </div>
        <footer>
            <p>Powered By John Argyroulis / WWW Technologies 2014-2015 / University of Thessaly</p>
        </footer>
    </body>
</html>
