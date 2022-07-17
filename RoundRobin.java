//package com.company;

public class RoundRobin extends Scheduler {

    private int quantum;

    public RoundRobin() {
        this.quantum = 1; // default quantum
    }

    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }
 //συνάρτηση η οποία προσθέτει τη διεργασία p στη λίστα με τα processes
    public void addProcess(Process p) {
        this.processes.add(p);
    }
//Συνάρτηση η οποία παίρνει παίρνει την επόμενη διεργασία και υλοποιεί τον round robin.
// Αφού ξεκινήσει η διεργασία να τρέχει ενημερώνεται το burst time και στη θέση του αρχικού μπαίνει αυτό που απομένει αφού μειώθηκε από το quantum
//Σε περίπτωση που απομένει burst time η διεργασία ξαναμπαίνει ready κ μπαίνει στη λίστα των processes
//Αν δεν έχει μείνει burst time η διεργασία γίνεται terminated και στο τέλος επιστρέφεται το αποτέλεσμα της
    public Process getNextProcess() {        
        Process k=processes.get(0);
        k.run();
        CPU.clock = CPU.clock + quantum - 1;
        k.setBurstTime(k.getBurstTime() - quantum);
        processes.remove(0);
        if(k.getBurstTime() > 0)
        {
            k.getPCB().setState(ProcessState.READY,CPU.clock);
            processes.add(k);
        }
        if(k.getBurstTime() <= 0 )
        {

            k.getPCB().setState(ProcessState.TERMINATED,CPU.clock);
        }



        return k;

    }
}
