package RITClass;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class RITClassTest {
    @Test
    public void testNaturalOrder() {
        RITClass[] classes = {
            new RITClass("GCIS 124-02"),
            new RITClass("GCIS 124-01"),
            new RITClass("CSCI 101-01")
        };
        Arrays.sort(classes);
        assertEquals("CSCI 101-01", classes[0].toString());
        assertEquals("GCIS 124-01", classes[1].toString());
        assertEquals("GCIS 124-02", classes[2].toString());
    }

    @Test
    public void testAlternateOrder() {
        TreeSet<RITClass> set = new TreeSet<>(new RITClass.DescendingComparator());
        set.add(new RITClass("GCIS 124-02"));
        set.add(new RITClass("GCIS 124-01"));
        set.add(new RITClass("CSCI 101-01"));
        Object[] result = set.toArray();
        assertEquals("GCIS 124-02", result[0].toString());
        assertEquals("GCIS 124-01", result[1].toString());
        assertEquals("CSCI 101-01", result[2].toString());
    }
}

