/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.DTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Order implements Serializable {

    private int orderID;
    private int userID;
    private List<Item> listItem;
    private float total; //discount
    private String date;

    public Order() {
    }

    public Order(int orderID, int userID, List<Item> listItem, float total) {
        this.orderID = orderID;
        this.userID = userID;
        this.listItem = listItem;
        this.total = total;
    }

    public Order(int userID, float total, String date) {
        this.userID = userID;
        this.total = total;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Item> getListItem() {
        return listItem;
    }

    public void setListItem(List<Item> listItem) {
        this.listItem = listItem;
    }

    public float getTotal() {
        return this.total = total;    
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCountList() {
        if (getListItem() != null) {
            return getListItem().size();
        }
        return 0;
    }
    
    public float getPriceTotal(){ // not discount
        float rs = 0;
        for (Item item : listItem) {
            rs += (item.getPrice() * item.getQuantity());
        }
        return rs;
    }

    
}
