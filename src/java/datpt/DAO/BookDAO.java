/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.DAO;

import datpt.DTO.BookDTO;
import datpt.Ulities.DBHelper;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BookDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<BookDTO> getAllBook() throws SQLException, ClassNotFoundException {
        List<BookDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID\n"
                    + "from tblBook\n"
                    + "where status = 1 and quantity > 0";
            if (con != null) {
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("BookID");
                    String bookName = rs.getString("BookName");
                    String images = rs.getString("image");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("quantity");
                    String date = rs.getString("CurrentDate");
                    boolean status = rs.getBoolean("Status");
                    int cateID = rs.getInt("CategoryID");

                    list.add(new BookDTO(bookID, bookName, images, price, quantity, date, status, cateID));
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

    public int removeBookStore(int id) throws SQLException, ClassNotFoundException {
        try {
            con = DBHelper.makeConnection();
            String sql = "update tblBook\n"
                    + "set Status = 0\n"
                    + "where BookID = ?";
            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);

                int result = pst.executeUpdate();
                if (result > 0) {
                    return result;
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

    public int editBookStore(int bookID, String bookName, String images, float price, int cateID) throws SQLException, ClassNotFoundException {
        try {
            con = DBHelper.makeConnection();
            String sql = "update tblBook\n"
                    + "set BookName = ?, image = ?, Price = ?, CategoryID = ?\n"
                    + "where BookID = ?";
            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setString(1, bookName);
                pst.setString(2, images);
                pst.setFloat(3, price);
                pst.setInt(4, cateID);
                pst.setInt(5, bookID);

                int result = pst.executeUpdate();
                if (result > 0) {
                    return result;
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

    public int insertBookStore(String bookName, String images, float price, int quantity, boolean status, int cateID) throws SQLException, ClassNotFoundException, ParseException {
        try {
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            con = DBHelper.makeConnection();
            String sql = "insert into tblBook(BookName,image,Price,quantity,CurrentDate,Status,CategoryID)\n"
                    + "values (?,?,?,?,?,?,?)";
            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setString(1, bookName);
                pst.setString(2, images);
                pst.setFloat(3, price);
                pst.setInt(4, quantity);
                pst.setDate(5, sqlDate);
                pst.setBoolean(6, status);
                pst.setInt(7, cateID);

                int result = pst.executeUpdate();
                if (result > 0) {
                    return result;
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

    public BookDTO getBookByID(int id) throws SQLException, ClassNotFoundException {
        BookDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID\n"
                    + "from tblBook\n"
                    + "where BookID = ?";
            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);

                rs = pst.executeQuery();
                if (rs.next()) {
                    int bookID = rs.getInt("BookID");
                    String bookName = rs.getString("BookName");
                    String images = rs.getString("image");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("quantity");
                    String date = rs.getString("CurrentDate");
                    boolean status = rs.getBoolean("Status");
                    int cateID = rs.getInt("CategoryID");

                    dto = new BookDTO(bookID, bookName, images, price, quantity, date, status, cateID);

                    return dto;
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
        return dto;
    }

    public int setQuantityBook(int bookID, int quantity) throws SQLException, ClassNotFoundException {
        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "update tblBook\n"
                        + "set quantity = quantity - ?\n"
                        + "where BookID = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quantity);
                pst.setInt(2, bookID);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return result;
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

    public List<BookDTO> getBookByPrice(int num1, int num2) throws SQLException, ClassNotFoundException {
        List<BookDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID\n"
                        + "from tblBook\n"
                        + "where Status = 1 and Price between ? and ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, num1);
                pst.setInt(2, num2);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("BookID");
                    String bookName = rs.getString("BookName");
                    String images = rs.getString("image");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("quantity");
                    String date = rs.getString("CurrentDate");
                    boolean status = rs.getBoolean("Status");
                    int cateID = rs.getInt("CategoryID");

                    list.add(new BookDTO(bookID, bookName, images, price, quantity, date, status, cateID));
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

    public List<BookDTO> getBookByName(String searchValue) throws SQLException, ClassNotFoundException {
        List<BookDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID\n"
                        + "from tblBook\n"
                        + "where Status = 1 and BookName like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1,"%" +searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("BookID");
                    String bookName = rs.getString("BookName");
                    String images = rs.getString("image");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("quantity");
                    String date = rs.getString("CurrentDate");
                    boolean status = rs.getBoolean("Status");
                    int cateID = rs.getInt("CategoryID");

                    list.add(new BookDTO(bookID, bookName, images, price, quantity, date, status, cateID));
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

}
