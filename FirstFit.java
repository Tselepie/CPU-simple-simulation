//package com.company;
import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm {

    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }
//συνάρτηση στην οποία υλοποιείται ο firstfit
//αρχικά ελέγχουμε αν το διαθέσιμο μπλοκ είναι μεγαλύτερο από το μέγεθος της διεργασίας
//Στον παραπάνω έλεγχο αν δεν χρησημοποιείται κανένα memory slot τότε το δημιουργούμε εμείς
//Στη συνέχεια το προσθέτουμε στα memory slot που χρησημοποιούνται
//Στην περίπτωση που έχουμε χρησημοποιούμενα memory slots τότε τα ελέγχουμε όλα τα memory slots και αν χωράει κάπου η διεργασία μας την βάζουμε σε ένα timeslot
//και το τοποθετούμε στα timeslots που χρησημοποιούνται
//Αφού τελειώσουμε με όλα αυτά και η διεργασία μπορεί να φορτωθεί την τοποθετούμε σε ένα time slot η διεργασία γίνεται ready και επιστρέφεται το address
 public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;
        boolean load = false;
        boolean otherslot=false;
        int lastEnd = -1;


        /* TODO: you need to add some code here
         * Hint: this should return the memory address where the process was
         * loaded into if the process fits. In case the process doesn't fit, it
         * should return -1. */

        for (int i = 0; i < availableBlockSizes.length; i++) {

            if (availableBlockSizes[i] >= p.getMemoryRequirements()) {

                if (currentlyUsedMemorySlots.size() == 0) {


                    MemorySlot slot = new MemorySlot(0, p.getMemoryRequirements(), 0, availableBlockSizes[i]);
                    currentlyUsedMemorySlots.add(slot);
                    address = i;
                    return address;
                } else {


                    for (MemorySlot slot : currentlyUsedMemorySlots) {

                        if (slot.getBlockEnd() == availableBlockSizes[i]) {
                            otherslot = true;
                            address = i;


                            if (slot.getBlockEnd() - slot.getEnd() >= p.getMemoryRequirements()) {
                                load = true;
                                lastEnd = slot.getEnd();
                                address = i;


                            } else {

                                load = false;
                                address = -1;
                                break;

                            }
                        }

                    }
                }

            }
            if (address != -1) {
                break;
            }


        }

        if (otherslot) {

        if (load) {

                MemorySlot slot = new MemorySlot(lastEnd + 1, p.getMemoryRequirements() + 1 + lastEnd, 0, availableBlockSizes[address]);

            currentlyUsedMemorySlots.add(slot);

            }

            } else if(address != -1) {

              MemorySlot slot = new MemorySlot(0,p.getMemoryRequirements(),0,availableBlockSizes[address]);
              currentlyUsedMemorySlots.add(slot);
               fit=true;
        }


            if (address != -1) {
                p.getPCB().setState(ProcessState.READY, CPU.clock);
                fit=true;
            }
            return address;

        }
    }


