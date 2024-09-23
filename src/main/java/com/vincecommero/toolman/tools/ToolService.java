package com.vincecommero.toolman.tools;

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
	
	public Tool getToolByToolCode(String toolCode) {
		return toolRepository.findById(toolCode).get();
	}
	
	public Tool saveTool(Tool tool) {
		return this.toolRepository.save(tool);
	}
	
	public ToolType saveToolType(ToolType toolType) {
		return this.toolTypeRepository.save(toolType);
	}
}
