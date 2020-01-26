package servlet;

import beans.BlackKing;
import beans.BlackRook;
import beans.Game;
import beans.WhiteKing;
import beans.WhiteRook;
import datalayer.DALChess;
import datalayer.DALPlayer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.weld.servlet.SessionHolder;
public class ChessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("game")!=null)
        {
            new datalayer.DALChess().unloadStaticVariable();
            Game game= new Game();
            
            int playerNo1= Integer.parseInt(request.getSession().getAttribute("playerNo").toString());
            int playerNo2=Integer.parseInt(request.getParameter("otherPlayerNo"));
          
          game.setBlack(new DALPlayer().getPlayer(playerNo1));
          game.setWhite(new DALPlayer().getPlayer(playerNo2));
          game.setCurrentPlayer(game.getWhite());
          
          game.setBlackKingCheck(BlackKing.check);
          game.setWhiteKingCheck(WhiteKing.check);
          game.setBlackKingCastle(BlackKing.castle);
          game.setWhiteKingCastle(WhiteKing.castle);
          game.setBlackRookLeftCastle(BlackRook.leftCastle);
          game.setBlackRookRightCastle(BlackRook.rightCastle);
          game.setWhiteRookLeftCastle(WhiteRook.leftCastle);
          game.setWhiteRookRightCastle(WhiteRook.rightCastle);
          
          datalayer.DALChess obj = new DALChess();
          int gameId=obj.startGame(game);
          
          response.sendRedirect("Chess/ProfileDetails.jsp?challenge=1");
        }
        if(request.getParameter("resume")!=null)
        {
            int gameId=Integer.parseInt(request.getParameter("resume").toString());
            request.getSession().setAttribute("gameId", gameId);
            response.sendRedirect("Chess/Play.jsp");
        }
        
        if(request.getParameter("gameId")!=null)
        {
            int gameId= Integer.parseInt(request.getParameter("gameId"));
            new datalayer.DALChess().challengeAccepted(gameId);
            request.getSession().setAttribute("gameId", gameId);
            response.sendRedirect("Chess/Play.jsp");
        }
        if(request.getParameter("deleteGameId")!=null)
        {
            int gameId= Integer.parseInt(request.getParameter("deleteGameId"));
            new datalayer.DALChess().challengeDeclined(gameId);
            response.sendRedirect("Chess/Challenges.jsp");
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
