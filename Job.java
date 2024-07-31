
public class Job implements Comparable<Job>{
	int id;
	int arrivalTime;
	int timeForJob;
	int priority;
	private int startTime;
	int waitTime;
	int endTime;
	static int idCounter=1;
	
	
	Job(){
	}
	
	Job(int aTime, int jobTime,int prio){
		setID();
		setArrivalTime(aTime);
		setTimeForJob(jobTime);
		setPriority(prio);
	}
	
	public void setID() {
		id=idCounter;
		idCounter++;
	}
	public int getID() {
		return id;
	}
	
	public void setArrivalTime(int at) {
		arrivalTime=at;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setTimeForJob(int tJ) {
		timeForJob = tJ;
	}
	public int getTimeForJob() {
		return timeForJob;
	}
	
	public void setPriority(int p ) {
		priority = p;
	}
	public int getPriority() {
		return priority;
	}
	
	public void setStartTime(int start) {
		startTime=start;
		waitTime=startTime-arrivalTime;
	}
	public int getStartTime() {
		return startTime;
	}
	
	public void setEndTime(int end) {
		endTime = end;
	}
	public int getEndTime() {
		return endTime;
	}
	public int getWaitTime() {
		return waitTime;
	}
	
	@Override
	public int compareTo(Job o) {
		if(priority==o.priority) {
			return 0;
		}else if(priority>o.priority) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
