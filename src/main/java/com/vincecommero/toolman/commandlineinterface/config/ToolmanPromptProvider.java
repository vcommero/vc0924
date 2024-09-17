package com.vincecommero.toolman.commandlineinterface.config;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ToolmanPromptProvider implements PromptProvider {
	
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("[Toolman CLI]$~ ");
    }

}
