package com.example.wordsfilter.service.impl;

import com.example.wordsfilter.dto.ProductDto;
import com.example.wordsfilter.entity.Product;
import com.example.wordsfilter.entity.User;
import com.example.wordsfilter.repository.ProductRepo;
import com.example.wordsfilter.repository.UserRepo;
import com.example.wordsfilter.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepo productRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
         String email = userDetails.getUsername();

         Optional<User> optionalUser = userRepo.findByEmail(email);

         if (optionalUser.isEmpty()) {
             throw  new AuthorizationServiceException("Invalid user");
         }

         User user = optionalUser.get();

         Product product = modelMapper.map(productDto, Product.class);
         product.setUser(user);

         user.addProduct(product);
         Product savedProduct = productRepo.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }
}
