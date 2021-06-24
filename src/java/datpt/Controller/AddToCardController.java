/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import datpt.DAO.BookDAO;
import datpt.DTO.BookDTO;
import datpt.DTO.Item;
import datpt.DTO.Order;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "AddToCardController", urlPatterns = {"/AddToCardController"})
public class AddToCardController extends HttpServlet {

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

        String bookID = request.getParameter("bookID");
        String url = "search.jsp";

        try {
            //TH1: chua co Order
            HttpSession session = request.getSession();
            int quantity = 1;
            int indexForListBook = 0;
            BookDAO dao = new BookDAO();
            BookDTO dto = dao.getBookByID(Integer.parseInt(bookID));
            List<BookDTO> listBook = (List<BookDTO>) session.getAttribute("ListBook");
            if (session.getAttribute("Order") == null) {
                Order order = new Order();
                List<Item> list = new ArrayList<>();
                Item item = new Item();
                item.setItemID(dto.getBookID());
                item.setBook(dto);
                item.setPrice(dto.getPrice()); // 80
                item.setQuantity(quantity); // 1 

                //setQuantity for book (bussiness)
                item.getBook().setQuantity(item.getBook().getQuantity() - quantity);
                for (int i = 0; i < listBook.size(); i++) {
                    if (listBook.get(i).getBookID() == item.getItemID()) {
                        indexForListBook = i;
                    }
                }
                listBook.get(indexForListBook).setQuantity(item.getBook().getQuantity());

                list.add(item);
                
                order.setListItem(list);
                session.setAttribute("ListBook", listBook);
                session.setAttribute("Order", order);
            } else { //da co Order
                //TH1: book da ton tai
                //TH2: book chua ton tai

                int index = 0;
                Item tmp = null;
                Order o = (Order) session.getAttribute("Order");
                List<Item> listItem = o.getListItem();

                for (int i = 0; i < listItem.size(); i++) {
                    if (listItem.get(i).getItemID() == dto.getBookID()) {
                        index = i;
                        tmp = listItem.get(i);
                    }
                }

                if (tmp != null) {
                    tmp.setQuantity(tmp.getQuantity() + quantity);
                    
                    tmp.getBook().setQuantity(tmp.getBook().getQuantity() - quantity);
                    for (int n = 0; n < listBook.size(); n++) {
                        if(listBook.get(n).getBookID() == tmp.getItemID()){
                            indexForListBook = n;
                        }
                    }
                    listBook.get(indexForListBook).setQuantity(tmp.getBook().getQuantity());
                    
                    
                    
                    listItem.set(index, tmp);
                } else {
                    Item i = new Item();
                    i.setItemID(dto.getBookID());
                    i.setBook(dto);
                    i.setPrice(dto.getPrice());
                    i.setQuantity(quantity);

                    i.getBook().setQuantity(i.getBook().getQuantity() - quantity);
                    for (int n = 0; n < listBook.size(); n++) {
                        if(listBook.get(n).getBookID() == i.getItemID()){
                            indexForListBook = n;
                        }
                    }
                    listBook.get(indexForListBook).setQuantity(i.getBook().getQuantity());

                    listItem.add(i);
                }

                session.setAttribute("ListBook", listBook);
                session.setAttribute("Order", o);
                
            }

        } catch (SQLException ex) {
            log("AddToCardController + SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("AddToCardController + ClassNotFound: " + ex.getMessage());
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
