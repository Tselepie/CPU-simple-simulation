//package com.company;

public class FCFS extends Scheduler {

    public FCFS() {
        /* TODO: you _may_ need to add some code here */
    }
//συνάρτηση η οποία προσθέτει τη διεργασία p στη λίστα με τα processes
    public void addProcess(Process p) {
        /* TODO: you need to add some code here */
        this.processes.add(p);
    }
//συναρτηση η οποία παίρνει την επόμενη διεργασία και υλοποιεί τον fcfs.Δημιουργώ μια νέα διεργασία
    //Διατρέχουμε τον πίνακα των διεργασιών και σε περίπτωση που κάποια είναι ready τότε ξεκινά να τρέχει
    //Όταν τελειώσει η διεργασία γίνεται terminated και επιστρέφεται
    public Process getNextProcess() {
        /* TODO: you need to add some code here
         * and change the return value */
        Process d=new Process(0,0,0);
        
        for(Process p:processes){
            if(p.getPCB().getState()==ProcessState.READY){
                d.run();
               CPU.clock=CPU.clock+d.getBurstTime()-1;
                d=p;
                break;
            }


        }
        d.getPCB().setState(ProcessState.TERMINATED,CPU.clock);
        return d;
    }
}
