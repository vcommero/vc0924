package com.vincecommero.toolman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vincecommero.toolman.tools.model.Tool;
import com.vincecommero.toolman.tools.model.ToolType;
import com.vincecommero.toolman.tools.repository.ToolRepository;
import com.vincecommero.toolman.tools.repository.ToolTypeRepository;

/**
 * Seeds data into the H2 database.
 */

@Component
@Order(1)
public class H2DataLoader implements CommandLineRunner {
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ToolTypeRepository toolTypeRepository;

	@Override
	public void run(String... args) {
		System.out.println("==== Starting commandlinerunner");
		
		ToolType ladder = new ToolType();
		ladder.setTypeName("Ladder");
		ladder.setDailyCharge(1990000);
		ladder.setWeekdayCharge(true);
		ladder.setWeekendCharge(true);
		ladder.setHolidayCharge(false);
		toolTypeRepository.save(ladder);
		
		ToolType chainsaw = new ToolType();
		chainsaw.setTypeName("Chainsaw");
		chainsaw.setDailyCharge(1490000);
		chainsaw.setWeekdayCharge(true);
		chainsaw.setWeekendCharge(false);
		chainsaw.setHolidayCharge(true);
		toolTypeRepository.save(chainsaw);
		
		ToolType jackhammer = new ToolType();
		jackhammer.setTypeName("Jackhammer");
		jackhammer.setDailyCharge(2990000);
		jackhammer.setWeekdayCharge(true);
		jackhammer.setWeekendCharge(false);
		jackhammer.setHolidayCharge(false);
		toolTypeRepository.save(jackhammer);
		
		Tool tool1 = new Tool();
		tool1.setToolCode("CHNS");
		tool1.setToolType(chainsaw);
		tool1.setToolBrand("Stihl");
		toolRepository.save(tool1);
		
		Tool tool2 = new Tool();
		tool2.setToolCode("LADW");
		tool2.setToolType(ladder);
		tool2.setToolBrand("Werner");
		toolRepository.save(tool2);
		
		Tool tool3 = new Tool();
		tool3.setToolCode("JAKD");
		tool3.setToolType(jackhammer);
		tool3.setToolBrand("DeWalt");
		toolRepository.save(tool3);
		
		Tool tool4 = new Tool();
		tool4.setToolCode("JAKR");
		tool4.setToolType(jackhammer);
		tool4.setToolBrand("Ridgid");
		toolRepository.save(tool4);
	}
}
