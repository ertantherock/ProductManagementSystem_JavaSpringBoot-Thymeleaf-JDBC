package com.example.ProductManagementApplication.controllers;

import com.example.ProductManagementApplication.props.Products;
import com.example.ProductManagementApplication.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    ProductService productService = new ProductService();

    int status = -1;
    String message = "";


    @GetMapping("/productSettings")
    public String productSettings(){

        return "productSettings";
    }

    @PostMapping("/addProduct")
    public String addProduct(Products products){
        ProductService productService = new ProductService();
        int status = productService.addProduct(products);

        if (status>0){
            return "redirect:/";
        }

        return "productSettings";


    }

    @GetMapping("/productInfo/{uid}")
    public String productInfo(@PathVariable int uid, Model model){

        model.addAttribute("product",productService.getProductInfo(uid));

        return "productInfo";
    }

    @PostMapping("/productUpdate")
    public String productUpdate(Products products){
        productService.updateProduct(products);
        return "redirect:/";
    }



}
