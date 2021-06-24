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
public class DiscountDTO implements Serializable{
    private int discountID;
    private String discountName;
    private String keyWord;
    private int discountPercent;
    private String beginDate;
    private String endDate;

    public DiscountDTO() {
    }

    public DiscountDTO(int discountID, String discountName, String keyWord, int discountPercent, String beginDate, String endDate) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.keyWord = keyWord;
        this.discountPercent = discountPercent;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
