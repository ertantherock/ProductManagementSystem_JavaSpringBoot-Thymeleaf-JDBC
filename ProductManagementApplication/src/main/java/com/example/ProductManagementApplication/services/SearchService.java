package com.example.ProductManagementApplication.services;

import com.example.ProductManagementApplication.props.Products;
import com.example.ProductManagementApplication.utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    DB db = new DB();

    public List<Products> getSearch(String q){

        List<Products> getProductSearch = new ArrayList<>();

        try {
            q = "%" + q + "%";
            String sql = "select * from product where title like ? or details like ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,q);
            pre.setString(2,q);
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Products products = new Products();
                products.setPid(rs.getInt("pid"));
                products.setStock(rs.getInt("stock"));
                products.setPrice(rs.getInt("price"));
                products.setDetails(rs.getString("details"));
                products.setTitle(rs.getString("title"));
                getProductSearch.add(products);
            }
        }catch (Exception ex) {
            System.err.println("get Search Error: "+ ex);
        } finally {
            db.close();
        }


        return getProductSearch;
    }
}
