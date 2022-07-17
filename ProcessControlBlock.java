import java.util.ArrayList;

public class ProcessControlBlock {
    
    private final int pid;
    private ProcessState state;
    // the following two ArrayLists should record when the process starts/stops
    // for statistical purposes
    private ArrayList<Integer> startTimes; // when the process starts running
    private ArrayList<Integer> stopTimes;  // when the process stops running
    
    private static int pidTotal= 0;
    
    public ProcessControlBlock() {
        this.state = ProcessState.NEW;
        this.startTimes = new ArrayList<Integer>();
        this.stopTimes = new ArrayList<Integer>();
        
        int PID_MAX_LIMIT= 32768; // the maximum value of a PID for 32-bit machine
        
        if(pidTotal < PID_MAX_LIMIT)
            pidTotal++;
        else
            pidTotal= 0;
            
        this.pid = pidTotal; // change this line
        
    }

    public ProcessState getState() {
        return this.state;
    }
    
    public void setState(ProcessState state, int currentClockTime) {         
         if(state.equals(ProcessState.READY)){
             if(this.state.equals(ProcessState.RUNNING))
                 this.stopTimes.add(currentClockTime);
         } else if(state.equals(ProcessState.RUNNING))
             this.startTimes.add(currentClockTime);
         
         this.state = state;
        
    }
    
    public int getPid() { 
        return this.pid;
    }
    
    public ArrayList<Integer> getStartTimes() {
        return startTimes;
    }
    
    public ArrayList<Integer> getStopTimes() {
        return stopTimes;
    }
    
}
