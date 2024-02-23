package com.example.eagrotis.rest;

import com.example.eagrotis.config.AuthTokenFilter;
import com.example.eagrotis.config.JwtUtils;
import com.example.eagrotis.entity.Application;
import com.example.eagrotis.entity.Employee;
import com.example.eagrotis.payload.request.CheckRequest;
import com.example.eagrotis.payload.request.LoginRequest;
import com.example.eagrotis.payload.request.SumbitRequest;
import com.example.eagrotis.payload.response.MessageResponse;
import com.example.eagrotis.repository.ApplicationRepository;
import com.example.eagrotis.repository.EmployeeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/Employee")
public class EmployeeRestController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



    @GetMapping("/applications")
    public List<Application> getAllApplications(){
        return applicationRepository.findAll();
    }

    @GetMapping("/application/{applicationId}")
    public Application getApplication(@PathVariable Long applicationId){

        return applicationRepository.getApplicationById(applicationId);

    }

    @PutMapping("/application/approval/{applicationId}")
    public ResponseEntity<?> examineApplication(@PathVariable Long applicationId, @Valid @RequestBody CheckRequest checkRequest){
        try {

            // Automatically fill the employee attribute - when it will work without any errors it will be putted to FarmerRest also
            /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            */

            Application application = applicationRepository.getApplicationById(applicationId);
            if (application == null){
                throw new RuntimeException("Application not found");
            }


            Employee employee = employeeRepository.findByUsername(checkRequest.getEmployee());
            if (employee == null){
                throw new RuntimeException("Employee not found");
            }

            application.setEmployee(employee);

            application.setStatus(checkRequest.getStatus());

            application.setComments(checkRequest.getComments());

            applicationRepository.save(application);


            return ResponseEntity.ok(new MessageResponse("Application " + applicationId + " checked successfully"));

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error checking application: " + e.getMessage()));
        }

    }

}
