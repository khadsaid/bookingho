package com.example.bookingho.myAPI;

import com.example.bookingho.model.Customer;
import com.example.bookingho.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")

public class CustomerApi {
    @Autowired
    public CustomerRepo customerRepo;


    @GetMapping("/Allcustomer")
    public ResponseEntity<?>getcusromer(){
        try {
            List<Customer>customerList = customerRepo.findAll();
            if (customerList.isEmpty()){
                return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(customerList,HttpStatus.ACCEPTED);
            }
        }catch (Exception exception){
            return  new ResponseEntity<>("database error",HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("/byid{custId}")
    public ResponseEntity<?>getbyId(@PathVariable int custId){
        try {
            Optional<Customer>optionalCustomer = customerRepo.findById(custId);
            if (optionalCustomer.isPresent()){
                return new ResponseEntity<>(optionalCustomer, HttpStatus.FOUND);
            }else {
                return  new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("server down",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete{custId}")
    public  ResponseEntity<?>delete(@PathVariable int custId){
        try {
            customerRepo.deleteById(custId);
            return new ResponseEntity<>("successifull",HttpStatus.ACCEPTED);

        }catch (Exception exception){
            return new ResponseEntity<>("data not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add")
    public  ResponseEntity<?>add (@RequestBody Customer customer){
        try {
            Customer customer1=customerRepo.save(customer);
            return new ResponseEntity<>("inserted succesefull",HttpStatus.ACCEPTED);
        }catch (Exception exception){
            return new ResponseEntity<>("not inserted",HttpStatus.BAD_REQUEST);
        }
    }
}
