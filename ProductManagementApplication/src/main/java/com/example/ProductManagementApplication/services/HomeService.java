package com.example.ProductManagementApplication.services;

import com.example.ProductManagementApplication.props.Products;
import com.example.ProductManagementApplication.utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeService {

    public List<Products> getProducts() {
        List<Products> productsList = new ArrayList<>();

        DB db = new DB();

        try {
            String sql = "select * from product where status = 1 order by pid desc";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Products p = new Products();
                p.setPid(rs.getInt("pid"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getInt("price"));
                p.setStock(rs.getInt("stock"));
                p.setDetails(rs.getString("details"));
                productsList.add(p);
            }
        } catch (Exception ex) {
            System.err.println("get Products Error: " + ex);
        } finally {
            db.close();
        }
        return productsList;
    }
}








