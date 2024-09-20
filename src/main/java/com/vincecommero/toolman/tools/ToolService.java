package com.vincecommero.toolman.tools;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;
import com.vincecommero.toolman.tools.repository.ToolRepository;
import com.vincecommero.toolman.tools.repository.ToolTypeRepository;

@Service
public class ToolService {
	
	private final ToolRepository toolRepository;
	private final ToolTypeRepository toolTypeRepository;
	
	public ToolService(ToolRepository toolRepository, ToolTypeRepository toolTypeRepository) {
		this.toolRepository = toolRepository;
		this.toolTypeRepository = toolTypeRepository;
	}

	public List<Tool> getAllTools() {
		return toolRepository.findAll();
	}
	
	public List<ToolType> getAllToolTypes() {
		return toolTypeRepository.findAll();
	}
	
	public Tool getToolByToolCode(String toolCode) {
		return toolRepository.findById(toolCode).get();
	}
	
	public ToolType getToolTypeByTypeName(String toolType) {
		return toolTypeRepository.findById(toolType).get();
	}
	
	
}
