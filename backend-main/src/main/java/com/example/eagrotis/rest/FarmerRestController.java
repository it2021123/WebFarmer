package com.example.eagrotis.rest;

import com.example.eagrotis.entity.*;
import com.example.eagrotis.payload.request.SumbitRequest;
import com.example.eagrotis.payload.response.MessageResponse;
import com.example.eagrotis.repository.ApplicationRepository;
import com.example.eagrotis.repository.FarmerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Farmer")
public class FarmerRestController {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private FarmerRepository farmerRepository;

    //------- Submit (actual data at the database) -------//

    @PutMapping("/submitApplication")
    public ResponseEntity<?> sumbitApplication(@Valid @RequestBody SumbitRequest sumbitRequest) {
        if (applicationRepository.existsByAgriculturalCooperativeName(sumbitRequest.getAgriculturalCooperativeName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }


        // Create new application
        Application application = new Application(sumbitRequest.getAgriculturalCooperativeName(),
                sumbitRequest.getMembers(),
                sumbitRequest.getProducts(),
                sumbitRequest.getCultivation_area());

        String farmerUserName = sumbitRequest.getFarmer();
        application.setStatus(Application.statusEnum.UNSEEN);
        // The 3 lines of code below
        applicationRepository.save(application);

        Long applicationId = application.getId();
        application = applicationRepository.getApplicationById(applicationId);

        if (farmerUserName == null) {
            throw new RuntimeException("Farmer not exist");
        }else {
            Farmer farmer = farmerRepository.findByUsername(farmerUserName);
            if (farmer == null) {
                throw new RuntimeException("Farmer is null");
            } else {
                application.setFarmer(farmer);
            }
        }
            applicationRepository.save(application);





        return ResponseEntity.ok(new MessageResponse("Application submitted successfully!"));
    }


    @GetMapping("/get/not")
    public List<Application> notifications(){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Farmer farmer = farmerRepository.findByUsername(username);
        return applicationRepository.findByFarmer(farmer);


    }


}



