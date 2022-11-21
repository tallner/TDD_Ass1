package com.cte;

import com.cte.models.Book;
import com.cte.services.BorrowService;
import com.cte.services.DatabaseService;
import com.cte.services.PaymentService;
import com.cte.services.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    private ArrayList<Book> bookArrayList;
    private BorrowService borrowService;
    private SearchService searchService;
    private PaymentService paymentService;
    private DatabaseService databaseService;
    ArgumentCaptor<Integer> payArgumentCaptor;

   // @Mock
   // BorrowService borrowService;


    @BeforeEach
    public void  setUp() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String filePath = "src/main/resources/BookDatabase.json";
        List<Book> books = Arrays.asList(mapper.readValue(Paths.get(filePath).toFile(), Book[].class));

        bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("title1","Book genre","Book writer","Book publicity date","Book rating",4,true));
        bookArrayList.add(new Book("title2","Drama","Joe2","2020-02-19","2",55,true));
        bookArrayList.add(new Book("title3","Drama","Joe3","2020-03-19","3",34,true));
        bookArrayList.add(new Book("title4","Drama","Joe4","2020-04-19","4",12,true));

        paymentService = mock(PaymentService.class);
        databaseService = mock(DatabaseService.class);

        when(databaseService.readBooksFromDatabase()).thenReturn(books);

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
