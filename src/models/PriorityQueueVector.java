package models;

import java.util.Vector;

import interfaces.IPriorityQueue;

public class PriorityQueueVector<E extends Comparable<E>> implements IPriorityQueue<E>{
    private Vector<E> data; // the data, kept in heap order

    /**
     * construction empty
     */
    public PriorityQueueVector(){
        data = new Vector<E>();
    }

    /**
     * constructor with vector
     * @param v
     */
    public PriorityQueueVector(Vector<E> v)
    // post: constructs a new priority queue from an unordered vector
    {
        int i;
        data = new Vector<E>(v.size()); 
        for (i = 0; i < v.size(); i++)
        { 
            add(v.get(i));
        }
    }

    /**
     * get parent
     * @param i
     * @return the position of the parent
     */
    private static int parent(int i){
        return (i-1)/2;
    }

    /**
     * get  left child
     * @param i
     * @return position left child
     */
    private static int left(int i){
        return 2*i+1;
    }

    /**
     * get right child
     * @param i
     * @return position right child
     */
    private static int right(int i){
        return 2*(i+1);
    }

    /**
     * get first element
     */
    @Override
    public E getFirst() {
        return data.get(0);
    }

    /**
     * remove element
     */
    @Override
    public E remove() {
        E minVal = getFirst();
        data.set(0,data.get(data.size()-1));
        data.setSize(data.size()-1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    /**
     * set root in child
     * @param raiz
     */
    private void pushDownRoot(int raiz)
 
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

    
    @Override
    /**
     * insert element
     */
    public void add(E value) {
        data.add(value);
        percolateUp(data.size()-1);
    }

    /**
     * validate if is empty
     */
    @Override
    public boolean isEmpty() {
        if (data.size()==0){
            return true;
        }
        return false;
    }

    /**
     * get size of element
     */
    @Override
    public int size() {
        return data.size();
    }

    
    /**
     * remove element public method
     */
    public E pop() {
		
		return data.remove(data.size()-1);
	}

	
	/**
     * 
     * @param v
     * @param count
     * @return return string of all the elements
     */
	public String[] toString(PriorityQueueVector<String> v,int count) {
		String str[] = new String[count];
		for(int i = 0; i<count; i++) {
			str[i] = v.pop();
		}
		return str;
	}
	
    /*
     * order vector
     */
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
	
    /**
     * search element
     * @param index
     * @return
     */
	public E get(int index) {
		return data.get(index);
	}

    /**
     * return vector added
     * @param str
     * @param count
     * @param v
     * @return
     */
    public PriorityQueueVector<String> listVectorHeap(String[] str, int count, PriorityQueueVector<String> v) {
		for(int i = count-1;i>=0;i--) {
			v.add(str[i]);
		}
		return v;
	}

    /**
     * validate if the parent have the correct position
     * @param hoja
     */

    private void percolateUp(int hoja){
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
}