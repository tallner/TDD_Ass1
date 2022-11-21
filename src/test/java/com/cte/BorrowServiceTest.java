package com.cte;

import com.cte.models.Book;
import com.cte.services.BorrowService;
import com.cte.services.DatabaseService;
import com.cte.services.PaymentService;
import com.cte.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    private BorrowService borrowService;
    private SearchService searchService;
    private PaymentService paymentService;
    private DatabaseService databaseService;
    private TestData testData;
    ArgumentCaptor<Integer> payArgumentCaptor;

   // @Mock
   // BorrowService borrowService;


    @BeforeEach
    public void  setUp() throws IOException {
        testData = new TestData();

        paymentService = mock(PaymentService.class);
        databaseService = mock(DatabaseService.class);

        when(databaseService.readBooksFromDatabase()).thenReturn(testData.getTestData());

        searchService = new SearchService(databaseService);
        borrowService = new BorrowService(searchService,paymentService);

        payArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

    }

    @Test
    @DisplayName("Test if book is bookable")
    public void bookingIsPossibleIfBookAvailable(){
        BorrowRequest borrowRequest = new BorrowRequest("title1");

        assertTrue(borrowService.checkAvailability(borrowRequest));
    }

    @Test
    @DisplayName("Book a book and check that is unavailable")
    public void bookingOfOneBookAndCheckItIsBooked(){
        BorrowRequest borrowRequest = new BorrowRequest("title1");

        borrowService.bookOneBook(borrowRequest);

        assertFalse(borrowService.checkAvailability(borrowRequest));
    }

    @Test
    @DisplayName("Book a book and check payment is ok")
    public void bookingOfOneBookAndCheckItIsPayed(){
        BorrowRequest borrowRequest = new BorrowRequest("title1");

        borrowService.bookOneBook(borrowRequest);

        verify(paymentService, times(1)).pay(payArgumentCaptor.capture());
        int amountToPay = payArgumentCaptor.getValue();
        assertEquals(4, amountToPay);
    }
}
