import java.util.ArrayList;

public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private int numChildren; // 2^x

    public PowerOfTwoMaxHeap(int exponent) {
        this.heap = new ArrayList<>();
        this.numChildren = (int) Math.pow(2, exponent);
    }

    public void insert(int value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");

        int max = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            bubbleDown(0);
        }

        return max;
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / numChildren;
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else break;
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int largest = index;
            for (int i = 1; i <= numChildren; i++) {
                int childIndex = numChildren * index + i;
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                    largest = childIndex;
                }
            }
            if (largest == index) break;
            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); // 2^2 = 4 children
        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.insert(25);
        
        System.out.println(heap.popMax()); // 30
        System.out.println(heap.popMax()); // 25
    }
}
