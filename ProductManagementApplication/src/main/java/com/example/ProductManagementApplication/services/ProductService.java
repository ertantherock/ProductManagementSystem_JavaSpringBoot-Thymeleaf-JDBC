package com.example.ProductManagementApplication.services;

import com.example.ProductManagementApplication.props.Products;
import com.example.ProductManagementApplication.utils.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public int addProduct(Products products) {
        int status = 0;

        DB db = new DB();
        try{
            String sql = "insert into product(pid,title,price,details,stock,status) values (NULL, ?, ?, ?,?,1)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,products.getTitle());
            pre.setInt(2,products.getPrice());
            pre.setString(3, products.getDetails());
            pre.setInt(4,products.getStock());
            status = pre.executeUpdate();
        }catch (Exception ex){
            System.err.println("Add Product Error -- : " + ex);
        } finally {
            db.close();
        }

        return status;
    }

    public List<Products> getProductInfo(int pid){

        List<Products> productsList = new ArrayList<>();
        DB db = new DB();

        try {
            String sql = "select * from product where pid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,pid);
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

        }catch (Exception ex) {
            System.err.println("ProductInfo Error --: "+ ex);

        }finally {
            db.close();
        }
        return productsList;
    }

    public int updateProduct(Products products){
        int status = 0;
        DB db = new DB();

        try {
            String sql = "update product set title = ?, price = ?, details = ?, stock = ? where pid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, products.getTitle());
            pre.setInt(2,products.getPrice());
            pre.setString(3,products.getDetails());
            pre.setInt(4,products.getStock());
            pre.setInt(5,products.getPid());
            status =pre.executeUpdate();
        }catch (Exception ex) {
            System.err.println("Update Product Error -- : "+ex);
        }finally {
            db.close();
        }
        return status;
    }
    public int productDelete(int pid,int dbStatus){
        DB db = new DB();
        int status = 0;


        try {
            String sql = "update product set status = ?  where pid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,dbStatus);
            pre.setInt(2,pid);
            status = pre.executeUpdate();

        }catch (Exception ex) {
            System.err.println("Product Delete Error: "+ ex);
        }finally {
            db.close();
        }
        return status;

    }



}
