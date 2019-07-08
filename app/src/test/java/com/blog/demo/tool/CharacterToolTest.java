package com.blog.demo.tool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CharacterToolTest {

    @Test
    public void testJoin() {
        List<String> list = new ArrayList<>();
        list.add("Mike");

        assertEquals("Mike", CharacterTool.join(list, ","));

        list.add("Jack");
        assertEquals("Mike,Jack", CharacterTool.join(list, ","));
    }

}