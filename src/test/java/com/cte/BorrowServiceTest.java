package com.cte;

import com.cte.services.BorrowService;
import com.cte.services.DatabaseService;
import com.cte.services.PaymentService;
import com.cte.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
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

        searchService = mock(SearchService.class);
        borrowService = new BorrowService(searchService,paymentService);

        payArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

    }

    @ParameterizedTest
    @DisplayName("Test if book is bookable")
    @ValueSource(strings = "title1")
    public void bookingIsPossibleIfBookAvailable(String title) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(0));
        assertTrue(borrowService.checkAvailability(borrowRequest));
    }

    @ParameterizedTest
    @DisplayName("Book a book and check that is unavailable")
    @ValueSource(strings = "title1")
    public void bookingOfOneBookAndCheckItIsBooked(String title) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(0));
        String expected = "Book borrowed";
        String actual = borrowService.bookOneBook(borrowRequest);

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @DisplayName("Book an unavailable book should not work")
    @ValueSource(strings = "title5")
    public void bookingOfAnUnavailableBook(String title) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(4));

        String expected = "Book not available";
        String actual = borrowService.bookOneBook(borrowRequest);

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @DisplayName("Book a book and check payment is sent to paymentService")
    @ValueSource(strings = "title1")
    public void bookingOfOneBookAndCheckCorrectAmountSentToPayment(String title) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(0));
        borrowService.bookOneBook(borrowRequest);

        verify(paymentService, times(1)).pay(payArgumentCaptor.capture());

        int expected = testData.getTestData().get(0).getPrice();
        int actual = payArgumentCaptor.getValue();

        assertEquals(expected, actual);
    }
}
