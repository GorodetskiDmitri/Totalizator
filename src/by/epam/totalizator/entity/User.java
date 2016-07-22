package by.epam.totalizator.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID =  1L;

	private int id;
	private String status;
	private String login;
	private int password;
	private double balance;
	private String name;
	private String surname;
	private String email;
	private String address;
	private String phone;
	private String passport;
	private Date dateOfBirth;
	private String betAllow;
	
	public User(){
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBetAllow() {
		return betAllow;
	}

	public void setBetAllow(String betAllow) {
		this.betAllow = betAllow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj) return true;
	     if (obj == null || getClass() != obj.getClass()) return false;
	        
	     User other = (User) obj;
	        
	     if (id != other.id) return false;
	     if (status != null ? !status.equals(other.status) : other.status != null) return false;
	     if (login != null ? !login.equals(other.login) : other.login != null) return false;
	     if (password != other.password) return false;
	     if (balance != other.balance) return false;
	     if (name != null ? !name.equals(other.name) : other.name != null) return false;
	     if (surname != null ? !surname.equals(other.surname) : other.surname != null) return false;
	     if (email != null ? !email.equals(other.email) : other.email != null) return false;
	     if (address != null ? !address.equals(other.address) : other.address != null) return false;
	     if (phone != null ? !phone.equals(other.phone) : other.phone != null) return false;
	     if (passport != null ? !passport.equals(other.passport) : other.passport != null) return false;
	     if (dateOfBirth != null ? !dateOfBirth.equals(other.dateOfBirth) : other.dateOfBirth != null) return false;
	     if (betAllow != null ? !betAllow.equals(other.betAllow) : other.betAllow != null) return false;
	     return true;
	 }   
	 
	 @Override
	 public int hashCode() {
		 int result = 1;
	     result = 31 * id;
	     result = 31 * result + (status != null ? status.hashCode() : 0);
	     result = 31 * result + (login != null ? login.hashCode() : 0);
	     result = 31 * result + password;
	     result = 31 * result + (int)balance;
	     result = 31 * result + (name != null ? name.hashCode() : 0);
	     result = 31 * result + (surname != null ? surname.hashCode() : 0);
	     result = 31 * result + (email != null ? email.hashCode() : 0);
	     result = 31 * result + (address != null ? address.hashCode() : 0);
	     result = 31 * result + (phone != null ? phone.hashCode() : 0);
	     result = 31 * result + (passport != null ? passport.hashCode() : 0);
	     result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
	     result = 31 * result + (betAllow != null ? betAllow.hashCode() : 0);
	     return result;
	 }

	 @Override
	 public String toString() {
		 return getClass().getName() + "@" + "id : " + id + ", status : " + status +
	    			", login : " + login + ", password : " + password +
	    			", balance : " + balance + ", name : " + name +
	    			", surname : " + surname + ", email : " + email +
	    			", address : " + address + ", phone : " + phone +
	    			", passport : " + passport + ", dateOfBirth : " + dateOfBirth +
	    			", betAllow : " + betAllow;
	}
}

