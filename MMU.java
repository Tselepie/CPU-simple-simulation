import java.util.ArrayList;

public class MMU {

    private final int[] availableBlockSizes;
    private MemoryAllocationAlgorithm algorithm;
    private ArrayList<MemorySlot> currentlyUsedMemorySlots;

    public MMU(int[] availableBlockSizes, MemoryAllocationAlgorithm algorithm) {
        this.availableBlockSizes = availableBlockSizes;
        this.algorithm = algorithm;
        this.currentlyUsedMemorySlots = new ArrayList<MemorySlot>();
    }

    public boolean loadProcessIntoRAM(Process p) {
        boolean fit = false;

        if (!p.getPCB().getPlaced()) {
            if (algorithm.fitProcess(p, currentlyUsedMemorySlots) != -1) {
                fit = true;
            }
        }
        else if (p.getPCB().getState().equals(ProcessState.TERMINATED)) {
            for (MemorySlot slot : currentlyUsedMemorySlots) {

                if (slot.getBlockEnd() - slot.getEnd() == availableBlockSizes[slot.getBlockStart()]) {
                    availableBlockSizes[slot.getBlockStart()] += slot.getEnd();
                    currentlyUsedMemorySlots.remove(new MemorySlot(slot.getStart(), slot.getEnd(),
                            slot.getBlockStart(), slot.getBlockEnd()));
                }
            }
        }


        return fit;
    }
}
