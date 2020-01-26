function  validateRegistration()
{
    var ret=true;
    var playerId= document.getElementById("txtPlayerId");
    var password=document.getElementById("txtPassword");
    var confirmPassword= document.getElementById("txtConfirmPassword");
    var name=document.getElementById("txtName");
    var country=document.getElementById("ddlCountry");
    var otherCountry=document.getElementById("txtOtherCountry");
    var securityQuestion=document.getElementById("ddlSecurityQuestion");
    var answer=document.getElementById("txtAnswer");
    
    var spPlayerId=document.getElementById("msgPlayerId");
    var spPassword=document.getElementById("msgPassword");
    var spConfirmPassword=document.getElementById("msgConfirmPassword");
    var spName=document.getElementById("msgName");
    var spCountry=document.getElementById("msgCountry");
    var spSecurityQuestion=document.getElementById("msgSecurityQuestion");
    var spAnswer=document.getElementById("msgAnswer");
    
    spPlayerId.innerHTML="";
    spPassword.innerHTML="";
    spConfirmPassword.innerHTML="";
    spName.innerHTML="";
    spCountry.innerHTML="";
    spSecurityQuestion.innerHTML="";
    spAnswer.innerHTML="";
    
    if(playerId.value.length==0)
    {
        spPlayerId.innerHTML="Please enter Username!!";
        ret=false;
    }
    
    if(password.value.length==0)
    {
        spPassword.innerHTML="Please enter Password!!";
        ret=false;
    }
    
    if(name.value.length==0)
    {
        spName.innerHTML="Please enter your name!!";
        ret=false;
    }
    
    if(country.selectedIndex<=0)
    {
        spCountry.innerHTML="Please select country!!";
        ret=false;
    }
    if(otherCountry.value.length==0&&country.selectedIndex==country.length-1)
    {
        spCountry.innerHTML="Please give country!!";
        ret=false;
    }
    if(securityQuestion.selectedIndex<=0)
    {
        spSecurityQuestion.innerHTML="Please select question!!";
        ret=false;
    }
    if(answer.value.length==0)
    {
        spAnswer.innerHTML="Please answer !!!";
        ret=false;
    }
    if(password.value!=confirmPassword.value)
    {
        spConfirmPassword.innerHTML="Passwords donot matches";
        ret=false;
    }
    
    
    return ret;
}

function validateLogin()
{
    var ret=true;
    var playerId= document.getElementById("txtPlayerId");
    var password=document.getElementById("txtPassword");
    
    
    var spPlayerId=document.getElementById("msgPlayerId");
    var spPassword=document.getElementById("msgPassword");
    
    
    spPlayerId.innerHTML="";
    spPassword.innerHTML="";
    
    
    if(playerId.value.length==0)
    {
        spPlayerId.innerHTML="Please enter Username!!";
        ret=false;
    }
    
    if(password.value.length==0)
    {
        spPassword.innerHTML="Please enter Password!!";
        ret=false;
    }
    
    return ret;
}



function validateUpdate()
{
    
    var ret=true;
    
    var name=document.getElementById("txtName");
    var country=document.getElementById("ddlCountry");
    
    var spName=document.getElementById("msgName");
    var spCountry=document.getElementById("msgCountry");
    
    var spName=document.getElementById("msgName");
    var spCountry=document.getElementById("msgCountry");
    
    if(name.value.length==0)
    {
        spName.innerHTML="Please enter your name!!";
        ret=false;
    }
    
    if(country.selectedIndex<=0)
    {
        spCountry.innerHTML="Please select country!!";
        ret=false;
    }
    return ret;
}


function validateChangePassword()
{
    var ret= true;
    var newPassword=document.getElementById("txtNewPassword");
    var confirmPassword=document.getElementById("txtConfirmPassword");
     
    var spNewPassword=document.getElementById("msgNewPassword");
    var spConfirmPassword=document.getElementById("msgConfirmPassword");
    
    spNewPassword.innerHTML="";
    spConfirmPassword.innerHTML="";
    
    if(newPassword.value.length==0)
    {
        spNewPassword.innerHTML="This field cannot be empty";
        ret=false;
    }
    if(confirmPassword.value!=newPassword.value)
    {
        spConfirmPassword.innerHTML="Passwords must be same";
        ret=false;
    }
    
    
    return ret;
    
}


function validateCountry()
{
        
    var otherCountry=document.getElementById("txtOtherCountry");
    
    var ddl = document.getElementById("ddlCountry");
    
    if(ddlCountry.selectedIndex==ddlCountry.length-1)
    {
        otherCountry.value="";
        otherCountry.style.display="block";
    }
    else
    {
        otherCountry.style.display="none";
    }

    
}

function validateUsername()
{
    var username=document.getElementById("txtPlayerId");
    alert("helo");
}
var selected=false;
var row;
var column;
var url="";
var urlSelected="";
var status="false";
var blackDead=-1;
var whiteDead=-1;
var lastMove;
var interval=null;


function validateMove(r,c)
{   
    
    var w=document.getElementById("whiteDeletedCount");
    whiteDead=parseInt(w.value);
    var p=document.getElementById("blackDeletedCount");
    blackDead=parseInt(p.value);
    
    var blackWin=document.getElementById("blackWin");
    var whiteWin=document.getElementById("whiteWin");
    
    var currentPlayer=document.getElementById("currentPlayer");
    urlSelected=$("#cell"+r+c).attr("src");
    var color=urlSelected.substr(12,1);

    lastMove=document.getElementById("lastMove");
    
    if(selected==true&&currentPlayer.value==color)
    {
        url=urlSelected;
        row=r;
        column=c;
        $("#audio").attr("src","music/Button.mp3");
        
    }
    if(selected==false && currentPlayer.value!=color)
    {
        $("#audio").attr("src","music/Error.mp3");
    }
    if(selected==false && currentPlayer.value==color)
    {
        $(document).ready
        (
            function()
            {                
                row=r;
                column=c;                
                url=$("#cell"+row+column).attr("src");
                selected=true;
                $("#audio").attr("src","music/Button.mp3");
                
            }
        )
        
    }
    else
    {
        if(selected==true &&(row !=r || column != c)&& color!=currentPlayer.value)
        {
            validateTurn(row,column,r,c);
            
            if(status=="true"||status=="check")
            {
                $("#ChessBoard").css('pointer-events','none');
                lastMove.value=(row+""+column+""+r+""+c);
                $(document).ready
                (                
                    function()
                    {   
                        
                        $("#cell"+r+c).attr("src",url);
                        $("#cell"+row+column).attr("src","");
                        if(status=="check")
                        {
                            $("#audio").attr("src","music/Check.mp3");
                        }
                        else
                        {
                            $("#audio").attr("src","music/Button.mp3");
                        }
                        if(currentPlayer.value=="w")
                        {
                            currentPlayer.value="b";
                            document.getElementById("currentColor").value="black";
                            
                            
                            if(urlSelected!="")
                            {   $("#blackDead"+blackDead).attr("src",urlSelected);
                                $("#blackDead"+blackDead).show();
                                blackDead=blackDead+1;
                                p.value=blackDead;
                            }
                            
                        }
                        else
                        {
                            currentPlayer.value="w";
                            document.getElementById("currentColor").value="white";
                            
                            if(urlSelected!="")
                            {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                                $("#whiteDead"+whiteDead).show();
                                whiteDead=whiteDead+1;
                                w.value=whiteDead;
                            }
                        }
                          
                    
                    }
                )
            }
           else if(status=="revive"||status=="rcheck")
           {
               lastMove.value=(row+""+column+""+r+""+c);
                $(document).ready
                (                
                    function()
                    {      
                        $("#audio").attr("src","music/Button.mp3");
                        if(status=="rcheck")
                        {
                            document.getElementById("txt1").value="check";
                            $("#audio").attr("src","music/Check.mp3");
                        }
                        $("#cell"+row+column).attr("src","");
                        if(currentPlayer.value=="w")
                        {
                            currentPlayer.value="b";
                            document.getElementById("currentColor").value="black";
                            url="ChessImages/white_queen.png";
                            if(urlSelected!="")
                            {   $("#blackDead"+blackDead).attr("src",urlSelected);
                                $("#blackDead"+blackDead).show();
                                blackDead=blackDead+1;
                                p.value=blackDead;
                            }
                        }
                        else
                        {
                            currentPlayer.value="w";
                            document.getElementById("currentColor").value="white";
                            url="ChessImages/black_queen.png";
                            if(urlSelected!="")
                            {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                                $("#whiteDead"+whiteDead).show();
                                whiteDead=whiteDead+1;
                                w.value=whiteDead;
                            }
                        }
                        $("#cell"+r+c).attr("src",url);
                    }
                )
                
            }
            else if(status=="left castle"||status=="lcc")
            {
               lastMove.value=(row+""+column+""+r+""+c); 
                $(document).ready
                (                
                    function()
                    {   if(status=="lcc")
                        {
                            document.getElementById("txt1").value="check";
                            $("#audio").attr("src","music/Check.mp3");
                        }
                        else
                        {
                            $("#audio").attr("src","music/Castle.mp3");
                        }
                        $("#cell"+r+c).attr("src",url);    
                        $("#cell"+row+column).attr("src","");
                        c=c+1;
                        if(currentPlayer.value=="w")
                        {
                            currentPlayer.value="b";
                            document.getElementById("currentColor").value="black";
                            url="ChessImages/white_rook.png"
                        }
                        else
                        {
                            currentPlayer.value="w";
                            document.getElementById("currentColor").value="white";
                            url="ChessImages/black_rook.png"
                        }
                        $("#cell"+r+c).attr("src",url);
                        c=c-2;
                        $("#cell"+r+c).attr("src","");
                    }
                )
            }
            else if(status=="right castle"||status=="rcc")
            {
                lastMove.value=(row+""+column+""+r+""+c);
                $(document).ready
                (                
                    function()
                    {      
                        if(status=="rcc")
                        {
                            document.getElementById("txt1").value="check";
                            $("#audio").attr("src","music/Check.mp3");
                        }
                        else
                        {
                            $("#audio").attr("src","music/Castle.mp3");
                        }
                        $("#cell"+r+c).attr("src",url);    
                        $("#cell"+row+column).attr("src","");
                        c=c-1;
                        if(currentPlayer.value=="w")
                        {
                            currentPlayer.value="b";
                            document.getElementById("currentColor").value="black";
                            url="ChessImages/white_rook.png"
                        }
                        else
                        {
                            currentPlayer.value="w";
                            document.getElementById("currentColor").value="white";
                            url="ChessImages/black_rook.png"
                        }
                        $("#cell"+r+c).attr("src",url);
                        c=c+3;
                        $("#cell"+r+c).attr("src","");
                    }
                )
            }
            else if(status=="checkmate")    
            {
                lastMove.value=(row+""+column+""+r+""+c);
                $(document).ready
                (                
                    function()
                    {      
                        
                        $("#cell"+r+c).attr("src",url);
                        $("#cell"+row+column).attr("src","");
                        $("#audio").attr("src","music/Checkmate.mp3");
                        if(currentPlayer.value=="w")
                        {
                            document.getElementById("txt1").value=whiteWin.value;
                            document.getElementById("currentColor").value=""; 
                            
                            if(urlSelected!="")
                            {   $("#blackDead"+blackDead).attr("src",urlSelected);
                                $("#blackDead"+blackDead).show();
                                blackDead=blackDead+1;
                                p.value=blackDead;
                            }    
                        }
                        else
                        {
                            document.getElementById("txt1").value=blackWin.value;
                            document.getElementById("currentColor").value=""; 
                            
                            if(urlSelected!="")
                            {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                                $("#whiteDead"+whiteDead).show();
                                whiteDead=whiteDead+1;
                                w.value=whiteDead;
                            }
                            
                        }
                        currentPlayer.value="g";
                        
                    }
                )
            }
            else if(status=="Illegal Move"||status=="false")
            {
                $(document).ready
                (                
                    function()
                    {      
                        
                        $("#audio").attr("src","music/Error.mp3");
                        
                        
                    }
                )
            }
            
            selected=false;
        
        }
        
    }
    
}
var obj;

function validateTurn(row1,column1,row2,column2)
{
    
    if(window.XMLHttpRequest)
    {
        obj = new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {
        obj = new ActiveXObject("Microsoft.XMLHttp");
    }
    else
    {
        alert("Browser doesn't support AJAX");
    }
    var ajaxUrl="ValidateTurn.jsp?row1="+row1+"&column1="+column1+"&row2="+row2+"&column2="+column2;
    obj.open("get",ajaxUrl,false);
    obj.onreadystatechange=f2;
    obj.send();
    
}

function f2()
{
    if(obj.readyState==4)
    {
        status =obj.responseText.trim();
        document.getElementById("txt1").value=status;
        
    }
}

function reverse()
{
    var table = document.getElementById("TChessBoard");
    rows = table.rows;

    for (var i = 0; i < rows.length; i++) 
    {
        rows[i].parentNode.insertBefore(rows[rows.length-1], rows[i]);
        
    }
}

function start() {
    
    		var eventSource = new EventSource("/Chess/ChallengeServlet");  
		
              
                eventSource.addEventListener('newChallenge', function(event) {
			
				document.getElementById('msgSpan').innerHTML = event.data;
				
			}, false);
                eventSource.addEventListener('acceptChallenge', function(event) {
			
				document.getElementById('msgSpan1').innerHTML = event.data;
				
			}, false);

		
	}
        
        
 var move;
 setInterval(getLastMove,3000);
 var r1;
 var c1;
 var r2;
 var c2;

function getLastMove()
{
    getLastMoves();
    lastMove=document.getElementById("lastMove");
    var rowInitial=parseInt(move.substr(0,1));
    var columnInitial=parseInt(move.substr(1,1));
    var rowFinal=parseInt(move.substr(2,1));
    var columnFinal=parseInt(move.substr(3,1));
    var moveStatus=move.substr(4);
    var dbLastMove=rowInitial+""+columnInitial+""+rowFinal+""+columnFinal;
    
    var w=document.getElementById("whiteDeletedCount");
    whiteDead=parseInt(w.value);
    var p=document.getElementById("blackDeletedCount");
    blackDead=parseInt(p.value);
    
    var blackWin=document.getElementById("blackWin");
    var whiteWin=document.getElementById("whiteWin");
    
    
    
    
    if(!(lastMove.value==dbLastMove))
    {
        $("#ChessBoard").css('pointer-events','auto');  
        var currentPlayer=document.getElementById("currentPlayer");
        document.getElementById("txt1").value=moveStatus;
        var url =$("#cell"+rowInitial+columnInitial).attr("src");
        var urlSelected=$("#cell"+rowFinal+columnFinal).attr("src");
        $("#cell"+rowInitial+columnInitial).attr("src","");
        $("#cell"+rowFinal+columnFinal).attr("src",url);
        if(moveStatus=="true"||moveStatus=="check")
        {
            if(moveStatus=="true")
            {
                $("#audio").attr("src","music/Button.mp3");
            }
            if(moveStatus=="check")
            {
                $("#audio").attr("src","music/Check.mp3");    
            }
            if(currentPlayer.value=="w")
            {
                currentPlayer.value="b";
                document.getElementById("currentColor").value="black";
                if(urlSelected!="")
                {   $("#blackDead"+blackDead).attr("src",urlSelected);
                    $("#blackDead"+blackDead).show();
                    blackDead=blackDead+1;
                    p.value=blackDead;
                }

            }
            else
            {
                currentPlayer.value="w";
                document.getElementById("currentColor").value="white";
                if(urlSelected!="")
                {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                    $("#whiteDead"+whiteDead).show();
                    whiteDead=whiteDead+1;
                    w.value=whiteDead;
                }
            }
        }         
        else if(moveStatus=="left castle"||moveStatus=="lcc")
        {
            if(moveStatus=="left castle")
            {
                $("#audio").attr("src","music/Castle.mp3");
                document.getElementById("txt1").value="Castle";
            }
            if(moveStatus=="lcc")
            {
                $("#audio").attr("src","music/Check.mp3");
                document.getElementById("txt1").value="check";
            }
             if(currentPlayer.value=="w")
            {
                currentPlayer.value="b";
                document.getElementById("currentColor").value="black";
                url="ChessImages/white_rook.png"
            }
            else
            {
                currentPlayer.value="w";
                document.getElementById("currentColor").value="white";
                url="ChessImages/black_rook.png"
            }
           
            columnFinal=columnFinal-1;
            $("#cell"+rowFinal+columnFinal).attr("src","");
            columnFinal=columnFinal+2;
            $("#cell"+rowFinal+columnFinal).attr("src",url);
            columnFinal=columnFinal-1;
            
             
        }
        else if(moveStatus=="right castle"||moveStatus=="rcc")
        {
            if(moveStatus=="right castle")
            {
                $("#audio").attr("src","music/Castle.mp3");
                document.getElementById("txt1").value="Castle";
      
            }
            if(moveStatus=="rcc")
            {
                $("#audio").attr("src","music/Check.mp3");
                document.getElementById("txt1").value="check";
            }
             if(currentPlayer.value=="w")
            {
                currentPlayer.value="b";
                document.getElementById("currentColor").value="black";
                url="ChessImages/white_rook.png"
            }
            else
            {
                currentPlayer.value="w";
                document.getElementById("currentColor").value="white";
                url="ChessImages/black_rook.png"
            }
           
            columnFinal=columnFinal+2;
            $("#cell"+rowFinal+columnFinal).attr("src","");
            columnFinal=columnFinal-3;
            $("#cell"+rowFinal+columnFinal).attr("src",url);
            columnFinal=columnFinal+1;
            
             
        }
        else if(moveStatus=="revive"||moveStatus=="rcheck")
        {
            if(moveStatus=="revive")
            {
                $("#audio").attr("src","music/Button.mp3");
                document.getElementById("txt1").value="Revive";
      
            }
            if(moveStatus=="rcheck")
            {
                $("#audio").attr("src","music/Check.mp3");
                document.getElementById("txt1").value="check";
            }
            if(currentPlayer.value=="w")
            {
                currentPlayer.value="b";
                document.getElementById("currentColor").value="black";
                url="ChessImages/white_queen.png";
                if(urlSelected!="")
                {   $("#blackDead"+blackDead).attr("src",urlSelected);
                    $("#blackDead"+blackDead).show();
                    blackDead=blackDead+1;
                    p.value=blackDead;
                }
            }
            else
            {
                currentPlayer.value="w";
                document.getElementById("currentColor").value="white";
                url="ChessImages/black_queen.png";
                if(urlSelected!="")
                {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                    $("#whiteDead"+whiteDead).show();
                    whiteDead=whiteDead+1;
                    w.value=whiteDead;
                }
            }
            $("#cell"+rowFinal+columnFinal).attr("src",url);
        }
        else if(moveStatus=="checkmate")
        {
            $("#audio").attr("src","music/Checkmate.mp3");
             
               
            if(currentPlayer.value=="w")
            {
                document.getElementById("txt1").value=whiteWin.value;
                document.getElementById("currentColor").value=""; 

                if(urlSelected!="")
                {   $("#blackDead"+blackDead).attr("src",urlSelected);
                    $("#blackDead"+blackDead).show();
                    blackDead=blackDead+1;
                    p.value=blackDead;
                }    
            }
            else
            {
                document.getElementById("txt1").value=blackWin.value;
                document.getElementById("currentColor").value=""; 

                if(urlSelected!="")
                {   $("#whiteDead"+whiteDead).attr("src",urlSelected);
                    $("#whiteDead"+whiteDead).show();
                    whiteDead=whiteDead+1;
                    w.value=whiteDead;
                }

            }
            currentPlayer.value="l";
        }
        r1=rowInitial;
        r2=rowFinal;
        c1=columnInitial;
        c2=columnFinal;
        $("#table"+r1+c1).addClass("to");
        $("#table"+r2+c2).addClass("from");
        setTimeout(remove,7000);
    }    
    lastMove.value=rowInitial+""+columnInitial+""+rowFinal+""+columnFinal;
}

function getLastMoves()
{
        

        if(window.XMLHttpRequest)
       {
            obj = new XMLHttpRequest();
        }
        else if(window.ActiveXObject)
        {
            obj = new ActiveXObject("Microsoft.XMLHttp");
        }
        else
        {
            alert("Browser doesn't support AJAX");
        }
        var ajaxUrl="GetLastMove.jsp";
        obj.open("get",ajaxUrl,false);
        obj.onreadystatechange=f3;
        obj.send();

}

function f3()
{
    if(obj.readyState==4)
    {
        move=obj.responseText.trim();
        
    
 
    }
    
}

 function remove()
 {
        $("#table"+r1+c1).removeClass("to");
        $("#table"+r2+c2).removeClass("from");
     
 }





