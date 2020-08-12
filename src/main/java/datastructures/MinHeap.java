package datastructures;

public class MinHeap {
    int[]heap;
    int lastPos;
    int size;

    public MinHeap() {
        heap = new int[51];
        size = 51;
        lastPos = 0;

    }

    public void add(int i) {
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
        }
        int chieldValue = heap[pos];
        int parentPos = pos/2;
        int parentValue = heap[parentPos];
        if(parentValue > chieldValue) {
            swap(pos,parentPos);
            checkHeapRuleBottomUp(parentPos);
        }
    }

    private void checkHeapRuleTopDown(int pos) {
        int parentValue = heap[pos];
        int leftChildPos = pos*2;
        int rightChildPos = (pos*2)+1;
        int rightChildValue;
        int leftChildValue;

        if(leftChildPos > lastPos) {
            //done
        } else if(rightChildPos > lastPos) {
            leftChildValue = heap[leftChildPos];
            if(leftChildValue<parentValue) {
                swap(leftChildPos,pos);
            }
        } else {
            leftChildValue = heap[leftChildPos];
            rightChildValue = heap[rightChildPos];
            if(leftChildValue < rightChildValue) {
                if(leftChildValue<parentValue) {
                    swap(leftChildPos,pos);
                    checkHeapRuleTopDown(leftChildPos);
                }
            } else {
                if(rightChildValue < parentValue) {
                    swap(rightChildPos, pos);
                    checkHeapRuleTopDown(rightChildPos);
                }
            }
        }
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public int poll() {
        int min = heap[1];
        swap(1, lastPos);
        lastPos--;
        checkHeapRuleTopDown(1);
        return min;
    }

    public boolean isEmpty() {
        return lastPos == 0;
    }

    private void grow() {
        int[] newHeap = new int[((size-1)*2)+1];
        for(int i = 1; i<=lastPos;i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        size = ((size-1)*2)+1;
    }
    
    
}