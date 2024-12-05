package CC103;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FinalProject {
	static int[][] drink = new int[5][3];
	static int[] pastry = new int [3];
	static String[] drinkList = {"Logic Latte", "Pixel Punch", "Bitstream Brew", "Code Cooler", "Debug Delight"}, sizes = {"byte", "int", "long"}, pastryList = {"Glazed Donut", "Alcapone", "Vanilluxe"};
	static double[] price = { 22, 25, 30, 27, 30, 35, 30, 33, 38, 32, 35, 40, 32, 35, 40, 25, 30, 25} ;
	static double totalCost = 0, finalCost = 0, money = 0;
	static int error = 0, totalDrinks = 0, totalOrder = 0;
	static boolean personWithDisabilities = false, discount = false;
	
	public static void main(String[] args) {
		int input = -1;
		Scanner scan = new Scanner(System.in);
		while(!(input == 0|| (input == 9) && totalOrder > 0)) {	
			for(int i = 0; i < 50; i++, System.out.println());
			if(input == 9 && totalOrder <= 0) {
				System.out.println("You have not ordered anything");
				System.out.println("If you wish to cancel this transaction type [0]");
				error = 0;
			}
			if(error != 0) System.out.println("Invalid input, Returned to Menu");
			error = 0;				
			showMenu();
			System.out.println("____________________________Cart____________________________");
			showCart();
			showCartTotal();								    
			System.out.print("Type the code of your order: ");
			input = checkValidity(scan, input);
			if(input >= 1 && input <= 8) checkOrder(scan, input);
			else if (input == 10 && totalOrder > 0) removeOrder(scan, input);
			else error++;
			totalOrder = (drink[0][0]+drink[0][1]+drink[0][2]+drink[1][0]+drink[1][1]+drink[1][2]+drink[2][0]+drink[2][1]+drink[2][2]+drink[3][0]+drink[3][1]+drink[3][2]+drink[4][0]+drink[4][1]+drink[4][2]+pastry[0]+pastry[1]+pastry[2]);										 
			totalDrinks = totalOrder - (pastry[0]+pastry[1]+pastry[2]);
			totalCost = (drink[0][0]*price[0])+(drink[0][1]*price[1])+(drink[0][2]*price[2])+(drink[1][0]*price[3])+(drink[1][1]*price[4])+(drink[1][2]*price[5])+(drink[2][0]*price[6])+(drink[2][1]*price[7])+(drink[2][2]*price[8])+(drink[3][0]*price[9])+(drink[3][1]*price[10])+(drink[3][2]*price[11])+(drink[4][0]*price[12])+(drink[4][1]*price[13])+(drink[4][2]*price[14])+(pastry[0]*price[15])+(pastry[1]*price[16])+(pastry[2]*price[17]);
		}
		if(input == 0) System.out.println("Order being cancelled, we hope to see you again soon!");	
		else if (input == 9) {
			finalCost = totalCost*1.2;
			for(int i = 0; i < 100; i++, System.out.println());
			while (!(input == 1||input == 2 || input == 3)) {
				System.out.print(" [1] Senior Citizen\n [2] PWD\n [3] ineligible for discount\nAre you eligible for a Senior/PWD Discount: ");
				input = checkValidity(scan, input);
				if(!(input == 1|| input == 2 || input == 3)) input = invalidInput(scan, input);
			}
			for(int i = 0; i < 50; i++, System.out.println());
			if(input == 1 || input == 2){
				if(input == 2) personWithDisabilities = true;
				discount = true; 
				finalCost -= totalCost*0.24;
			}
			while(money<(finalCost)) {
				System.out.println("___________________________Total____________________________");
				showCart();
				showOrderTotal(discount);
				money = checkMoney(scan);	
				if(money<(finalCost)) money = invalidMoney(scan, input);
				}	
		for(int i = 0; i < 50; i++, System.out.println());
		showFinalTotal(discount);
		}scan.close();	
	}
	
	public static void showMenu() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~Binary Brews~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("============================Menu============================");
		System.out.println("---------------------------Drinks---------------------------");
		System.out.println("-Code---------------byte------int-------long----------------");
		System.out.printf(" [1]%-14s P%.02f |  P%.02f |  P%.02f %n", drinkList[0], price[0] , price[1] , price[2]);
		System.out.printf(" [2]%-14s P%.02f |  P%.02f |  P%.02f %n", drinkList[1], price[3] , price[4] , price[5]);
		System.out.printf(" [3]%-14s P%.02f |  P%.02f |  P%.02f %n", drinkList[2], price[6] , price[7] , price[8]);
		System.out.printf(" [4]%-14s P%.02f |  P%.02f |  P%.02f %n", drinkList[3], price[9] , price[10], price[11]);
		System.out.printf(" [5]%-14s P%.02f |  P%.02f |  P%.02f %n", drinkList[4], price[12], price[13], price[14]);
		System.out.println("---------------------------Donuts---------------------------");
		System.out.printf(" [6]%-14s P%.02f %n", pastryList[0], price[15]);
		System.out.printf(" [7]%-14s P%.02f %n", pastryList[1], price[16]);
		System.out.printf(" [8]%-14s P%.02f %n", pastryList[2], price[17]);
		System.out.println("============================================================");
		if(totalOrder!=0) {
			System.out.println(" [9]  To go to Check Out");
			System.out.println(" [0]  To cancel your order");
			System.out.println(" [10] To Remove an order from your cart");
			System.out.println("============================================================");
		}
	}

	static void showCart() {
		if(totalDrinks>0)  						    System.out.println("---------------------------Drinks---------------------------");
		if((drink[0][0]+drink[0][1]+drink[0][2])> 0)System.out.printf(" >%-20s %dx P%.0f | %dx P%.0f | %dx P%.0f : P%.02f %n",drinkList[0] , drink[0][0] , price[0] , drink[0][1] ,price[1] , drink[0][2], price[2] , ((drink[0][0]*price[0] )+(drink[0][1]*price[1] )+(drink[0][2]*price[2] )));
		if((drink[1][0]+drink[1][1]+drink[1][2])> 0)System.out.printf(" >%-20s %dx P%.0f | %dx P%.0f | %dx P%.0f : P%.02f %n",drinkList[1] , drink[1][0] , price[3] , drink[1][1] ,price[4] , drink[1][2], price[5] , ((drink[1][0]*price[3] )+(drink[1][1]*price[4] )+(drink[1][2]*price[5] )));
		if((drink[2][0]+drink[2][1]+drink[2][2])> 0)System.out.printf(" >%-20s %dx P%.0f | %dx P%.0f | %dx P%.0f : P%.02f %n",drinkList[2] , drink[2][0] , price[6] , drink[2][1] ,price[7] , drink[2][2], price[8] , ((drink[2][0]*price[6] )+(drink[2][1]*price[7] )+(drink[2][2]*price[8] )));
		if((drink[3][0]+drink[3][1]+drink[3][2])> 0)System.out.printf(" >%-20s %dx P%.0f | %dx P%.0f | %dx P%.0f : P%.02f %n",drinkList[3] , drink[3][0] , price[9] , drink[3][1] ,price[10], drink[3][2], price[11], ((drink[3][0]*price[9] )+(drink[3][1]*price[10])+(drink[3][2]*price[11])));
		if((drink[4][0]+drink[4][1]+drink[4][2])> 0)System.out.printf(" >%-20s %dx P%.0f | %dx P%.0f | %dx P%.0f : P%.02f %n",drinkList[4] , drink[4][0] , price[12], drink[4][1] ,price[13], drink[4][2], price[14], ((drink[4][0]*price[12])+(drink[4][1]*price[13])+(drink[4][2]*price[14])));
		if((totalOrder-totalDrinks)>0)              System.out.println("---------------------------Donuts---------------------------");
		if(pastry[0] > 0)                           System.out.printf(" >%-20s %dx %-23s P%1.02f %n", pastryList[0], pastry[0], "", (pastry[0]*price[15]));
		if(pastry[1] > 0)                           System.out.printf(" >%-20s %dx %-23s P%1.02f %n", pastryList[1], pastry[1], "", (pastry[1]*price[16]));
		if(pastry[2] > 0)                           System.out.printf(" >%-20s %dx %-23s P%1.02f %n", pastryList[2], pastry[2], "", (pastry[2]*price[17]));
	}

	static int checkValidity(Scanner scan, int input) {
		while(true) {
			try{
				input = Integer.parseInt(scan.nextLine());
				if(input < 0) throw new Exception();
				return(input);
			}
			catch(Exception e) {
				return(-1);
			}
		}
	}

	static double checkMoney(Scanner scan) {
		while(true) {
			try{
				money = Double.parseDouble(scan.nextLine().replaceAll("P", ""));
				if(money < 0) throw new Exception();
				return(money);
			} 
			catch(Exception e) {
				return(0);
			}
		}
	}

	static int invalidInput(Scanner scan, int input) {
		System.out.print("Invalid input, Try again: ");
		input = checkValidity(scan,input);
		return(input);
	}

	static double invalidMoney(Scanner scan, int input) {
		while(money < finalCost) {
			System.out.print("Inserted money is not enough/invalid, Please Retype: ");
			money = checkMoney(scan);		
		}
		return(money);
	}

	public static void checkOrder(Scanner scan, int input) {
		int choice = 0;
		if(input >= 1 && input <=5) {	
			int size = 0;
			for(int i = 1; !(input == i-1); choice = i-1, ++i);
			System.out.println("Typing any invalid input will exit this choice.");
	    	System.out.print(" [1] byte\n [2] int\n [3] long\nWhat size would you like: ");
	    	while(!(input == -1)) {
	    		input = checkValidity(scan, input);
		    	if(input == 1 || input == 2 || input == 3){
					size = (input == 1) ? 0 : (input == 2)? 1 : 2;
					System.out.println("Typing any invalid input will exit this choice.");
		    		System.out.print("How many "+sizes[size]+"-sized "+drinkList[choice]+"s would you like: ");
		    		while(!(input == -1)) {
		    			input = checkValidity(scan, input);
			    		if(input > 0) {
							drink[choice][size] += input;
							input = -1; 
			    		}
			    		else System.out.print("Input is not a valid number, please retype: ");
					}
				}
				else System.out.print("Invalid size, please retype: ");
	    	}
		}
		else {
			for(int i = 1; !(input == i+5); choice = i, ++i);
			System.out.println("Typing any invalid input will exit this choice.");
			System.out.print("How many "+pastryList[choice]+" would you like: ");
			while(true) {
				input = checkValidity(scan, input);
	    		if(input > 0) {
					pastry[choice] += input;
					break;
	    		}
	    		else if(input == -1) break;
	    		else System.out.print("Input is not a valid number, please retype: ");
	    		
			}
		}
	}

	public static void removeOrder(Scanner scan, int input) {
		boolean exceedsCart = false;
		while(input != 0) {
			for(int i = 0; i < 50; i++, System.out.println());
			if(error != 0) System.out.println("Invalid input, try again");
			error = 0;
			if(exceedsCart == true) System.out.println("You did not have any of the item you tried to remove\nReturned to removal screen");
			exceedsCart = false;
			System.out.println("===========================Remove===========================");
			if(totalDrinks>0)  	System.out.println("---------------------------Drinks---------------------------");
			for(int i = 0; i != 5; i++) {
				if(drink[i][0]+drink[i][1]+drink[i][2] > 0) {
					System.out.printf("%-24s"," ["+(i+1)+"]"+drinkList[i]+":");
					for(int j = 0; j != 3; j++) {
						if(drink[i][j] > 0) System.out.printf("%-25s %1s", drink[i][j]+"x P"+String.format("%.02f",price[((i*3)+j)])+"/"+sizes[j], "P"+String.format("%.2f", drink[i][j]*price[((i*3)+j)])); 
						if(j !=2) {
							if(drink[i][j+1] > 0 && ((j==1 && drink[i][1] > 0) || drink[i][0] > 0)) System.out.printf("%n %-23s", "");
						}
						else System.out.println();
					}
				}
			}
			if((totalOrder-totalDrinks)>0)              System.out.println("---------------------------Donuts---------------------------");
			if(pastry[0] > 0)                           System.out.println(String.format("%-23s %-25s %1s"," [6]"+pastryList[0]+":",pastry[0]+"x","P"+String.format("%.02f", (pastry[0]*price[15]))));
			if(pastry[1] > 0)                           System.out.println(String.format("%-23s %-25s %1s"," [7]"+pastryList[1]+":",pastry[1]+"x","P"+String.format("%.02f", (pastry[1]*price[16]))));
			if(pastry[2] > 0)                           System.out.println(String.format("%-23s %-25s %1s"," [8]"+pastryList[2]+":",pastry[2]+"x","P"+String.format("%.02f", (pastry[2]*price[17]))));
			System.out.println("============================================================");
			System.out.println(" [0]  To Return to Main menu");
			System.out.println(" [10] To Remove all orders from your cart");
			System.out.println("============================================================");
			System.out.print("Select the code of item to remove: ");
			input = checkValidity(scan, input);
			if(input >= 1 && input <= 5) {
				int choice = input-1, size = 0;
				System.out.print(" [1] byte\n [2] int\n [3] long\nWhat size would you like to remove: ");
				input = checkValidity(scan, input);
				size = (input == 1) ? 0 : (input == 2)? 1 : 2;
				if(drink[choice][size] == 0) exceedsCart = true;
				while(!(input == -1) && !exceedsCart) {
					if(input >= 1 && input <= 3){
						System.out.print("How many "+sizes[size]+"-sized "+drinkList[choice]+"s would you like to remove: ");
						while(!(input == -1)) {
							input = checkValidity(scan, input);
							if(input >= 0 && input <= drink[choice][size]) {
								drink[choice][size] -= input;
								input = -1; 
							}
							else if(input >= 0 && input > drink[choice][size]) System.out.print("Exceeds number of "+sizes[size]+"-sized "+drinkList[choice]+"s \n please retype: ");
							else System.out.print("Input is not a valid number, please retype: ");
						}
					}
					else {
						System.out.print("Invalid size, please retype: ");
						input = checkValidity(scan, input);
					}
				}
			}
			else if(input >= 6 && input <= 8) {
				int choice = input-6;
				if(pastry[choice] == 0) exceedsCart = true;
				System.out.print("How many "+pastryList[choice]+" would you like to remove: ");
				while(!exceedsCart) {
					input = checkValidity(scan, input);
					if(input <= pastry[choice] || input == 0) {
						pastry[choice] -= input;
						break;
					}
					else if(input > pastry[choice]) System.out.print("Exceeds number of "+pastryList[choice]+" \nif you have none please type [0]\notherwise please retype: ");
					else System.out.print("Input is not a valid number, please retype: "); 
				}
			}
			else if(input == 0);
			else error++;
			if(input == 10) {
				removeAllOrders();
				error = 0;
			}
		}
	}

	public static void showCartTotal() {
		if(totalOrder>0) {							
							System.out.println("------------------------------------------------------------");
							System.out.println(String.format("%-49s %1s","Total Value of Cart:",     "P"+String.format("%.02f", totalCost)));}
	    					System.out.println("____________________________________________________________");
	}

	public static void showOrderTotal(boolean discount) {
		String discountCause = (personWithDisabilities)? "PWD":"Senior Citizen";
		if(totalOrder>0) {							
							System.out.println("------------------------------------------------------------");
		                    System.out.println(String.format("%-49s %1s","Total Value of Purchases:",     "P"+String.format("%.02f", totalCost)));
		                    System.out.println(String.format("%-49s %1s","VAT (Variable Added Tax):","P"+String.format("%.02f", (totalCost*0.2))));
		if(discount == true)System.out.println(String.format("%-49s %1s","Discount("+discountCause+"):",      			 "P"+String.format("%.02f", ((totalCost*1.2)*0.2))));
							System.out.println(String.format("%-49s %1s","Final amount:",   "P"+String.format("%.02f", (finalCost))));
							System.out.println("____________________________________________________________");}
							System.out.print("Enter amount of money being given: ");
	}

	public static void showFinalTotal(boolean discount) {
		String discountCause = (personWithDisabilities)? "PWD":"Senior Citizen";
		int orderCount = orderCount();
									 System.out.println("____________________________________________________________");
		                             System.out.println("                  BINARY BREWS PROG LAB 2");
		                             System.out.println("               Operated by: Class of 1A - G1");
		                             System.out.println("            BULACAN STATE UNIVERSITY PROG LAB 2");
		                             System.out.println("                         Philippines");
		                             System.out.println("                       VAT REGISTERED");
		                             System.out.println("                   TIN: 123-456-789-0000");
		                             System.out.println("           Serial Number: AP-1234-12345678910-05");
		                             System.out.println("                   MIN: 12345678901011121");
		                             System.out.println("                       SALES INVOICE");
		if(discount == true)         System.out.println("                          (EXEMPT)");
									 System.out.printf("%-35s %1s %n", "Terminal:  1", "Cashier: Self Checkout");
									 System.out.println("           Date and Time: 12/05/2024 01:45:12 pm");
									 System.out.printf("%-40s %1s %n","ST #: 1-23456789", "Order Number: 1");
									 System.out.println("            Trans #: "+String.format("%04d", orderCount)+ " Queue #: 01 Guest: 1");
								     System.out.println("_________________________Purchased__________________________");
		if(totalDrinks>0)   	     System.out.println("---------------------------Drinks---------------------------");
		for(int i = 0; i != 5; i++) {
			if(drink[i][0]+drink[i][1]+drink[i][2] > 0) {
				System.out.printf("%-24s",">"+drinkList[i]+":");
				for(int j = 0; j != 3; j++) {
					if(drink[i][j] > 0) System.out.printf("%-25s %1s", drink[i][j]+"x P"+String.format("%.02f",price[((i*3)+j)])+"/"+sizes[j], "P"+String.format("%.2f", drink[i][j]*price[((i*3)+j)])); 
					if(j !=2) {
						if(drink[i][j+1] > 0 && ((j==1 && drink[i][1] > 0) || drink[i][0] > 0)) System.out.printf("%n %-23s", "");
					}
					else System.out.println();
				}
			}
		}
		if((totalOrder-totalDrinks)>0)System.out.println("---------------------------Donuts---------------------------");
		if(pastry[0] > 0)            System.out.println(String.format("%-23s %-25s %1s",">"+pastryList[0]+":",pastry[0]+"x","P"+String.format("%.02f", (pastry[0]*price[15]))));
		if(pastry[1] > 0)            System.out.println(String.format("%-23s %-25s %1s",">"+pastryList[1]+":",pastry[1]+"x","P"+String.format("%.02f", (pastry[1]*price[16]))));
		if(pastry[2] > 0)            System.out.println(String.format("%-23s %-25s %1s",">"+pastryList[2]+":",pastry[2]+"x","P"+String.format("%.02f", (pastry[2]*price[17]))));
									 System.out.println("------------------------------------------------------------");
		if(totalDrinks>0)			 System.out.println(String.format("%-49s %1s","Total number of Drinks:      ",totalDrinks));
		if(totalOrder-totalDrinks>0) System.out.println(String.format("%-49s %1s","Total number of Pastries:     ",(totalOrder-totalDrinks)));
									 System.out.println(String.format("%-49s %1s","Total number of Purchases:",   totalOrder));
						 			 System.out.println(String.format("%-49s %1s","Total Value of Purchases:",     "P"+String.format("%.02f", totalCost)));
									 System.out.println(String.format("%-49s %1s","VAT (Variable Added Tax):","P"+String.format("%.02f", (totalCost*0.2))));
		if(discount == true)		 System.out.println(String.format("%-49s %1s","Discount("+discountCause+"):",      "P"+		  String.format("%.02f", ((totalCost*1.2)*0.2))));
									 System.out.println(String.format("%-49s %1s","Total Cost of Order:",     "P"+String.format("%.02f", (finalCost))));
									 System.out.println(String.format("%-49s %1s","Tendered Cash:",           "P"+String.format("%.02f", money)));
		if(money - finalCost>= 0.005)System.out.println(String.format("%-49s %1s","Change:",         "P"+		  String.format("%.02f", (money-finalCost))));
		  							 System.out.println("____________________________________________________________");
		  							 System.out.println("TIN: ");
		  							 System.out.println("ID: 2024");
		  							 System.out.println("Name: Group 5");
		  							 System.out.println("Signature: ");
		  							 System.out.println();
		  							 System.out.println("Name: ");
		  							 System.out.println("Address: ");
		  							 System.out.println("Tin: ");
		  							 System.out.println("Business Type: ");
		  							 System.out.println();
		  							 System.out.println("THIS SERVES AS A SALES INVOICE.");
		  							 System.out.println("We look forward to seeing you again!");
		  							 System.out.println("Contact us at: 0xxx xxxx xxx");
		  							 System.out.println("or email at: BinaryBrews@email.com");
		 recordReceipt(discountCause, orderCount);
	}
	
	public static int orderCount() {
		int orderNumber = 0, seniorNumber = 0, pwdNumber = 0, discountNumber = 0;
		double grossIncome = 0;
		File file = new File("OrderData.txt");
		try {
			if(file.createNewFile()) {
				try (FileWriter writer = new FileWriter("OrderData.txt")) {
					writer.write("Gross Income: "+(grossIncome)+"\n");
					writer.write("Next Order Number: "+(++orderNumber)+"\n");
					writer.write("Senior Count: "+(seniorNumber)+"\n");
					writer.write("PWD Count: "+(pwdNumber)+"\n");
					writer.write("Discount Count: "+ discountNumber);
				} catch(IOException e) {
					System.out.println("Error Occured");
				}
			}
		} catch(IOException e) {
			System.out.println("Error Occured");
		}
		if(file.isFile()) {
				try {
					FileReader read = new FileReader("OrderData.txt");
					BufferedReader reader = new BufferedReader(read);
					grossIncome = Double.parseDouble((reader.readLine()).replaceAll("[a-zA-Z:]",""));
					orderNumber = Integer.parseInt((reader.readLine()).replaceAll("[\\D]",""));
					seniorNumber = Integer.parseInt((reader.readLine()).replaceAll("[\\D]",""));
					pwdNumber = Integer.parseInt((reader.readLine()).replaceAll("[\\D]",""));		
					reader.close();
				} catch (FileNotFoundException e) {
					System.out.println("File does not exist...?");
				} catch (IOException e) {
					System.out.println("Error Occured");
				}
		}
		try (FileWriter writer = new FileWriter("OrderData.txt")) {
			writer.write("Gross Income: "+(String.format("%.02f", grossIncome+finalCost))+"\n");
			writer.write("Next Order Number: "+(++orderNumber)+"\n");
			if(discount) {
				if(personWithDisabilities)pwdNumber++; 
				else seniorNumber++;
			}
			writer.write("Senior Count: "+ seniorNumber+"\n");
			writer.write("PWD Count: "+ pwdNumber+"\n");
			discountNumber = seniorNumber +pwdNumber;
			writer.write("Discount Count: "+ discountNumber);
			
		}catch(FileNotFoundException e) {
			System.out.println("File does not exist...?");
		}catch(IOException e) {
			System.out.println("Error Occured");
		}
		return(--orderNumber);
	}

	static void recordReceipt(String discountCause, int orderCount) {
		File file = new File("ReceiptHistory.txt");
		try {
			file.createNewFile();
		} catch(IOException e) {
			System.out.println("Error Occured");
		}
		try (FileWriter writer = new FileWriter("ReceiptHistory.txt")) {
									writer.append("____________________________________________________________\n");
									writer.append("                BYTES AND FLUIDS PROG LAB 2\n");
									writer.append("               Operated by: Class of 1A - G1\n");
									writer.append("            BULACAN STATE UNIVERSITY PROG LAB 2\n");
									writer.append("                         Philippines\n");
									writer.append("                       VAT REGISTERED\n");
									writer.append("                   TIN: 123-456-789-0000\n");
									writer.append("           Serial Number: AP-1234-12345678910-05\n");
									writer.append("                   MIN: 12345678901011121\n");
									writer.append("                       SALES INVOICE\n");
	    if(discount == true)        writer.append("                          (EXEMPT)\n");
	   								writer.append("Terminal:  1                          Cashier: Self Checkout\n");
									writer.append("           Date and Time: 12/05/2024 01:45:12 pm\n");
									writer.append("ST #: 1-23456789                             Order Number: 1\n");
									writer.append("            Trans #: "+String.format("%04d", orderCount)+ " Queue #: 01 Guest: 1\n");
									writer.append("_________________________Purchased__________________________\n");
	   if(totalDrinks>0)   	        writer.append("---------------------------Drinks---------------------------\n");
	   if((drink[0][0]+drink[0][1]+drink[0][2])> 0)writer.append(String.format("%-16s %-32s %1s",">"+drinkList[0]+":",drink[0][0]+"x P"+price[0]+" | "+drink[0][1]+"x P"+price[1]+" | "+drink[0][2]+"x P"+price[2]+" :","P"+String.format("%.02f", ((drink[0][0]*price[0])+(drink[0][1]*price[1])+(drink[0][2]*price[2]))))+"\n");
	   if((drink[1][0]+drink[1][1]+drink[1][2])> 0)writer.append(String.format("%-16s %-32s %1s",">"+drinkList[1]+":",drink[1][0]+"x P"+price[3]+" | "+drink[1][1]+"x P"+price[4]+" | "+drink[1][2]+"x P"+price[5]+" :","P"+String.format("%.02f", ((drink[1][0]*price[3])+(drink[1][1]*price[4])+(drink[1][2]*price[5]))))+"\n");
	   if((drink[2][0]+drink[2][1]+drink[2][2])> 0)writer.append(String.format("%-16s %-32s %1s",">"+drinkList[2]+":",drink[2][0]+"x P"+price[6]+" | "+drink[2][1]+"x P"+price[7]+" | "+drink[2][2]+"x P"+price[8]+" :","P"+String.format("%.02f", ((drink[2][0]*price[6])+(drink[2][1]*price[7])+(drink[2][2]*price[8]))))+"\n");
	   if((drink[3][0]+drink[3][1]+drink[3][2])> 0)writer.append(String.format("%-16s %-32s %1s",">"+drinkList[3]+":",drink[3][0]+"x P"+price[9]+" | "+drink[3][1]+"x P"+price[10]+" | "+drink[3][2]+"x P"+price[11]+" :","P"+String.format("%.02f", ((drink[3][0]*price[9])+(drink[3][1]*price[10])+(drink[3][2]*price[11]))))+"\n");
	   if((drink[4][0]+drink[4][1]+drink[4][2])> 0)writer.append(String.format("%-16s %-32s %1s",">"+drinkList[4]+":",drink[4][0]+"x P"+price[12]+" | "+drink[4][1]+"x P"+price[13]+" | "+drink[4][2]+"x P"+price[14]+" :","P"+String.format("%.02f", ((drink[4][0]*price[12])+(drink[4][1]*price[13])+(drink[4][2]*price[14]))))+"\n");
	   if((totalOrder-totalDrinks)>0)writer.append("--------------------------Pastries--------------------------\n");
	   if(pastry[0] > 0)            writer.append(String.format("%-18s %-30s %1s",">"+pastryList[0]+":",pastry[0]+"x","P"+String.format("%.02f", (pastry[0]*price[15])))+"\n");
	   if(pastry[1] > 0)            writer.append(String.format("%-18s %-30s %1s",">"+pastryList[1]+":",pastry[1]+"x","P"+String.format("%.02f", (pastry[1]*price[16])))+"\n");
	   if(pastry[2] > 0)            writer.append(String.format("%-18s %-30s %1s",">"+pastryList[2]+":",pastry[2]+"x","P"+String.format("%.02f", (pastry[2]*price[17])))+"\n");
									writer.append("------------------------------------------------------------\n");
	   if(totalDrinks>0)			writer.append(String.format("%-49s %1s","Total number of Drinks:      ",totalDrinks)+"\n");
	   if(totalOrder-totalDrinks>0) writer.append(String.format("%-49s %1s","Total number of Pastries:     ",(totalOrder-totalDrinks))+"\n");
									writer.append(String.format("%-49s %1s","Total number of Purchases:",   totalOrder)+"\n");
									writer.append(String.format("%-49s %1s","Total Value of Purchases:",     "P"+String.format("%.02f", totalCost))+"\n");
									writer.append(String.format("%-49s %1s","VAT (Variable Added Tax):","P"+String.format("%.02f", (totalCost*0.2)))+"\n");
	   if(discount == true)		    writer.append(String.format("%-49s %1s","Discount("+discountCause+"):",      "P"+		  String.format("%.02f", ((totalCost*1.2)*0.2)))+"\n");
									writer.append(String.format("%-49s %1s","Total Cost of Order:",     "P"+String.format("%.02f", (finalCost)))+"\n");
									writer.append(String.format("%-49s %1s","Tendered Cash:",           "P"+String.format("%.02f", money))+"\n");
	   if(money - finalCost>= 0.005)writer.append(String.format("%-49s %1s","Change:",         "P"+		  String.format("%.02f", (money-finalCost)))+"\n");
									writer.append("____________________________________________________________\n");
									writer.append("TIN: \n");
									writer.append("ID: 2024\n");
									writer.append("Name: Group 5\n");
									writer.append("Signature: \n");
									writer.append("Name: \n");
									writer.append("Address: \n");
									writer.append("Tin: \n");
									writer.append("Business Type: \n");
									writer.append("THIS SERVES AS A SALES INVOICE.\n");
									writer.append("We look forward to seeing you again!\n");
									writer.append("Contact us at: 0xxx xxxx xxx\n");
									writer.append("or email at: businessemail@email.com\n");
		} catch(IOException e) {
			System.out.println("Error Occured");
		}
	}

	public static void removeAllOrders() {
		for(int i =0; i != 5; i++) for(int j = 0; j !=3; j++) drink[i][j] = 0;
		for(int i =0; i !=3; pastry[i]=0, i++);
		totalDrinks = 0;
		totalOrder = 0;
		totalCost = 0;
	}
	
}
