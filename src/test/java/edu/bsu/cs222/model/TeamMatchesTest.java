package edu.bsu.cs222.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamMatchesTest {
    @Test
    public void findMatchTest(){
        List<Object> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        List<Object> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("3");
        List<Object> same = TeamMatches.findMatch(list1,list2);
        List<Object> list3 = new ArrayList<>();
        list3.add("2");
        list3.add("3");
        boolean b = same.equals(list3);
        assertTrue(b);
    }
}
