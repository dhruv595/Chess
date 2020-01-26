package servlet;

import datalayer.DALPlayer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class PictureServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int playerNo = Integer.parseInt(request.getSession().getAttribute("playerNo").toString());
        String RealPath = this.getServletContext().getRealPath("/");
        String FullPath = RealPath + "Pictures\\Pic" + playerNo + ".jpg";

        Part part = request.getPart("ProfilePic");
        OutputStream outstream;
        InputStream instream;
        try {
            File f1 = new File(FullPath);
            outstream = new FileOutputStream(f1);
            instream = part.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = instream.read(bytes)) != -1) {
                outstream.write(bytes, 0, read);
            }
            outstream.close();
            instream.close();

            datalayer.DALPlayer objDAL = new DALPlayer();
            objDAL.updateProfilePic(playerNo);
            response.sendRedirect("Chess/ProfileDetails.jsp");
        } catch (Exception ex) {
            System.out.println(ex);
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
