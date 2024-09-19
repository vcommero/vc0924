package com.vincecommero.toolman.checkout.model;

import java.time.LocalDate;

public class RentalAgreement {
	
	// Note: Currency fields are in microdollars stored in integer values to avoid precision errors in computation.

	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private int dailyRentalCharge;
	private int chargeDays;
	private long prediscountCharge;
	private int discountPercent;
	private long discountAmount;
	private long finalCharge;
	
	public RentalAgreement() {}
	
	public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate,
			LocalDate dueDate, int dailyRentalCharge, int chargeDays, long prediscountCharge, int discountPercent,
			long discountAmount, long finalCharge) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = dailyRentalCharge;
		this.chargeDays = chargeDays;
		this.prediscountCharge = prediscountCharge;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}

	// Getters and setters
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}
	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getDailyRentalCharge() {
		return dailyRentalCharge;
	}
	public void setDailyRentalCharge(int dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}
	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public long getPrediscountCharge() {
		return prediscountCharge;
	}
	public void setPrediscountCharge(long prediscountCharge) {
		this.prediscountCharge = prediscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public long getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}

	public long getFinalCharge() {
		return finalCharge;
	}
	public void setFinalCharge(long finalCharge) {
		this.finalCharge = finalCharge;
	}
	
	// Printing method
}
