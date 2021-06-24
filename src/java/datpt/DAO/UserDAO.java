/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.DAO;

import datpt.DTO.DiscountDTO;
import datpt.DTO.HistoryDTO;
import datpt.DTO.UserDTO;
import datpt.Ulities.DBHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public UserDTO getAccount(String userName, String password) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select UserID,UserName,PassWord,Address,Phone,RoleID\n"
                        + "from tblUsers\n"
                        + "where UserName = ? and PassWord = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userName);
                pst.setString(2, password);

                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("UserID");
                    String name = rs.getString("UserName");
                    String address = rs.getString("Address");
                    String phone = rs.getString("Phone");
                    String roleID = rs.getString("RoleID");
                    user = new UserDTO(id, name, "********", address, phone, roleID);

                    return user;
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }

    public int createDiscount(String DName, String keyWord, int DPercent, String endDate) throws SQLException, ClassNotFoundException {

        try {

            //get Current Date
            java.util.Date date = new java.util.Date();
            java.sql.Date beginDate = new java.sql.Date(date.getTime());

            //parse String to DateSQL
            java.sql.Date endDateSQL = Date.valueOf(endDate);

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblDiscount(DName,keyword,DPercent,Begindate,Enddate,status)\n"
                        + "values (?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, DName);
                pst.setString(2, keyWord);
                pst.setInt(3, DPercent);
                pst.setDate(4, beginDate);
                pst.setDate(5, endDateSQL);
                pst.setInt(6, 1);

                int rs = pst.executeUpdate();
                if (rs > 0) {
                    return rs;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<DiscountDTO> getListDiscount() throws SQLException, ClassNotFoundException {
        List<DiscountDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select DiscountID,DName,keyword,DPercent,Begindate,Enddate,status\n"
                        + "from tblDiscount\n"
                        + "where status = 1";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int discountID = rs.getInt("DiscountID");
                    String discountName = rs.getString("DName");
                    String keyWord = rs.getString("keyword");
                    int discountPercent = rs.getInt("DPercent");
                    String beginDate = rs.getString("Begindate");
                    String endDate = rs.getString("Enddate");

                    list.add(new DiscountDTO(discountID, discountName, keyWord, discountPercent, beginDate, endDate));
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<HistoryDTO> loadHistory(int userID) throws SQLException, ClassNotFoundException {
        List<HistoryDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select o.DateOrder, d.detailPrice, d.detailQuantity, b.image , b.BookName\n"
                        + "from tblOrder o inner join tblDetail d on o.OrderID = d.OrderID, tblBook b\n"
                        + "where b.BookID = d.BookID and UserID = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String date = rs.getString("DateOrder");
                    String bookName = rs.getString("BookName");
                    int quantity = rs.getInt("detailQuantity");
                    float price = rs.getFloat("detailPrice");
                    String images = rs.getString("image");

                    list.add(new HistoryDTO(date, bookName, quantity, price, images));
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public int insertOrder(float total, int userID) throws SQLException, ClassNotFoundException {
        try {
            //get Current Date
            java.util.Date date = new java.util.Date();
            java.sql.Date beginDate = new java.sql.Date(date.getTime());

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblOrder\n"
                        + "values (?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setDate(1, beginDate);
                pst.setFloat(2, total);
                pst.setInt(3, userID);

                int rs = pst.executeUpdate();
                if (rs > 0) {
                    return rs;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;

    }

    public int getLastOrder() throws SQLException, ClassNotFoundException {
        int result = 0;
        try {        
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(OrderID) as OrderID\n"
                        + "FROM tblOrder";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    result = rs.getInt("OrderID");
                }
               
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    public int insertDetail(float detailPrice, int detailQuantity, int oderID, int bookID) throws SQLException, ClassNotFoundException {
        try {
            //get Current Date
            java.util.Date date = new java.util.Date();
            java.sql.Date beginDate = new java.sql.Date(date.getTime());

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert tblDetail (detailPrice,detailQuantity,OrderID,BookID)\n"
                        + "values (?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, detailPrice);
                pst.setInt(2, detailQuantity);
                pst.setInt(3, oderID);
                pst.setInt(4, bookID);

                int rs = pst.executeUpdate();
                if (rs > 0) {
                    return rs;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public int removeDiscount(int discountID) throws SQLException, ClassNotFoundException {
        try {
            //get Current Date
            java.util.Date date = new java.util.Date();
            java.sql.Date beginDate = new java.sql.Date(date.getTime());

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update tblDiscount\n"
                        + "set status = 0\n"
                        + "where DiscountID = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, discountID);

                int rs = pst.executeUpdate();
                if (rs > 0) {
                    return rs;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;

    }
}
