package by.epam.totalizator.entity;

import java.io.Serializable;
import java.util.Date;

public class Bet implements Serializable {
	
	private static final long serialVersionUID =  1L;

	private int id;
	private User user;
	private Line line;
	private Date betDate;
	private double amount;
	private String outcome;
	private String betStatus;
	
	public Bet() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Date getBetDate() {
		return betDate;
	}

	public void setBetDate(Date betDate) {
		this.betDate = betDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
	    
		Bet other = (Bet) obj;
	    
		if (user != null ? !user.equals(other.user) : other.user != null) return false;
		if (line != null ? !line.equals(other.line) : other.line != null) return false;
		if (betDate != null ? !betDate.equals(other.betDate) : other.betDate != null) return false;
		if (amount != other.amount) return false;
		if (outcome != null ? !outcome.equals(other.outcome) : other.outcome != null) return false;
		if (betStatus != null ? !betStatus.equals(other.betStatus) : other.betStatus != null) return false;
		return true;
	 }
	
	 @Override
	 public int hashCode() {
		 int result = 1;
		 long temp;
	     result = 31 * result + (user != null ? user.hashCode() : 0);
	     result = 31 * result + (line != null ? line.hashCode() : 0);
	     result = 31 * result + (betDate != null ? betDate.hashCode() : 0);
	     temp = Double.doubleToLongBits(amount);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
	     result = 31 * result + (betStatus != null ? betStatus.hashCode() : 0);
	     return result;
	 }

	 @Override
	 public String toString() {
		 return getClass().getName() + "@" + "id : " + id + ", user : " + user +
	    			", line : " + line + ", betDate : " + betDate +
	    			", amount : " + amount + ", outcome : " + outcome +
	    			", betStatus : " + betStatus;
	}
}
