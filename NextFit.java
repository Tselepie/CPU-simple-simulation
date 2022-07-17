import java.util.ArrayList;

public class NextFit extends MemoryAllocationAlgorithm {

    private static int pos = 0;

    public NextFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;

        int i=pos;
        do {
            if(p.getMemoryRequirements() <= availableBlockSizes[i]){
                MemorySlot slot = new MemorySlot(i, p.getMemoryRequirements(),
                        i, availableBlockSizes[i]);

                availableBlockSizes[i] -= slot.getEnd();
                currentlyUsedMemorySlots.add(new MemorySlot(slot.getStart(), slot.getEnd(), // start = blockStart = i
                        slot.getBlockStart(), slot.getBlockEnd())); // end = memoryRequirements, blockEnd = default available RAM block size
                address = i;
                fit = true;
                break;
            }
            i++;
            pos = (pos + 1)%availableBlockSizes.length; // loops the
        }while(i < availableBlockSizes.length);

        return address;
    }
}
