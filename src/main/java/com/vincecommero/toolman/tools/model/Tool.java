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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "toolType", referencedColumnName = "toolType")
	private ToolType toolType;
	
	private String toolBrand;
}
