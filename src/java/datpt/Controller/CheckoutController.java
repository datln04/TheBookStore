/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import datpt.DAO.BookDAO;
import datpt.DAO.UserDAO;
import datpt.DTO.DiscountDTO;
import datpt.DTO.HistoryDTO;
import datpt.DTO.Item;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datpt.DTO.Order;
import datpt.DTO.UserDTO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

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

        String url = "shoppingCard.jsp";
        String discountID = request.getParameter("discountID");
        String totalPrice = request.getParameter("total");
        float total = 0;

        try {
            if (!totalPrice.isEmpty()) {
                total = Float.parseFloat(totalPrice);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                Order order = (Order) session.getAttribute("Order");

                UserDTO user = (UserDTO) session.getAttribute("User");

                // add order 
                UserDAO dao = new UserDAO();
                BookDAO bookDAO = new BookDAO();
                if (order.getCountList() > 0) {

                    int lastOrder = dao.insertOrder(total, user.getUserID());

                    if (lastOrder > 0) {
                        int getLastOrderID = dao.getLastOrder();

                        // add detail
                        List<Item> list = order.getListItem();
                        for (Item item : list) {
                            int insertDetail = dao.insertDetail(item.getPrice(), item.getQuantity(), getLastOrderID, item.getItemID());
                            int setBookQuantity = bookDAO.setQuantityBook(item.getItemID(), item.getQuantity());
                        }

                        // set discount if it use
                        if (discountID != "") {
                            int removeDiscount = dao.removeDiscount(Integer.parseInt(discountID));
                            if (removeDiscount > 0) {
                                session.setAttribute("Order", null);
                                session.removeAttribute("Order");

                                List<DiscountDTO> listDiscount = dao.getListDiscount();
                                session.setAttribute("ListDiscount", listDiscount);

                                List<HistoryDTO> listHistory = dao.loadHistory(user.getUserID());
                                session.setAttribute("ListHistory", listHistory);

                                request.setAttribute("SUCCESS", "remove Success");
                                url = "search.jsp";
                            }
                        } else {
                            session.setAttribute("Order", null);
                            session.removeAttribute("Order");

                            List<DiscountDTO> listDiscount = dao.getListDiscount();
                            session.setAttribute("ListDiscount", listDiscount);

                            List<HistoryDTO> listHistory = dao.loadHistory(user.getUserID());
                            session.setAttribute("ListHistory", listHistory);

                            request.setAttribute("SUCCESS", "remove Success");
                            url = "search.jsp";
                        }

                    }
                }

            }

        } catch (SQLException ex) {
            log("CheckOutController + SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CheckOutController + ClassNotFound: " + ex.getMessage());
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
