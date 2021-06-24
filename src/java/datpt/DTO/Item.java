/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.DTO;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Item implements Serializable{
    private int itemID;
    private BookDTO book;
    private float price;
    private int quantity;

    public Item() {
    }

    public Item(int itemID, BookDTO book, float price, int quantity) {
        this.itemID = itemID;
        this.book = book;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
