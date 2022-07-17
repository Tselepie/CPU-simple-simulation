
public class Process {
    private ProcessControlBlock pcb;
    private int arrivalTime;
    private int burstTime;
    private int memoryRequirements;
    private double timeInBackground;
    
    public Process(int arrivalTime, int burstTime, int memoryRequirements) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.memoryRequirements = memoryRequirements;
        this.pcb = new ProcessControlBlock();
        this.timeInBackground = 0;
    }
    
    public ProcessControlBlock getPCB() {
        return this.pcb;
    }
   
    public void run() {
        pcb.setState(ProcessState.RUNNING, CPU.clock);
    }
    
    public void waitInBackground() {
        this.timeInBackground = CPU.clock - this.arrivalTime;
        this.pcb.setState(ProcessState.READY, CPU.clock);
    }

    public double getWaitingTime() {
        return getTurnAroundTime() - burstTime;
    }
    
    public double getResponseTime() {
        return pcb.getStopTimes().get(0) - pcb.getStartTimes().get(0);
    }
    
    public double getTurnAroundTime() {
        // turnaround time= completion time- arrival time
        return pcb.getStopTimes().get(pcb.getStopTimes().size() - 1) - arrivalTime;
    }
}
