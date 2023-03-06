package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SlidingWindowTest {

    SlidingWindow underTest;

    @Test
    public void grantAccessTest(){
        //given
        underTest = new SlidingWindow(0, 1);
        //when
        boolean expected = underTest.grantAccess();
        boolean expected1 = underTest.grantAccess();

        //then
        assertEquals(expected, true);
        assertEquals(expected1, true);

    }


}