package by.epam.totalizator.entity;

import java.io.Serializable;
import java.util.Date;

public class Line implements Serializable {

	private static final long serialVersionUID =  1L;

	private int id;
	private Sport sport;
	private Competition competition;
	private String eventName;
	private Date startDate;
	private double winCoeff;
	private double drawCoeff;
	private double loseCoeff;
	private double minBet;
	private double maxBet;
	private String fixedResult;
	private int score1;
	private int score2;
	
	public Line() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getWinCoeff() {
		return winCoeff;
	}

	public void setWinCoeff(double winCoeff) {
		this.winCoeff = winCoeff;
	}

	public double getDrawCoeff() {
		return drawCoeff;
	}

	public void setDrawCoeff(double drawCoeff) {
		this.drawCoeff = drawCoeff;
	}

	public double getLoseCoeff() {
		return loseCoeff;
	}

	public void setLoseCoeff(double loseCoeff) {
		this.loseCoeff = loseCoeff;
	}

	public double getMinBet() {
		return minBet;
	}

	public void setMinBet(double minBet) {
		this.minBet = minBet;
	}

	public double getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(double maxBet) {
		this.maxBet = maxBet;
	}

	public String getFixedResult() {
		return fixedResult;
	}

	public void setFixedResult(String fixedResult) {
		this.fixedResult = fixedResult;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
	    
		Line other = (Line) obj;
	    
		if (sport != null ? !sport.equals(other.sport) : other.sport != null) return false;
		if (competition != null ? !competition.equals(other.competition) : other.competition != null) return false;
		if (eventName != null ? !eventName.equals(other.eventName) : other.eventName != null) return false;
		if (startDate != null ? !startDate.equals(other.startDate) : other.startDate != null) return false;
		if (winCoeff != other.winCoeff) return false;
		if (drawCoeff != other.drawCoeff) return false;
		if (loseCoeff != other.loseCoeff) return false;
		if (minBet != other.minBet) return false;
		if (maxBet != other.maxBet) return false;
		if (fixedResult != null ? !fixedResult.equals(other.fixedResult) : other.fixedResult != null) return false;
		if (score1 != other.score1) return false;
		if (score2 != other.score2) return false;
		return true;
	 }
	
	 @Override
	 public int hashCode() {
		 int result = 1;
		 long temp;
	     result = 31 * result + (sport != null ? sport.hashCode() : 0);
	     result = 31 * result + (competition != null ? competition.hashCode() : 0);
	     result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
	     result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
	     temp = Double.doubleToLongBits(winCoeff);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     temp = Double.doubleToLongBits(drawCoeff);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     temp = Double.doubleToLongBits(loseCoeff);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     temp = Double.doubleToLongBits(minBet);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     temp = Double.doubleToLongBits(maxBet);
	     result = 31 * result + (int) (temp ^ (temp >>> 32));
	     result = 31 * result + (fixedResult != null ? fixedResult.hashCode() : 0);
	     result = 31 * result + score1;
	     result = 31 * result + score2;
	     return result;
	 }

	 @Override
	 public String toString() {
		 return getClass().getName() + "@" + "id : " + id + ", sport : " + sport +
	    			", competition : " + competition + ", eventName : " + eventName +
	    			", startDate : " + startDate + ", winCoeff : " + winCoeff +
	    			", drawCoeff : " + drawCoeff + ", loseCoeff : " + loseCoeff +
	    			", minBet : " + minBet + ", maxBet : " + maxBet +
	    			", fixedResult : " + fixedResult + ", score1 : " + score1 +
	    			", score2 : " + score2;
	}
}
