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
public class UserDTO implements Serializable{
    private int userID;
    private String userName;
    private String password;
    private String address;
    private String phone;
    private String roleID;

    public UserDTO() {
    }

    public UserDTO(int userID, String userName, String password, String address, String phone, String roleID) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    
}
