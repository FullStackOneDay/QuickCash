package com.example.quick_cash_grp13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class createPostTest {
    public CreatePostActivity cpActivity = new CreatePostActivity();

    @Test
    public void checkSalaryIsValid(){
        assertTrue(cpActivity.isSalaryValid(5.25));
    }

    @Test
    public void checkSalaryIsInvalid(){
        assertFalse(cpActivity.isSalaryValid(-4.5));
    }

}
