package com.example.eagrotis.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(	name = "applications",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "agricultural_cooperative_name")
        })
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agricultural_cooperative_name") // Define the column name
    private String agriculturalCooperativeName;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status")
    private statusEnum status = statusEnum.UNSEEN;
    public enum statusEnum{
        UNSEEN, DISPROVED, APPROVED
    }
    public boolean toBeApproved(){
        if (getStatus() == statusEnum.UNSEEN) {
            return true;
        }
        return false;
    }
    public statusEnum toSetStatus(int i){
        if(i==1){
            return statusEnum.DISPROVED;
        }else if(i==0){
            return statusEnum.APPROVED;
        }else{
            return statusEnum.UNSEEN;
        }
    }

    @ElementCollection
    @CollectionTable(name = "application_members", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "member_name")
    private List<String> members;

    @ElementCollection
    @CollectionTable(name = "application_products", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "products_name")
    private List<String> products;

    @ElementCollection
    @CollectionTable(name = "application_area", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "area_name")
    private List<String> cultivation_area;

    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
            name = "applications_farmers",
            joinColumns = @JoinColumn(
                    name = "application_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "farmer_id", referencedColumnName = "id"))
    private Farmer farmer;

    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinTable(
            name = "applications_employees",
            joinColumns = @JoinColumn(
                    name = "application_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "employee_id", referencedColumnName = "id"))
    private Employee employee;


    public Application(){}

    public Application(String agriculturalCooperativeName,
                       List<String> members, List<String> products, List<String> cultivationArea) {
        this.agriculturalCooperativeName = agriculturalCooperativeName;
        this.members = members;
        this.products = products;
        this.cultivation_area = cultivationArea;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgriculturalCooperativeName() {
        return agriculturalCooperativeName;
    }

    public void setAgriculturalCooperativeName(String agriculturalCooperativeName) {
        this.agriculturalCooperativeName = agriculturalCooperativeName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getCultivationArea() {
        return cultivation_area;
    }

    public void setCultivationArea(List<String> cultivation_area) {
        this.cultivation_area = cultivation_area;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}