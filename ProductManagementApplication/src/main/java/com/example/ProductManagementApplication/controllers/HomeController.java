package com.example.ProductManagementApplication.controllers;

import com.example.ProductManagementApplication.services.HomeService;
import com.example.ProductManagementApplication.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    HomeService service = new HomeService();
    ProductService productService = new ProductService();
    int status = -1;
    String message = "";
    int pid = 0;

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("product", service.getProducts());
        model.addAttribute("status",status);
        model.addAttribute("message", message);
        model.addAttribute("pid",pid);
        // Reset status, message, and uid variables
        status = -1;
        message = "";
        pid = 0;

        return "home";

    }
    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable int pid){
        status = productService.productDelete(pid,0);
        if (status > 0) {
            message = "Delete Success : " + pid;
            this.pid=pid;


        }else {
            message = "Delete Fail - " + pid;
        }
        return "redirect:/";
    }

    @GetMapping("/addProduct")
    public String addProduct(){

        return "productSettings";
    }
}