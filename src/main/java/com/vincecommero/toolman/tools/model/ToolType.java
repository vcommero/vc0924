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
}
