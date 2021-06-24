/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import datpt.DTO.BookDTO;
import datpt.DTO.Item;
import datpt.DTO.Order;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DecreaseItemController", urlPatterns = {"/DecreaseItemController"})
public class DecreaseItemController extends HttpServlet {

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
         String itemID = request.getParameter("itemID");
        String url = "shoppingCard.jsp";
        int quantity = 1;
        int index = -1;
        int indexForBook = 0;
        
        try {
            HttpSession session = request.getSession(false);
           
            if(session!=null){
                Order order = (Order) session.getAttribute("Order");
                List<BookDTO> listBook = (List<BookDTO>) session.getAttribute("ListBook");
                List<Item> list = order.getListItem();
                for(int i = 0; i < list.size(); i++){
                    if(list.get(i).getItemID() == Integer.parseInt(itemID)){
                        index = i;
                    }
                }
                
                for(int i = 0; i < listBook.size(); i++){
                    if(listBook.get(i).getBookID() == Integer.parseInt(itemID)){
                        indexForBook = i;
                    }
                }
                
                if(index > -1){
                    list.get(index).getBook().setQuantity(list.get(index).getBook().getQuantity() + quantity);
                    list.get(index).setQuantity(list.get(index).getQuantity() - quantity);
                    listBook.get(indexForBook).setQuantity(listBook.get(indexForBook).getQuantity() + quantity);
                    
                    session.setAttribute("Order", order);
                    session.setAttribute("ListBook", listBook);
                   
                }
                
            }
           
        }finally{
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
