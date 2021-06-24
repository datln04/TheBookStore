/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import datpt.DAO.BookDAO;
import datpt.DAO.CategoryDAO;
import datpt.DAO.UserDAO;
import datpt.DTO.BookDTO;
import datpt.DTO.CategoryDTO;
import datpt.DTO.DiscountDTO;
import datpt.DTO.HistoryDTO;
import datpt.DTO.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        
        String name = request.getParameter("txtUserName");
        String pw = request.getParameter("txtPassword");
        
        String url = "login.jsp";

        try {
           UserDAO userDAO = new UserDAO();
           CategoryDAO cateDAO = new CategoryDAO();
           BookDAO bookdDAO = new BookDAO();
           
           UserDTO user = userDAO.getAccount(name, pw);
            
           if(user != null){
               HttpSession session = request.getSession();
               
               if(user.getRoleID().equals("1")){
                   url = "manageBook.jsp";
               }else{
                   url = "search.jsp";
               }
               
               List<CategoryDTO> Listcate = cateDAO.getAllCategory();
               List<DiscountDTO> listDiscount = userDAO.getListDiscount();
               List<HistoryDTO> listHistory = userDAO.loadHistory(user.getUserID());
               List<BookDTO> listBook = bookdDAO.getAllBook();
                             
               session.setAttribute("User", user);
               session.setAttribute("ListCate", Listcate);
               session.setAttribute("ListBook", listBook);
               session.setAttribute("ListHistory", listHistory);
               session.setAttribute("ListDiscount", listDiscount);
               
           }else{
               request.setAttribute("LoginFailed", "ERROR");
           }
                    
        } catch (SQLException ex) {
            log("LoginController + SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoginController + ClassNotFound: " + ex.getMessage());
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
