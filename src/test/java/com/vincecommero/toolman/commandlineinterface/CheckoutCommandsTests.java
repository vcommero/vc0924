package com.vincecommero.toolman.commandlineinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.vincecommero.toolman.checkout.CheckoutService;
import com.vincecommero.toolman.checkout.model.RentalAgreement;
import com.vincecommero.toolman.commandlineinterface.utility.ConsoleIO;

@ExtendWith(MockitoExtension.class)
public class CheckoutCommandsTests {
	
	private RentalAgreement mockAgreement = mock(RentalAgreement.class);
	private PrintStream mockPrintStream = mock(PrintStream.class);
	
	@Mock
	private CheckoutService checkoutService;
	
	@Mock
	private ConsoleIO consoleIO;
	
	@InjectMocks
	private CheckoutCommands checkoutCommands;
	
	@Test
	void shouldGenerateRentalAgreementAndReturnSuccessfullyMessage() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String discountString = "25";
		String checkoutDateString = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenReturn(toolCodeString);
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenReturn(rentalDaysString);
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenReturn(discountString);
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenReturn(checkoutDateString);
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenReturn(mockAgreement);
		when(consoleIO.getOutputPrintStream()).thenReturn(mockPrintStream);
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			String output = checkoutCommands.checkout();
			assertEquals("Checkout was successful!", output);
		});
		verify(checkoutService).checkout(
				eq(toolCodeString), 
				eq(Integer.parseInt(rentalDaysString)), 
				eq(LocalDate.parse(checkoutDateString)), 
				eq(Integer.parseInt(discountString)));
	}
	
	@Test
	void shouldHandleIllegalArgumentExceptionInCheckoutService() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String discountString = "25";
		String checkoutDateString = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenReturn(toolCodeString);
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenReturn(rentalDaysString);
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenReturn(discountString);
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenReturn(checkoutDateString);
		
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenThrow(new IllegalArgumentException("Bad arguments"));
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			String output = checkoutCommands.checkout();
			assertEquals("Bad arguments", output);
		});
	}
	
	@Test
	void shouldRepromptIfBlankToolCodeReceived() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String discountString = "25";
		String checkoutDateString = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenAnswer(new Answer<String>() {
			private int callCount = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				callCount++;
				if (callCount == 1) {
					return "";
				}
				else {
					return toolCodeString;
				}
			}
		});
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenReturn(rentalDaysString);
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenReturn(discountString);
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenReturn(checkoutDateString);
		when(consoleIO.getOutputPrintStream()).thenReturn(mockPrintStream);
		
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenReturn(mockAgreement);
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			checkoutCommands.checkout();
		});
		verify(consoleIO).println("Invalid input: Please enter a tool code.");
	}
	
	@Test
	void shouldHandleBadArgumentsForRentalDays() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String discountString = "25";
		String checkoutDateString = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenReturn(toolCodeString);
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenAnswer(new Answer<String>() {
			private int callCount = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				callCount++;
				return switch (callCount) {
				    case 1:
				        yield "";
				    case 2:
				        yield "abc";
				    case 3:
				    	yield "0";
				    case 4:
				    	yield "-1";
					default:
						yield rentalDaysString;
				};
			}
		});
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenReturn(discountString);
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenReturn(checkoutDateString);
		when(consoleIO.getOutputPrintStream()).thenReturn(mockPrintStream);
		
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenReturn(mockAgreement);
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			checkoutCommands.checkout();
		});
		verify(consoleIO, times(2)).println("Invalid input: Please enter a valid integer number of days.");
		verify(consoleIO, times(2)).println("Invalid input: Rental duration must be 1 day or greater.");
	}
	
	@Test
	void shouldHandleBadAndBlankArgumentsForDiscountPercentage() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String checkoutDateString = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenReturn(toolCodeString);
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenReturn(rentalDaysString);
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenAnswer(new Answer<String>() {
			private int callCount = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				callCount++;
				return switch (callCount) {
				    case 1:
				        yield "abc";
				    case 2:
				        yield "-1";
				    case 3:
				    	yield "101";
					default:
						yield "";
				};
			}
		});
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenReturn(checkoutDateString);
		when(consoleIO.getOutputPrintStream()).thenReturn(mockPrintStream);
		
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenReturn(mockAgreement);
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			checkoutCommands.checkout();
		});
		verify(consoleIO).println("Invalid input: Please enter a valid integer number for the discount.");
		verify(consoleIO, times(2)).println("Invalid input: Discount percentage can't be greater than 100% or less than 0%.");
		verify(checkoutService).checkout(
				eq(toolCodeString), 
				eq(Integer.parseInt(rentalDaysString)), 
				eq(LocalDate.parse(checkoutDateString)), 
				eq(0)); // Discount default should be zero
	}
	
	@Test
	void shouldHandleBadAndBlankArgumentsForCheckoutDate() {
		String toolCodeString = "ABCD";
		String rentalDaysString = "7";
		String discountString = "25";
		when(consoleIO.prompt("Please enter the code of the tool you wish to checkout: ")).thenReturn(toolCodeString);
		when(consoleIO.prompt("Please enter the rental duration in days: ")).thenReturn(rentalDaysString);
		when(consoleIO.prompt("Please enter the rental price discount percentage (from 0 to 100) or press Enter for 0: ")).thenReturn(discountString);
		when(consoleIO.prompt("Please enter the checkout date (in YYYY-MM-DD) or press Enter for today: ")).thenAnswer(new Answer<String>() {
			private int callCount = 0;
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				callCount++;
				return switch (callCount) {
				    case 1:
				        yield "2125-01-32";
				    case 2:
				        yield "2020-01-01";
				    case 3:
				    	yield "2125-5-5";
				    case 4:
				    	yield "01/01/2125";
				    case 5:
				    	yield "2125/01/01";
					default:
						yield "";
				};
			}
		});
		when(consoleIO.getOutputPrintStream()).thenReturn(mockPrintStream);
		
		when(checkoutService.checkout(anyString(), anyInt(), any(), anyInt())).thenReturn(mockAgreement);
		
		assertTimeout(Duration.ofSeconds(3), () -> {
			checkoutCommands.checkout();
		});
		verify(consoleIO, times(4)).println("Invalid input: Please enter a valid date");
		verify(consoleIO).println("Invalid input: Please enter a current or future date.");
		verify(checkoutService).checkout(
				eq(toolCodeString), 
				eq(Integer.parseInt(rentalDaysString)), 
				eq(LocalDate.now()), 
				eq(Integer.parseInt(discountString)));
	}
}
