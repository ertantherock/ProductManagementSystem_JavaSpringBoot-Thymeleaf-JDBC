package com.example.ProductManagementApplication.controllers;

import com.example.ProductManagementApplication.props.Products;
import com.example.ProductManagementApplication.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    SearchService searchService = new SearchService();

    // Maps GET requests with the "/search" URL path to this method
    // @RequestParam is used to check the variables sent in the searches
    // defaultValue is used to avoid errors when a user does not send a search query
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String q, Model model){

        // call the search method of the SearchService class and retrieve the list of users
        List<Products> productLs = searchService.getSearch(q);

        // add the list of users and the search query parameter to the model
        model.addAttribute("product", productLs);
        model.addAttribute("q", q);

        return "productSearch";
    }

}
