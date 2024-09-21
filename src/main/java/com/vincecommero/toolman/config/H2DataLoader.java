package com.vincecommero.toolman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vincecommero.toolman.tools.ToolService;
import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;

/**
 * Seeds data into the H2 database.
 */

@Component
@Order(1)
public class H2DataLoader implements CommandLineRunner {
	
	@Autowired
	private ToolService toolService;

	@Override
	public void run(String... args) {
		ToolType ladder = new ToolType();
		ladder.setTypeName("Ladder");
		ladder.setDailyCharge(1990000);
		ladder.setWeekdayCharge(true);
		ladder.setWeekendCharge(true);
		ladder.setHolidayCharge(false);
		toolService.saveToolType(ladder);
		
		ToolType chainsaw = new ToolType();
		chainsaw.setTypeName("Chainsaw");
		chainsaw.setDailyCharge(1490000);
		chainsaw.setWeekdayCharge(true);
		chainsaw.setWeekendCharge(false);
		chainsaw.setHolidayCharge(true);
		toolService.saveToolType(chainsaw);
		
		ToolType jackhammer = new ToolType();
		jackhammer.setTypeName("Jackhammer");
		jackhammer.setDailyCharge(2990000);
		jackhammer.setWeekdayCharge(true);
		jackhammer.setWeekendCharge(false);
		jackhammer.setHolidayCharge(false);
		toolService.saveToolType(jackhammer);
		
		Tool tool1 = new Tool();
		tool1.setToolCode("CHNS");
		tool1.setToolType(chainsaw);
		tool1.setToolBrand("Stihl");
		toolService.saveTool(tool1);
		
		Tool tool2 = new Tool();
		tool2.setToolCode("LADW");
		tool2.setToolType(ladder);
		tool2.setToolBrand("Werner");
		toolService.saveTool(tool2);
		
		Tool tool3 = new Tool();
		tool3.setToolCode("JAKD");
		tool3.setToolType(jackhammer);
		tool3.setToolBrand("DeWalt");
		toolService.saveTool(tool3);
		
		Tool tool4 = new Tool();
		tool4.setToolCode("JAKR");
		tool4.setToolType(jackhammer);
		tool4.setToolBrand("Ridgid");
		toolService.saveTool(tool4);
	}
}
