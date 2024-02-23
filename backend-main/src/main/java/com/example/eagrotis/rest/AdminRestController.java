package com.example.eagrotis.rest;

import com.example.eagrotis.dao.EmployeeDAO;
import com.example.eagrotis.dao.FarmerDAO;
import com.example.eagrotis.dao.UserDAO;
import com.example.eagrotis.dto.UserDTO;
import com.example.eagrotis.entity.Employee;
import com.example.eagrotis.entity.Farmer;
import com.example.eagrotis.entity.Role;
import com.example.eagrotis.entity.User;
import com.example.eagrotis.payload.request.UpdateRequest;
import com.example.eagrotis.payload.response.MessageResponse;
import com.example.eagrotis.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Admin")
public class AdminRestController {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private FarmerDAO farmerDao;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private RoleRepository roleRepository;



    // ----- Users ----- //

    // ----- Get all employees ----- //
    @GetMapping("/users")
    public List<Object[]> getUsers(){return userDao.getUsers(); }

    // ----- User from Id ----- //
    @GetMapping( "/users/{userId}")
    @ResponseBody
    public UserDTO getUser(@PathVariable Long userId) {
        return userDao.getUserDTO(userId);
    }
    /*    It doesn't display roles // Old version(works)
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userDao.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    // ----- Update user ----- //
    @PutMapping("/users/update-user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,@Valid @RequestBody UpdateRequest updateRequest) {
        try {
            // Retrieve the existing user
            User existingUser = userDao.getUser(userId);

            if (existingUser != null) {
                // Update user details if provided
                if (updateRequest.getUsername() != null) {
                    existingUser.setUsername(updateRequest.getUsername());
                }
                if (updateRequest.getName() != null) {
                    existingUser.setName(updateRequest.getName());
                }
                if (updateRequest.getBirthDate() != null) {
                    existingUser.setBirthDate(updateRequest.getBirthDate());
                }

                // Update user role if provided
                String role = updateRequest.getRole();
                if (role != null && !role.isEmpty()) {
                    Role userRole = roleRepository.findByName(role)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    existingUser.setRole(userRole);
                }

                // Save the updated user
                userDao.saveUser(existingUser);

                return ResponseEntity.ok(new MessageResponse("User " + userId + " updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("User with ID " + userId + " not found"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error updating user: " + e.getMessage()));
        }
    }


    // ----- Delete user
    @DeleteMapping("/users/delete-user/{userId}")
    public MessageResponse deleteUser(@PathVariable Long userId) {
        try{
            userDao.deleteUser(userId);

            return new MessageResponse("User "+ userId +" deleted successfully");
        } catch (Exception e) {
            return new MessageResponse("Error deleting user: " + e.getMessage());
        }

    }

    /*// Placeholder for setting/updating user's role (modify as needed)
    @PostMapping("/set-user-role/{userId}")
    public ResponseEntity<?> setUserRole(@Valid @RequestBody UpdateRequest updateRequest) {
        try {
            Long userId = updateRequest.getId();

            User updatingUser = userDao.getUser(userId);

            userDao.saveUser()

            return ResponseEntity.ok(new MessageResponse("User "+ userId +" deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error deleting user: " + e.getMessage()));
        }
    }*/



    // ----- Employees ----- //

    // ----- Get all employees ----- //
    @GetMapping("/employees")
    public List<Employee> getEmployees(){return employeeDAO.getEmployees(); }

    // ----- Employee from Id ----- //
    @GetMapping( "/employees/{employeeId}")
    @ResponseBody
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }

    // ----- Update employee ----- //
    @PutMapping("/employees/update-employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId,@Valid @RequestBody UpdateRequest updateRequest) {
        try {
            // Assuming that signUpRequest includes user ID
            //Long employeeId = updateRequest.getId();

            // Retrieve the existing user
            Employee existingEmployee = employeeDAO.getEmployee(employeeId);

            if (existingEmployee != null) {
                // Update user details if provided
                if (updateRequest.getUsername() != null) {
                    existingEmployee.setUsername(updateRequest.getUsername());
                }
                if (updateRequest.getName() != null) {
                    existingEmployee.setName(updateRequest.getName());
                }
                if (updateRequest.getBirthDate() != null) {
                    existingEmployee.setBirthDate(updateRequest.getBirthDate());
                }

                // Save the updated user
                employeeDAO.saveEmployee(existingEmployee);

                return ResponseEntity.ok(new MessageResponse("Employee " + employeeId + " updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Employee with ID " + employeeId + " not found"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error updating employee: " + e.getMessage()));
        }
    }

    // ----- Delete employee ----- //
    @DeleteMapping("/employees/delete-employee/{employeeId}")
    public MessageResponse deleteEmployee(@PathVariable Long employeeId) {
        try{
            employeeDAO.deleteEmployee(employeeId);

            return new MessageResponse("Employee "+ employeeId +" deleted successfully");
        } catch (Exception e) {
            return new MessageResponse("Error deleting employee: " + e.getMessage());
        }

    }



    // ----- Farmers ----- //

    // ----- Get all farmers ----- //
    @GetMapping("/farmers")
    public List<Farmer> getFarmers(){return farmerDao.getFarmers(); }

    // ----- Employee from Id ----- //
    @GetMapping( "/farmers/{farmerId}")
    @ResponseBody
    public Farmer getFarmer(@PathVariable Long farmerId) {
        return farmerDao.getFarmer(farmerId);
    }

    // ----- Update farmer ----- //
    @PutMapping("/farmers/update-farmer/{userId}")
    public ResponseEntity<?> updateFarmer(@PathVariable Long userId,@Valid @RequestBody UpdateRequest updateRequest) {
        /*try {
            // Assuming that signUpRequest includes user ID
            //Long farmerId = updateRequest.getId();

            // Retrieve the existing user
            Farmer existingFarmer = farmerDao.getFarmer(farmerId);

            if (existingFarmer != null) {
                // Update user details if provided
                if (updateRequest.getUsername() != null) {
                    existingFarmer.setUsername(updateRequest.getUsername());
                }
                if (updateRequest.getName() != null) {
                    existingFarmer.setName(updateRequest.getName());
                }
                if (updateRequest.getBirthDate() != null) {
                    existingFarmer.setBirthDate(updateRequest.getBirthDate());
                }

                // Save the updated user
                farmerDao.saveFarmer(existingFarmer);

                return ResponseEntity.ok(new MessageResponse("Farmer " + farmerId + " updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Farmer with ID " + farmerId + " not found"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error updating farmer: " + e.getMessage()));
        }

        try {
            // Retrieve the existing user
            Farmer existingFarmer = farmerDao.getFarmer(farmerId);

            if (existingFarmer != null) {
                // Update user details if provided
                if (updateRequest.getUsername() != null) {
                    existingFarmer.setUsername(updateRequest.getUsername());
                }
                if (updateRequest.getName() != null) {
                    existingFarmer.setName(updateRequest.getName());
                }
                if (updateRequest.getBirthDate() != null) {
                    existingFarmer.setBirthDate(updateRequest.getBirthDate());
                }

                // Update user role if provided
                String role = updateRequest.getRole();
                if (role != null && !role.isEmpty()) {
                    Role userRole = roleRepository.findByName(role)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    existingFarmer.setRole(userRole);
                }

                // Save the updated user
                farmerDao.saveFarmer(existingFarmer);

                return ResponseEntity.ok(new MessageResponse("Farmer " + farmerId + " updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Farmer with ID " + farmerId + " not found"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error updating farmer: " + e.getMessage()));
        }*/

        try {
            // Retrieve the existing user
            User existingUser = userDao.getUser(userId);

            if (existingUser != null) {
                // Update user details if provided
                if (updateRequest.getUsername() != null) {
                    existingUser.setUsername(updateRequest.getUsername());
                }
                if (updateRequest.getName() != null) {
                    existingUser.setName(updateRequest.getName());
                }
                if (updateRequest.getBirthDate() != null) {
                    existingUser.setBirthDate(updateRequest.getBirthDate());
                }

                // Update user role if provided
                String role = updateRequest.getRole();
                if (role != null && !role.isEmpty()) {
                    Role userRole = roleRepository.findByName(role)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    existingUser.setRole(userRole);
                }

                // Save the updated user
                userDao.saveUser(existingUser);

                return ResponseEntity.ok(new MessageResponse("User " + userId + " updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("User with ID " + userId + " not found"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error updating user: " + e.getMessage()));
        }

    }

    // ----- Delete farmer ----- //
    @DeleteMapping("/farmers/delete-farmer/{farmerId}")
    public MessageResponse deleteFarmer(@PathVariable Long farmerId) {
        try{
            farmerDao.deleteFarmer(farmerId);

            return new MessageResponse("Farmer "+ farmerId +" deleted successfully");
        } catch (Exception e) {
            return new MessageResponse("Error deleting farmer: " + e.getMessage());
        }

    }


}
