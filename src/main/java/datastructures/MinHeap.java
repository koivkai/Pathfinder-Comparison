package datastructures;

/**
 * A datastructure(minheap). Currently unused.
 */
public class MinHeap {
    Cordinate[]heap;
    int lastPos;
    int size;

    public MinHeap() {
        heap = new Cordinate[51];
        size = 51;
        lastPos = 0;

    }

    public void add(Cordinate i) {
        if(lastPos +1 == size) {
            grow();
            add(i);
        } else {
            heap[lastPos +1] = i;
            lastPos++;
            checkHeapRuleBottomUp(lastPos);
        }

    }

    private void checkHeapRuleBottomUp(int pos) {
        if(pos == 1) {
            //done
        } else {
            Cordinate child = heap[pos];
            int parentPos = pos/2;
            Cordinate parent = heap[parentPos];
            if(parent.getDistanceEstimate() > child.getDistanceEstimate()) {
                swap(pos,parentPos);
                checkHeapRuleBottomUp(parentPos);
            }  
        }
        
    }

    private void checkHeapRuleTopDown(int pos) {
        Cordinate parent = heap[pos];
        int leftChildPos = pos*2;
        int rightChildPos = (pos*2)+1;
        Cordinate rightChild;
        Cordinate leftChild;

        if(leftChildPos > lastPos) {
            //done
        } else if(rightChildPos > lastPos) {
            leftChild = heap[leftChildPos];
            if(leftChild.getDistanceEstimate()<parent.getDistanceEstimate()) {
                swap(leftChildPos,pos);
            }
        } else {
            leftChild = heap[leftChildPos];
            rightChild = heap[rightChildPos];
            if(leftChild.getDistanceEstimate() < rightChild.getDistanceEstimate()) {
                if(leftChild.getDistanceEstimate()<parent.getDistanceEstimate()) {
                    swap(leftChildPos,pos);
                    checkHeapRuleTopDown(leftChildPos);
                }
            } else {
                if(rightChild.getDistanceEstimate() < parent.getDistanceEstimate()) {
                    swap(rightChildPos, pos);
                    checkHeapRuleTopDown(rightChildPos);
                }
            }
        }
    }

    private void swap(int a, int b) {
        Cordinate temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public Cordinate poll() {
        Cordinate min = heap[1];
        swap(1, lastPos);
        lastPos--;
        checkHeapRuleTopDown(1);
        return min;
    }

    public boolean isEmpty() {
        return lastPos == 0;
    }

    private void grow() {
        Cordinate[] newHeap = new Cordinate[((size-1)*2)+1];
        for(int i = 1; i<=lastPos;i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        size = ((size-1)*2)+1;
    }
    
    
}