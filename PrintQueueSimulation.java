import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class PrintQueueSimulation {
	
	PriorityQueue<Job>waitQueue;
	int totalWaitTime;
	Queue<Job>finishedQueue;
	public int currentTime =0;
	Printer[]printer;
	Random randy;
	int numberOfPrinters;
	int numberOfPrintJobs;
	
	PrintQueueSimulation(int numOfJ, int numOfPrint, int seed){
		numberOfPrinters = numOfPrint;
		numberOfPrintJobs = numOfJ;
		randy = new Random(seed);
		waitQueue  = new PriorityQueue<Job>(numOfJ);
		finishedQueue = new PriorityQueue<Job>(numOfJ);
		printer = new Printer[numOfPrint];
		for(int i =0;i<numOfPrint;i++) {
			printer[i]= new Printer("Printer"+i);
		}
	}
	
	public void simulate() {
		boolean flagDone = false;
		int jobNumber=0;
		while(flagDone==false){
			if(currentTime%80==0&&jobNumber<numberOfPrintJobs) {
					Job jobNew = new Job(currentTime,randy.nextInt(10,1101),randy.nextInt(1,12));
					waitQueue.add(jobNew);
					jobNumber++;
			}
			
			for(Printer print:printer) {
				if(print.getJob()!=null) {
					if(currentTime-print.printJob.getStartTime()>=print.printJob.timeForJob) {
						print.printJob.setEndTime(currentTime);
						finishedQueue.add(print.printJob);
						print.printJob=null;
						print.setStartIdleTime(currentTime);
					}						
				}
			}
			for(Printer print:printer) {
				Job jobtest = print.getJob();
				if(jobtest==null) {
					if(!waitQueue.isEmpty()) {
						jobtest=waitQueue.remove();
						print.getTotalIdleTime(currentTime);
						jobtest.setStartTime(currentTime);
						print.setJob(jobtest);
						print.setStartInUseTime(currentTime);
						totalWaitTime+=jobtest.getWaitTime();
					}
				}
			}
			currentTime++;
			if(jobNumber>=numberOfPrintJobs&&waitQueue.isEmpty()) {
				flagDone=true;
				for(Printer print:printer) {
					if(print.getJob()!=null) {
						flagDone=false;
					}
					else {
						print.getTotalIdleTime(currentTime);
					}
				}
			}
		}
	}
	
	public void displayStatistics() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter the name of your output file for the results:");
		String outputFile = input.next();
		PrintWriter outWriter = new PrintWriter(outputFile);
		outWriter.printf("Simulation Results\n");
		outWriter.printf("Simulation with %d printers lasted %d seconds and processed %d jobs\n",numberOfPrinters, currentTime,numberOfPrintJobs);
		double dTotWaitTime = (double)totalWaitTime;
		double dTnumPrintJobs = (double)numberOfPrintJobs;
		double avgWait = (double)(dTotWaitTime/dTnumPrintJobs);
		
		outWriter.printf("The average time in the wait queue for a job is %.2f seconds\n", avgWait);
		outWriter.printf("\nPrinter Statistics\n");
		outWriter.printf("%16S%11S%7S\n","Jobs","Time","Time");
		outWriter.printf("%6S%13S%8S%7S\n", "Name", "Processed", "In Use","Idle");
		for(int i=0;i<printer.length;i++) {
			outWriter.printf("%8S%7d%12d%7d\n", printer[i].printerName, printer[i].getTotalJobsProcessed(), printer[i].getTotalInUseTime(),printer[i].totalIdleTime);
		}
		outWriter.printf("\n\nJob Statistics\nJob No. Priority Wait Time Length of Job\n");
		int len = finishedQueue.size();
		for(int i =0;i<len;i++){
			
			Job j = finishedQueue.remove();
			outWriter.printf("%4d%8d%11d%12d\n", j.getID(),j.getPriority(),j.getWaitTime(),j.getTimeForJob());
		}
	
		outWriter.close();
		input.close();
	}
	
}
