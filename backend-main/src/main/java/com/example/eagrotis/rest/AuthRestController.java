package com.example.eagrotis.rest;


import com.example.eagrotis.config.AuthTokenFilter;
import com.example.eagrotis.config.JwtUtils;
import com.example.eagrotis.entity.*;
import com.example.eagrotis.payload.request.LoginRequest;
import com.example.eagrotis.payload.request.SignupRequest;
import com.example.eagrotis.payload.response.JwtResponse;
import com.example.eagrotis.payload.response.MessageResponse;
import com.example.eagrotis.repository.*;
import com.example.eagrotis.service.UserDetailsImpl;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    List<String> members = new ArrayList<>();
    List<String> products = new ArrayList<>();;
    List<String> areas = new ArrayList<>();;

    @Autowired
    public AuthRestController(Set<String> tokenBlacklist) {
        this.tokenBlacklist = tokenBlacklist;
    }
    
    private final Set<String> tokenBlacklist;



    // ----- Create the roles of the roles table if not exist ----- //
    @PostConstruct
    public void setup() {
        // Automatic store of roles at the DB
        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_ADMIN"));
            return null;
        });
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_USER"));
            return null;
        });
        roleRepository.findByName("ROLE_FARMER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_FARMER"));
            return null;
        });
        roleRepository.findByName("ROLE_EMPLOYEE").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_EMPLOYEE"));
            return null;
        });

        // Basic admin of the app
        if(adminRepository.existsByName("Administrator")){

        }else {
            Admin admin = new Admin("systemAdmin", "Administrator", encoder.encode("systemAdmin"), null);
            Role role = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error creating admin"));
            admin.setRole(role);
            adminRepository.save(admin);
        }
        if(userRepository.existsByName("SystemUser")){

        }else {
            User user = new User("systemUser", "SystemUser", encoder.encode("systemUser"), null);
            Role role = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error creating test user"));
            user.setRole(role);
            userRepository.save(user);
        }
        if(farmerRepository.existsByName("SystemFarmer")){

        }else{
            Farmer farmer = new Farmer("systemFarmer","SystemFarmer",encoder.encode("systemFarmer"),null);
            Role role = roleRepository.findByName("ROLE_FARMER")
                    .orElseThrow(() -> new RuntimeException("Error creating test farmer"));
            farmer.setRole(role);
            farmerRepository.save(farmer);
        }
        if(employeeRepository.existsByName("SystemEmployee")){

        }else{
            Employee employee = new Employee("systemEmployee","SystemEmployee",encoder.encode("systemEmployee"),null);
            Role role = roleRepository.findByName("ROLE_EMPLOYEE")
                    .orElseThrow(() -> new RuntimeException("Error creating employee"));
            employee.setRole(role);
            employeeRepository.save(employee);
        }
        
        
        
        if(farmerRepository.existsByName("SystemFarmer")){
            if (applicationRepository.existsByAgriculturalCooperativeName("Leonidio")) {

            }else{

                members.add("Giorgos");
                members.add("Panos");
                members.add("Kostas");

                products.add("milk");
                products.add("apples");
                products.add("pineapples");

                areas.add("Athens");
                areas.add("Thesaloniki");
                areas.add("Smirni");




                // Create new application
                Application application = new Application("Leonidio", members, products, areas );
                String farmerUserName = "systemFarmer";
                application.setStatus(Application.statusEnum.UNSEEN);
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

            }

        }else{
            throw new RuntimeException("Farmer is null");
        }

        if(farmerRepository.existsByName("SystemFarmer")){
            if (applicationRepository.existsByAgriculturalCooperativeName("Pera Melana")) {

            }else{
                members.clear();
                members.add("Giorgos");
                members.add("Panos");
                members.add("Kostas");

                products.clear();
                products.add("meli");
                products.add("elies");
                products.add("pineapples");

                areas.clear();
                areas.add("Athens");
                areas.add("Thesaloniki");
                areas.add("Smirni");




                // Create new application
                Application application = new Application("Pera Melana", members, products, areas );
                String farmerUserName = "systemFarmer";
                application.setStatus(Application.statusEnum.UNSEEN);
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

            }

        }else{
            throw new RuntimeException("Farmer is null");
        }


    }




    //------- Login (actual authentication of user) -------//

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("authentication");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("authentication: " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("post authentication");
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("jw: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);


        JwtResponse JwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getBirthDate(),
                role);

        return ResponseEntity.ok(JwtResponse);
    }





    //------- Register (actual data at the database) -------//

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already in use!"));
        }


        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getBirthDate());

        String strRole = signUpRequest.getRole();


        if (strRole == null) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setRole(userRole);
        }  else {

            switch (strRole) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(adminRole);
                    Admin admin = new Admin(user.getUsername(), user.getName(), user.getPassword(), user.getBirthDate());
                    admin.setRole(user.getRole());
                    adminRepository.save(admin);
                    break;
                case "ROLE_FARMER":
                    Role farmerRole = roleRepository.findByName("ROLE_FARMER")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(farmerRole);
                    Farmer farmer = new Farmer(user.getUsername(), user.getName(), user.getPassword(), user.getBirthDate());
                    farmer.setRole(user.getRole());
                    farmerRepository.save(farmer);
                    break;
                case "ROLE_EMPLOYEE":
                    Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(employeeRole);
                    Employee employee = new Employee(user.getUsername(), user.getName(), user.getPassword(), user.getBirthDate());
                    employee.setRole(user.getRole());
                    employeeRepository.save(employee);
                    break;
                default:
                    Role userRole = roleRepository.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    user.setRole(userRole);
                    userRepository.save(user);
            }
        }



        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }








    /*// Logout endpoint for invalidating a specific token
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Extract the token from the Authorization header
        String token = authTokenFilter.extractToken(request);

        if (token != null) {
            // Add the token to the blacklist
            tokenBlacklist.add(token);
            return ResponseEntity.ok(new MessageResponse("Logout successful"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid or missing token"));
        }
    }*/




    //------- Register (actual data at the database) -------//

    @PostMapping("/farmerSignUp")
    public ResponseEntity<?> registerFarmer(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already in use!"));
        }


        // Create new user's account
        Farmer farmer = new Farmer(signUpRequest.getUsername(),
                signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getBirthDate());

        Role role = roleRepository.findByName("ROLE_FARMER")
                .orElseThrow(() -> new RuntimeException("Error creating farmer"));
        farmer.setRole(role);

        farmerRepository.save(farmer);

        return ResponseEntity.ok(new MessageResponse("Farmer registered successfully!"));
    }

}
