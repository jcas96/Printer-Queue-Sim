import java.io.IOException;
import java.util.Scanner;

public class TestPrintQueueSimulation {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.printf("Please enter the number of printers for the simulation: ");
		int numPrinters = input.nextInt();
		System.out.printf("Please enter the number of printer jobs for the simulation: ");
		int numOfJobs = input.nextInt();	
		System.out.printf("Please enter a random number seed for the simulation: ");
		int seed = input.nextInt();
		PrintQueueSimulation newQPrinter = new PrintQueueSimulation(numOfJobs,numPrinters,seed);
		newQPrinter.simulate();
		newQPrinter.displayStatistics();
		
		input.close();
	}

}
