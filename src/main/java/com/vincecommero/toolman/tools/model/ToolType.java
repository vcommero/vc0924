package com.vincecommero.toolman.tools.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ToolTypes")
public class ToolType {

	@Id
	@Column(name = "type_name", nullable = false, unique = true)
	private String typeName;
	
	@Column(name = "daily_charge", nullable = false)
	private int dailyCharge; // Note: This is in microdollars, used to avoid precision errors with currency. Can use ints for small values.
	
	@Column(name = "weekday_charge", nullable = false)
	private boolean weekdayCharge;
	
	@Column(name = "weekend_charge", nullable = false)
	private boolean weekendCharge;
	
	@Column(name = "holiday_charge", nullable = false)
	private boolean holidayCharge;
	
	
	// Getters and setters
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String toolType) {
		this.typeName = toolType;
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
