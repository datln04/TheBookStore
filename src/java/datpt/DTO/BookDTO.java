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
public class BookDTO implements Serializable{
    private int bookID;
    private String bookName;
    private String images;
    private float price;
    private int quantity;
    private String date;
    private boolean status;
    private int categoryID;

    public BookDTO() {
    }

    public BookDTO(int bookID, String bookName, String images, float price, int quantity, String date, boolean status, int categoryID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.images = images;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.categoryID = categoryID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    
}
