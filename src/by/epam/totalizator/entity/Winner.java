package by.epam.totalizator.entity;

import java.io.Serializable;

public class Winner implements Serializable {
	
	private static final long serialVersionUID =  1L;
	
	private int idUser;
	private double amount;
	private double coefficient;
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
	    
		Winner other = (Winner) obj;
	    
		if (idUser != other.idUser) return false;
		if (amount != other.amount) return false;
		if (coefficient != other.coefficient) return false;
		return true;
	 }
	
	@Override
	public int hashCode() {
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(coefficient);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + idUser;
		return result;
	}
	
	 @Override
	 public String toString() {
		 return getClass().getName() + "@" + "idUser : " + idUser + ", amount : " + amount
				 + ", coefficient : " + coefficient;
	}

	
}
