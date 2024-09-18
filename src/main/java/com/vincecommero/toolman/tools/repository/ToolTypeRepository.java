package com.vincecommero.toolman.tools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vincecommero.toolman.tools.model.ToolType;

public interface ToolTypeRepository extends JpaRepository<ToolType, String> {
	
}
