package com.example.bankingapp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account", referencedColumnName = "account_number")
    private Accounts fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account", referencedColumnName = "account_number")
    private Accounts toAccount;

    private double transactionAmount;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    

    public Transactions(int transactionId, Accounts fromAccount, Accounts toAccount, double transactionAmount,
			String status, Date timestamp) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionAmount = transactionAmount;
		this.status = status;
		this.timestamp = timestamp;
	}

	public Transactions() {
        super();
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Accounts getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Accounts fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Accounts getToAccount() {
        return toAccount;
    }

    public void setToAccount(Accounts toAccount) {
        this.toAccount = toAccount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transactions [transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount="
                + toAccount + ", transactionAmount=" + transactionAmount + ", status=" + status + ", timestamp="
                + timestamp + "]";
    }
}
