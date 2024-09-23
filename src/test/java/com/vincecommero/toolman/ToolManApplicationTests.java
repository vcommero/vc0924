package com.vincecommero.toolman;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vincecommero.toolman.commandlineinterface.ToolmanCommandLine;
import com.vincecommero.toolman.config.H2DataLoader;

@SpringBootTest
class ToolManApplicationTests {
	
	@MockBean
	private ToolmanCommandLine toolmanCommandLine;
	
	@MockBean
	private H2DataLoader h2DataLoader;

	@Test
	void contextLoads() {
	}

}
