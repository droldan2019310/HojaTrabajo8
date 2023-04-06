package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.PriorityQueueVector;

public class PriorityQueueTest {
    PriorityQueueVector<String> vector = new PriorityQueueVector<String>();

    @Test
    public void insertTest(){
        vector.add("Maria Ramirez, apendicitis,A");
        assertEquals(vector.size(), 1);
    }


    @Test
    public void remoteTest(){
        vector.add("A, Maria Ramirez, apendicitis");
        vector.add("B, Carmen Sarmientos, dolores de parto");
        assertEquals(vector.pop(), "B, Carmen Sarmientos, dolores de parto");
    }
}


