package com.vincecommero.toolman.tools.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Tools",
		uniqueConstraints = @UniqueConstraint(columnNames = { "toolCode" }))
public class Tool {

	@Id
	private String toolCode;
	
	@OneToOne
	@JoinColumn(name = "toolType", referencedColumnName = "toolType")
	private ToolType toolType;
	
	private String toolBrand;

	
	// Getters and setters
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public ToolType getToolType() {
		return toolType;
	}
	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}
}
