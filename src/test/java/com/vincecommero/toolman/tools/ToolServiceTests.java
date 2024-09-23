package com.vincecommero.toolman.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;
import com.vincecommero.toolman.tools.repository.ToolRepository;
import com.vincecommero.toolman.tools.repository.ToolTypeRepository;

@ExtendWith(MockitoExtension.class)
public class ToolServiceTests {
	
	@Mock
	private ToolRepository toolRepository;
	
	@Mock
	private ToolTypeRepository toolTypeRepository;
	
	@InjectMocks
	private ToolService toolService;

	@Test
	void shouldReturnAToolWhenGivenAToolCodeOfExistingTool() {
		String testCode = "TEST EXISTING TOOL";
		Tool testTool = new Tool();
		testTool.setToolCode("TOOL");
		
		when(toolRepository.findById(testCode)).thenReturn(Optional.of(testTool));
		
		Tool outputTool = toolService.getToolByToolCode(testCode);
		
		assertEquals(testTool.getToolCode(), outputTool.getToolCode());
	}
	
	@Test
	void shouldThrowExceptionWhenNoToolFound() {
		String testCode = "TEST NONEXISTING TOOL";
		
		when(toolRepository.findById(testCode)).thenReturn(Optional.empty());
		
		assertThrows(NoSuchElementException.class, () -> toolService.getToolByToolCode(testCode));
	}
	
	@Test
	void shouldBeAbleToSaveAToolInTheDatabase() {
		Tool testTool = new Tool();
		testTool.setToolCode("TOOL");
		
		when(toolRepository.save(testTool)).thenReturn(testTool);
		
		Tool outputTool = toolService.saveTool(testTool);
		
		assertEquals(testTool.getToolCode(), outputTool.getToolCode());
	}
	
	@Test
	void shouldThrowExceptionIfToolToSaveIsNull() {
		when(toolRepository.save(null)).thenThrow(new IllegalArgumentException());
		
		assertThrows(IllegalArgumentException.class, () -> toolService.saveTool(null));
	}
	
	@Test
	void shouldBeAbleToSaveAToolTypeInTheDatabase() {
		ToolType testType = new ToolType();
		testType.setTypeName("Spoon");
		
		when(toolTypeRepository.save(testType)).thenReturn(testType);
		
		ToolType outputType = toolService.saveToolType(testType);
		
		assertEquals(testType.getTypeName(), outputType.getTypeName());
	}
	
	@Test
	void shouldThrowExceptionIfToolTypeToSaveIsNull() {
		when(toolTypeRepository.save(null)).thenThrow(new IllegalArgumentException());
		
		assertThrows(IllegalArgumentException.class, () -> toolService.saveToolType(null));
	}
}
