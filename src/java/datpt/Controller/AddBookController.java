/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import datpt.DAO.BookDAO;
import datpt.DTO.BookDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddBookController", urlPatterns = {"/AddBookController"})
public class AddBookController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String bookName = request.getParameter("txtBookName");
        String images = request.getParameter("txtImages");
        String price = request.getParameter("txtPrice");
        String quantity = request.getParameter("txtQuantity");
        String cateID = request.getParameter("txtCateID");

        String url = "search.jsp";
       

        try {
            BookDAO dao = new BookDAO();
            int rs = dao.insertBookStore(bookName, images, Float.parseFloat(price), Integer.parseInt(quantity), true, Integer.parseInt(cateID));
            if (rs > 0) {
                BookDAO bookDao = new BookDAO();

                HttpSession session = request.getSession(false);

                if (session != null) {
                    List<BookDTO> list = bookDao.getAllBook();
                    session.setAttribute("ListBook", list);
                    url = "manageBook.jsp";
                }
            }

        } catch (SQLException ex) {
            log("InsertBookController + SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("InsertBookController + ClassNotFound: " + ex.getMessage());
        } catch (ParseException ex) {
             log("InsertBookController + ParseException: " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
