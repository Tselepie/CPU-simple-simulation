
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
        /* TODO: you need to add some code here
         * Hint: this should run every time a process starts running */
        pcb.setState(ProcessState.RUNNING, CPU.clock);
    }
    
    public void waitInBackground() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */
        this.timeInBackground = CPU.clock - this.arrivalTime;
        this.pcb.setState(ProcessState.READY, CPU.clock);
    }

    public double getWaitingTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        return getTurnAroundTime() - burstTime;
    }
    
    public double getResponseTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        return pcb.getStopTimes().get(0) - pcb.getStartTimes().get(0);
    }
    
    public double getTurnAroundTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        // turnaround time= completion time- arrival time
        return pcb.getStopTimes().get(pcb.getStopTimes().size() - 1) - arrivalTime;
    }
}
