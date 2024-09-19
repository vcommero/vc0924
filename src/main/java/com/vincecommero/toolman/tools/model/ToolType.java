package com.vincecommero.toolman.tools.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "ToolTypes",
		uniqueConstraints = @UniqueConstraint(columnNames = { "toolType" }))
public class ToolType {

	@Id
	private String toolType;
	
	private int dailyCharge; // Note: This is in microdollars, used to avoid precision errors with currency. Can use ints for small values.
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	
	// Getters and setters
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	
	public int getDailyCharge() {
		return dailyCharge;
	}
	public void setDailyCharge(int dailyCharge) {
		this.dailyCharge = dailyCharge;
	}
	
	public boolean getWeekdayCharge() {
		return weekdayCharge;
	}
	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}
	
	public boolean getWeekendCharge() {
		return weekendCharge;
	}
	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}
	
	public boolean getHolidayCharge() {
		return holidayCharge;
	}
	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
}
