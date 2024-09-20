package com.vincecommero.toolman.commandlineinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ToolmanCommandLine implements CommandLineRunner {
	
	//@Value("${app.version}")
	private String appVersion = "0.0.1";
	
	private final String banner =
			"======================================\n" +
			"          Welcome to Toolman!         \n" +
			"          " + appVersion + "                            \n" +
			"======================================";
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println(banner);
		System.out.println("\nPlease select an option:");
		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true;
			while (running) {
				String input = scanner.nextLine().trim().toLowerCase();
				if (input.equals("exit")) {
					running = false;
                }
			}
		}
	}
}
