package com.bean;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Corrected date pattern.
    private LocalDate orderplaced;

    private Integer productid; // FK

    @ManyToOne
    @JoinColumn(name = "emailid") // FK
    private Login login; // Changed from String emailid to Login login

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public LocalDate getOrderplaced() {
        return orderplaced;
    }

    public void setOrderplaced(LocalDate orderplaced) {
        this.orderplaced = orderplaced;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Login getLogin() { // Changed from String getEmailid() to Login getLogin()
        return login;
    }

    public void setLogin(Login login) { // Changed from void setEmailid(String emailid) to void setLogin(Login login)
        this.login = login;
    }
}