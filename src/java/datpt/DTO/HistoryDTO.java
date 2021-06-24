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
public class HistoryDTO implements Serializable{
    private String date;
    private String bookName;
    private int quantity;
    private float price;
    private String images;

    public HistoryDTO() {
    }

    public HistoryDTO(String date, String bookName, int quantity, float price, String images) {
        this.date = date;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
