package com.developersview.springsecurityjwt.controller;

import com.developersview.springsecurityjwt.dto.AuthRequest;
import com.developersview.springsecurityjwt.dto.Product;
import com.developersview.springsecurityjwt.service.JwtService;
import com.developersview.springsecurityjwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spring-jwt/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome (){
        return "Welcome!..This endpoint is not secure.";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id){
        return productService.getProduct(id);
    }

    @PostMapping("/authentication")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user name ");
        }

    }
}