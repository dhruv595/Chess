package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChallengeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       response.setContentType("text/event-stream");	

		//encoding must be set to UTF-8
		response.setCharacterEncoding("UTF-8");
                boolean ret1=new datalayer.DALChess().pendingRequest(Integer.parseInt(request.getSession().getAttribute("playerNo").toString()));
		
                PrintWriter writer = response.getWriter();


                    writer.write("event:newChallenge\n");
                    
                    if(ret1==true)
                    {
                        writer.write("data: "+ "You have pending request" +"\n\n");
                    }
                    else
                    {
                        writer.write("data: "+ "You have no pending request" +"\n\n");

                    }
                   
                    try 
                    {
                            Thread.sleep(10);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
		
		writer.close();
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
