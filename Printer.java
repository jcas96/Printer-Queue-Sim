
public class Printer {
	Job printJob;
	String printerName;
	int startIdleTime;
	int startInUseTime;
	int totalIdleTime;
	int totalInUseTime;
	int totalJobsProcessed;
	
	Printer(){
		
	}
	Printer(String name){
		setPrinterName(name);
	}
	
	
	public void setPrinterName(String n) {
		printerName =n;
	}
	public String getPrinterName() {
		return printerName;
	}
	
	public void setJob(Job j) {
		printJob = j;
	}
	public Job getJob() {
		return printJob;
	}
	
	public void setStartInUseTime(int start) {
		startInUseTime = start;
		totalJobsProcessed++;
	}
	
	public void setStartIdleTime(int sIdle) {
		startIdleTime = sIdle;
		totalInUseTime += startIdleTime-startInUseTime;
	}
	
	public int getTotalIdleTime(int current) {
		totalIdleTime= current-startIdleTime;
		return totalIdleTime;
	}
	
	public int getTotalInUseTime() {
		return totalInUseTime;
	}
	public int getTotalJobsProcessed() {
		return totalJobsProcessed;
	}
	
	

}
