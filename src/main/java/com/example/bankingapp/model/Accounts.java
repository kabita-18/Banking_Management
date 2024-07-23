package com.example.bankingapp.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "accountNumber_seq", sequenceName = "accountNumber_seq", initialValue = 100000220)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountNumber_seq")
    @Column(name = "account_number", unique = true, nullable = false)
    private Long accountNumber;

    @Column(name = "account_holder_name", nullable = false)
    @Size(min = 1, max = 20)
    private String accountHolderName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DOB")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth = new Date();

    @Column(name = "amount", nullable = false)
    private double balance;

    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "mobile_number")
    @Size(min = 10, max = 10)
    private String mobileNumber;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate currentDate = LocalDate.now();

    @Column(name = "address")
    @Size(min = 5, max = 100, message = "Address should have a length between 5 and 100 characters.")
    private String address;

    public Accounts() {
        super();
    }

    public Accounts(Long accountNumber, String accountHolderName, Date dateOfBirth, double balance,
            @Email(message = "Invalid email address") String email, String mobileNumber, LocalDate currentDate,
            String address) {
        super();
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.currentDate = currentDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Accounts [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
                + ", dateOfBirth=" + dateOfBirth + ", balance=" + balance + ", email=" + email + ", mobileNumber="
                + mobileNumber + ", currentDate=" + currentDate + ", address=" + address + "]";
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
