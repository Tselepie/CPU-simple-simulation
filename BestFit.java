import java.util.ArrayList;

public class BestFit extends MemoryAllocationAlgorithm {
    
    public BestFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;

        int minSpace = 99999;
        // Find the proper block for the process
        for (int i = 0; i < availableBlockSizes.length; i++) {
            if (p.getMemoryRequirements() <= availableBlockSizes[i] && availableBlockSizes[i] <= minSpace) {
                minSpace = availableBlockSizes[i]; //minimum requirements needed
                address = i;
                fit = true;
            }
        }
        return address;
    }

}
