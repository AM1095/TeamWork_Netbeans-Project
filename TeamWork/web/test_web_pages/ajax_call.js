$(document).ready(function (){
  
    function ajaxData(){
        $.ajax({
           type : "GET",
           url : "/TeamWork/ajax_service",
           success : function(data){
               $("#content").append(data);
               console.log("request_success");
               console.log("the data is: ");
               console.log(data);
           },
           fail: function(){
               console.log("error in request");
           }

        });
    }
    
    ajaxData();
    
});

