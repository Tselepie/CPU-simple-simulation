import java.util.ArrayList;

public class CPU {

    public static int clock = 0; // this should be incremented on every CPU cycle

    private Scheduler scheduler;
    private MMU mmu;
    private Process[] processes;
    private int currentProcess;

    public CPU(Scheduler scheduler, MMU mmu, Process[] processes) {
        this.scheduler = scheduler;
        this.mmu = mmu;
        this.processes = processes;
    }

    public void run() {
        int min = Integer.MAX_VALUE;
        int pivot = 0;

        while(pivot < processes.length) { // sorts the Process[] array in order
            for (int i=pivot; i < processes.length; i++) {
                if (processes[i].getArrivalTime() < min) { // sorts from lower arrival time to higher
                    min = processes[i].getArrivalTime();
                    Process temp = processes[pivot];
                    processes[pivot] = processes[i];
                    processes[i] = temp;
                }
            }
            pivot++;
        }

        for (Process process : processes) { scheduler.addProcess(process); } // adds processes to "candidates for execution" list

        while (scheduler.processes.size() > 0) {
            Process process;
            currentProcess = scheduler.processes.get(0).getBurstTime(); // saves burst time of the candidate process
            scheduler.processes.get(0).getPCB().setState(ProcessState.READY, CPU.clock);
            process = scheduler.getNextProcess(); // first process in the list
            tick(); // new -> ready // 1 CPU cycle

            if(mmu.loadProcessIntoRAM(process)){ // checks if the process can be loaded to RAM
                process.getPCB().setPlaced(true); // placed to RAM

                if(process.getPCB().getState().equals(ProcessState.READY)){ // if it pauses
                    tick();                                                 // 2 CPU cycles
                    tick();
                    process.waitInBackground();
                }
                else {
                    mmu.loadProcessIntoRAM(process); // remove from RAM
                    scheduler.processes.remove(0); // if not then its TERMINATED
                }
            }
            else if(process.getPCB().getPlaced())
                continue;
            else {
                process.setBurstTime(currentProcess); // if not loaded resets burst time of the candidate process
                scheduler.processes.add(process);     // and adds it again to "candidates for execution" list
            }
        }
    }

    public void tick() {
        clock++;
    }
}
