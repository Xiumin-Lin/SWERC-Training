package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonneTest {
    private Personne p1, p2, p3;

    @BeforeEach
    void setUp() {
        p1 = new Personne(1,null,null);
        p2 = new Personne(2, null, null);
        p3 = new Personne(3,null,null);
    }

    @AfterEach
    void tearDown() {
        p1 = null;
        p2 = null;
        p3 = null;
    }

    @Test
    void getVal() {
        Assertions.assertEquals(1, p1.getVal());
        Assertions.assertEquals(2, p2.getVal());
        Assertions.assertEquals(3, p3.getVal());
    }

    @Test
    void getPrev() {
        p1.setPrev(p3);
        Assertions.assertEquals(p1.getPrev(), p3);
        Assertions.assertNull(p2.getPrev());
    }

    @Test
    void setPrev() {
        getPrev();
    }

    @Test
    void getNext() {
        p1.setNext(p2);
        Assertions.assertEquals(p1.getNext(), p2);
        Assertions.assertNull(p3.getNext());
    }

    @Test
    void setNext() {
        getNext();
    }
}