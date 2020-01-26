package servlet;

import beans.Player;
import datalayer.DALCommonFunctions;
import datalayer.DALPlayer;
import datalayer.EnumStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class PlayerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("btnRegister")!=null)
        {
            beans.Player player = new Player();
            player.setPlayerId(request.getParameter("txtPlayerId"));
            player.setPassword(request.getParameter("txtPassword"));
            player.setName(request.getParameter("txtName"));
            player.setGender(request.getParameter("rbtGender"));
            if(request.getParameter("ddlCountry").equals("Other"))
            {
                player.setCountry(request.getParameter("txtOtherCountry"));
                DALCommonFunctions.addCountry(player.getCountry());
            }
            else
            {
               player.setCountry(request.getParameter("ddlCountry"));
           
            }
                       
            player.setSecurityQuestion(request.getParameter("ddlSecurityQuestion"));
            player.setAnswer(request.getParameter("txtAnswer"));
            
            if(request.getParameter("rbtGender").equals("Male"))
            {
                player.setProfilePic("Male.png");
            }    
            else
            {
                player.setProfilePic("Female.png");
            }
                
            
            DALPlayer dp= new DALPlayer();
            if(dp.checkPlayerId(player.getPlayerId())!=EnumStatus.Success)
            {
                EnumStatus ret=dp.addPlayer(player);
                if(ret==EnumStatus.Success)
                {
                    response.sendRedirect("Login.jsp");
                }
  
            }
            else
            {
                RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.jsp");
                    request.setAttribute("name",player.getName());
                    dispatcher.forward(request, response);
            }
        }
        else if(request.getParameter("btnCheck")!=null)
        {
            beans.Player player = new Player();
            player.setPlayerId(request.getParameter("txtPlayerId"));
            DALPlayer dp= new DALPlayer();
            EnumStatus ret=dp.checkPlayerId(player.getPlayerId());
            if(ret==EnumStatus.Success)
            {
                RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.jsp");
                request.setAttribute("id",player.getPlayerId());
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.jsp");
                request.setAttribute("idAvailable",player.getPlayerId());
                dispatcher.forward(request, response);
            }
        }
        else if(request.getParameter("btnLogin")!=null)
        {
           
            String playerId=request.getParameter("txtPlayerId");
            String password=request.getParameter("txtPassword");
            
             
            datalayer.DALPlayer objDAL=new DALPlayer();
            beans.Player player=objDAL.authenticate(playerId, password);
            if(player.getPlayerNo()>0)
            {
                if(request.getParameter("chkRememberMe")!=null)
                {
                    Cookie ck= new Cookie("lastPlayerId",playerId );
                    Cookie ckPassword= new Cookie("lastPlayerPassword",password);
                    ck.setMaxAge(60);
                    ckPassword.setMaxAge(60);
                    response.addCookie(ck);
                    response.addCookie(ckPassword);
                }
 
                ServletContext application= getServletContext();
                
                ArrayList<Integer> loggedPlayers = null;
                
                if(application.getAttribute("loggedPlayers")==null)
                {
                    loggedPlayers = new ArrayList<Integer>();
                }
                else
                {   
                    
                    loggedPlayers = (ArrayList<Integer>) application.getAttribute("loggedPlayers");
                }
                if(loggedPlayers.indexOf(player.getPlayerNo())==-1)
                {
                    loggedPlayers.add(player.getPlayerNo());
                }
                application.setAttribute("loggedPlayers",loggedPlayers);
                request.getSession().setAttribute("playerNo",player.getPlayerNo());
                
                
                response.sendRedirect("Chess/ProfileDetails.jsp");
            }
            else
            {
                response.sendRedirect("Login.jsp?flag=1");
            }
        }
        else if(request.getParameter("btnUpdate")!=null)
        {
            
            int playerNo=Integer.parseInt(request.getSession().getAttribute("playerNo").toString());
            beans.Player player= new Player();
            player.setPlayerNo(playerNo);
            player.setName(request.getParameter("txtName"));
            player.setGender(request.getParameter("rbtGender"));
            if(request.getParameter("ddlCountry").equals("Other"))
            {
                player.setCountry(request.getParameter("txtOtherCountry"));
                DALCommonFunctions.addCountry(player.getCountry());
            }
            else
            {
               player.setCountry(request.getParameter("ddlCountry"));
           
            }
            DALPlayer dp = new DALPlayer();
            EnumStatus ret=dp.updateProfile(player);
            if(ret==EnumStatus.Success)
            {
                response.sendRedirect("Chess/ProfileDetails.jsp");
            }
        }
        else if(request.getParameter("btnChangePassword")!=null)
        {
            String oldPassword=request.getParameter("txtOldPassword");
            String newPassword=request.getParameter("txtNewPassword");
            int playerNo=Integer.parseInt(request.getSession().getAttribute("playerNo").toString());
            datalayer.DALPlayer dp= new DALPlayer();
            beans.Player player=dp.getPlayer(playerNo);
            if(!(oldPassword.equals(player.getPassword())))
            {
                 response.sendRedirect("Chess/ChangePassword.jsp?flag=1");
               // RequestDispatcher dispatcher=request.getRequestDispatcher("Chess/ChangePassword.jsp");
               // request.setAttribute("flag","1");
               // dispatcher.forward(request, response);
            }
            
            else
            {
                EnumStatus ret=dp.changePassword(playerNo,newPassword);
                  response.sendRedirect("Chess/ProfileDetails.jsp?change=1");
           //     RequestDispatcher dispatcher=request.getRequestDispatcher("Chess/OnlinePlayers.jsp");
             //   request.setAttribute("change","1");
               // dispatcher.forward(request,response);
            }
        }
        else if(request.getParameter("btnNext")!=null)
        {
            String playerId=request.getParameter("txtPlayerId");
            datalayer.DALPlayer objDAL=new DALPlayer();
            beans.Player player=objDAL.forgotPassword(playerId);
            if(player.getPlayerNo()>0)
            {
                request.getSession().setAttribute("playerNo", player.getPlayerNo());
                response.sendRedirect("ForgotPassword.jsp?flag=1");
            }
            else
            {
                response.sendRedirect("ForgotPassword.jsp?wrong=1");
            }
        }
        else if(request.getParameter("btnSubmit")!=null)  
        {
            String answer=request.getParameter("txtAnswer");
            String playerId=request.getParameter("txtPlayerId");
            datalayer.DALPlayer objDAL=new DALPlayer();
            beans.Player player=objDAL.forgotPassword(playerId);
            if(player.getAnswer().equals(answer))
            {
                response.sendRedirect("ChangeForgotPassword.jsp");
            }
            else
            {
                RequestDispatcher dispatcher=request.getRequestDispatcher("ForgotPassword.jsp?flag=1");
                request.setAttribute("change", "1");
                dispatcher.forward(request, response);
            }
        }
        else if(request.getParameter("btnChangeForgotPassword")!=null)
        {
            String newPassword=request.getParameter("txtNewPassword");
            int playerNo=Integer.parseInt(request.getSession().getAttribute("playerNo").toString());
            datalayer.DALPlayer dp= new DALPlayer();
            
               EnumStatus ret=dp.changePassword(playerNo, newPassword);
            
                RequestDispatcher dispatcher=request.getRequestDispatcher("Login.jsp");
                request.setAttribute("change", "1");
                dispatcher.forward(request, response);
            
        }
        else if(request.getParameter("otherPlayerNo")!=null)
        {
            int no=Integer.parseInt(request.getParameter("otherPlayerNo").toString());
            response.sendRedirect("Chess/PlayerProfile.jsp?otherPlayer="+no);
            
        }
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
