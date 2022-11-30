package com.cte;

import com.cte.services.BorrowService;
import com.cte.services.DatabaseService;
import com.cte.services.PaymentService;
import com.cte.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    private TestData testData;

    @Mock
    private SearchService searchService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private BorrowService borrowService;
    @Captor
    private ArgumentCaptor<Integer> payArgumentCaptor;

    @BeforeEach
    public void  setUp() {
        testData = new TestData();
    }

    private static List<Arguments> testInputsAvailableBooks(){
        return Arrays.asList(
                Arguments.of("title1",0),
                Arguments.of("title2",1),
                Arguments.of("title3",2),
                Arguments.of("title4",3)
                );
    }

    private static List<Arguments> testInputsUnavailableBooks(){
        return Arrays.asList(
                Arguments.of("title5",4)
        );
    }

    @ParameterizedTest
    @DisplayName("Test if book is bookable")
    @MethodSource("testInputsAvailableBooks")
    public void bookingIsPossibleIfBookAvailable(String title, int index) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(index));
        assertTrue(borrowService.checkAvailability(borrowRequest));
    }

    @ParameterizedTest
    @DisplayName("Book a book and check that is unavailable")
    @MethodSource("testInputsAvailableBooks")
    public void bookingOfOneBookAndCheckItIsBooked(String title, int index) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(index));
        String expected = "Book borrowed";
        String actual = borrowService.bookOneBook(borrowRequest);

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @DisplayName("Book an unavailable book should not work")
    @MethodSource("testInputsUnavailableBooks")
    public void bookingOfAnUnavailableBook(String title, int index) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(index));

        String expected = "Book not available";
        String actual = borrowService.bookOneBook(borrowRequest);

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @DisplayName("Book a book and check payment is sent to paymentService")
    @MethodSource("testInputsAvailableBooks")
    public void bookingOfOneBookAndCheckCorrectAmountSentToPayment(String title, int index) throws IOException {
        BorrowRequest borrowRequest = new BorrowRequest(title);
        when(searchService.searchTitle(title)).thenReturn(testData.getTestData().get(index));
        borrowService.bookOneBook(borrowRequest);

        verify(paymentService, times(1)).pay(payArgumentCaptor.capture());

        int expected = testData.getTestData().get(index).getPrice();
        int actual = payArgumentCaptor.getValue();

        assertEquals(expected, actual);
    }
}
