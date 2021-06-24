/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datpt.DAO;

import datpt.DTO.BookDTO;
import datpt.DTO.CategoryDTO;
import datpt.Ulities.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException {
        List<CategoryDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select CategoryID,CategoryName\n"
                    + "from tblCategory";
            if (con != null) {
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String name = rs.getString("CategoryName");

                    list.add(new CategoryDTO(id, name));
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

    public List<BookDTO> loadProductByCategoryId(int cateId) throws SQLException, ClassNotFoundException {
        List<BookDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            String sql = "select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID\n"
                    + "from tblBook\n"
                    + "where CategoryID = ?";
            if (con != null) {
                pst = con.prepareStatement(sql);
                pst.setInt(1, cateId);
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
