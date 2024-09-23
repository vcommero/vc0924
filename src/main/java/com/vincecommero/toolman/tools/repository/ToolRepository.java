package com.vincecommero.toolman.tools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vincecommero.toolman.tools.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, String>{

}
