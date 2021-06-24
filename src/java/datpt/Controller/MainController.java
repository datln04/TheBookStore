/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class MainController extends HttpServlet {

    private final String LOGIN_CONTROLLER = "LoginController";
    private final String DELETE_BOOK_STORE_CONTROLLER = "DeleteBookController";
    private final String UPDATE_BOOK_STORE_CONTROLLER = "UpdateBookController";
    private final String ADD_BOOK_STORE_CONTROLLER = "AddBookController";
    private final String LOAD_CATE_CONTROLLER = "CategoryController";
    private final String LOAD_PAGE_CONTROLLER = "LoadPageController";
    private final String ADD_TO_CARD_CONTROLLER = "AddToCardController";
    private final String REMOVE_ITEM_FROM_CARD = "RemoveItemFromCardServlet";
    private final String INCREASE_ITEM_CONTROLLER = "IncreaseItemController";
    private final String DECREASE_ITEM_CONTROLLER = "DecreaseItemController";
    private final String ADD_DISCOUNT_CONTROLLER = "AddDiscountController";
    private final String APPLY_DISCOUNT_CONTROLLER = "ApplyDiscountController";
    private final String CHECK_OUT_CONTROLLER = "CheckoutController";
    private final String LOG_OUT_CONTROLLER = "LogoutController";
    private final String SEARCH_BY_NUMBER = "SearchNumberController";
    

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

        String action = request.getParameter("btAction");
        String url = "";

        try {
            if ("".equals(action)) {
                url = "login.jsp";
            } else if ("Login".equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if ("Delete".equals(action)) {
                url = DELETE_BOOK_STORE_CONTROLLER;
            } else if ("Update".equals(action)) {
                url = UPDATE_BOOK_STORE_CONTROLLER;
            } else if ("Add".equals(action)) {
                url = ADD_BOOK_STORE_CONTROLLER;
            } else if ("Category".equals(action)) {
                url = LOAD_CATE_CONTROLLER;
            } else if ("LoadPage".equals(action)) {
                url = LOAD_PAGE_CONTROLLER;
            } else if ("AddToCard".equals(action)) {
                url = ADD_TO_CARD_CONTROLLER;
            } else if ("RemoveItemFromCard".equals(action)) {
                url = REMOVE_ITEM_FROM_CARD;
            } else if ("IncreaseItem".equals(action)) {
                url = INCREASE_ITEM_CONTROLLER;
            } else if ("DescreaseItem".equals(action)) {
                url = DECREASE_ITEM_CONTROLLER;
            } else if ("AddDiscount".equals(action)) {
                url = ADD_DISCOUNT_CONTROLLER;
            } else if ("DiscountApply".equals(action)) {
                url = APPLY_DISCOUNT_CONTROLLER;
            }else if("CheckOut".equals(action)){
                url = CHECK_OUT_CONTROLLER;
            }else if("LogOut".equals(action)){
                url = LOG_OUT_CONTROLLER;
            }else if("Search".equals(action)){
                url = SEARCH_BY_NUMBER;
            }

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
