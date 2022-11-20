package com.cte;

import com.cte.services.BorrowService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    @Mock
    BorrowService borrowService;


    @Test
    @DisplayName("Test if book is bookable")
    public void bookingIsPossibleIfBookAvailable(){
        BorrowRequest borrowRequest = new BorrowRequest("title1");
        //borrowService.checkAvailability(borrowRequest);

        assertEquals(true,borrowService.checkAvailability(borrowRequest));
    }
}
