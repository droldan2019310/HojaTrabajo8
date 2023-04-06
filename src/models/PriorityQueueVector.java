package models;

import java.util.Vector;

import interfaces.IPriorityQueue;

public class PriorityQueueVector<E extends Comparable<E>> implements IPriorityQueue<E>{
    protected Vector<E> data; // the data, kept in heap order

    public PriorityQueueVector(){
        data = new Vector<E>();
    }

    public PriorityQueueVector(Vector<E> v)
    // post: constructs a new priority queue from an unordered vector
    {
        int i;
        data = new Vector<E>(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++)
        { // add elements to heap
            add(v.get(i));
        }
    }

    protected static int parent(int i)
    // pre: 0 <= i < size
    // post: returns parent of node at location i
    {
        return (i-1)/2;
    }

    protected static int left(int i)
    // pre: 0 <= i < size
    // post: returns index of left child of node at location i
    {
        return 2*i+1;
    }

    protected static int right(int i){
    // pre: 0 <= i < size
    // post: returns index of right child of node at location i
        return 2*(i+1);
    }

    @Override
    public E getFirst() {
        return data.get(0);
    }

    @Override
    public E remove() {
        E minVal = getFirst();
        data.set(0,data.get(data.size()-1));
        data.setSize(data.size()-1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    protected void pushDownRoot(int raiz)
    // pre: 0 <= raiz < size
    // post: moves node at index root down to appropriate position in subtree
    {
        int heapSize = data.size();
        E valor = data.get(raiz);
        while (raiz < heapSize) {
            int poshijo = left(raiz);
            if (poshijo < heapSize)
            {
                if ((right(raiz) < heapSize) && ((data.get(poshijo+1)).compareTo(data.get(poshijo)) < 0)) {
                    poshijo++;
                }
                if ((data.get(poshijo)).compareTo(valor) < 0) {
                    data.set(raiz,data.get(poshijo));
                    raiz = poshijo;
                } else {
                    data.set(raiz,valor);
                    return;
                }
            } else {
                data.set(raiz,valor);
                return;
            }
        }
    }

    protected void percolateUp(int hoja)
    // pre: 0 <= leaf < size
    // post: moves node at index leaf up to appropriate position
    {
        int padre = parent(hoja);
        E value = data.get(hoja);
        while (hoja > 0 &&
                (value.compareTo(data.get(padre)) < 0))
        {
            data.set(hoja,data.get(padre));
            hoja = padre;
            padre = parent(hoja);
        }
        data.set(hoja,value);
    }

    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size()-1);
    }

    @Override
    public boolean isEmpty() {
        if (data.size()==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {

    }
    
    public E pop() {
		
		return data.remove(data.size()-1);
	}

	public PriorityQueueVector<String> listVectorHeap(String[] str, int count, PriorityQueueVector<String> v) {
		for(int i = count-1;i>=0;i--) {
			v.add(str[i]);
		}
		return v;
	}
	
	public String[] toString(PriorityQueueVector<String> v,int count) {
		String str[] = new String[count];
		for(int i = 0; i<count; i++) {
			str[i] = v.pop();
		}
		return str;
	}
	
	public String[] sortHeap(String[] str, int contador) {
		String temp;
		for(int i = 0; i<contador;i++) {
			for(int j = i+1; j<contador; j++) {
				if(str[i].compareTo(str[j])>0) {
					temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}
	

	public E get(int index) {
		return data.get(index);
	}

}