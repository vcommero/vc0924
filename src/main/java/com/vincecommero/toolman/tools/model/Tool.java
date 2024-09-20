package com.vincecommero.toolman.tools.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tools")
public class Tool {

	@Id
	@Column(name = "tool_code", nullable = false, unique = true)
	private String toolCode;
	
	@ManyToOne
	@JoinColumn(name = "tool_type", referencedColumnName = "type_name")
	private ToolType toolType;
	
	@Column(name = "tool_brand", nullable = false)
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
