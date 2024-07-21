package com.example.bankingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Login {
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Id
		private Long lId;
		private String usernameOrEmail;
	    private String password;
		public Long getlId() {
			return lId;
		}
		public void setlId(Long lId) {
			this.lId = lId;
		}
		public String getUsernameOrEmail() {
			return usernameOrEmail;
		}
		public void setUsernameOrEmail(String usernameOrEmail) {
			this.usernameOrEmail = usernameOrEmail;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "Login [lId=" + lId + ", usernameOrEmail=" + usernameOrEmail + ", password=" + password + "]";
		}
		public Login(Long lId, String usernameOrEmail, String password) {
			super();
			this.lId = lId;
			this.usernameOrEmail = usernameOrEmail;
			this.password = password;
		}
		public Login() {
			super();
		}
	    
	

}
