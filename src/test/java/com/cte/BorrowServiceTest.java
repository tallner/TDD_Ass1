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
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    private ArrayList<Book> bookArrayList;
    private BorrowService borrowService;
    private SearchService searchService;
    private PaymentService paymentService;
    private DatabaseService databaseService;

   // @Mock
   // BorrowService borrowService;


    @BeforeEach
    public void  setUp() {

        bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("title1","Book genre","Book writer","Book publicity date","Book rating",true));
        bookArrayList.add(new Book("title2","Drama","Joe2","2020-02-19","2",true));
        bookArrayList.add(new Book("title3","Drama","Joe3","2020-03-19","3",true));
        bookArrayList.add(new Book("title4","Drama","Joe4","2020-04-19","4",true));

        paymentService = mock(PaymentService.class);
        databaseService = mock(DatabaseService.class);

        when(databaseService.readBooksFromDatabase()).thenReturn(bookArrayList);

        searchService = new SearchService(databaseService);
        borrowService = new BorrowService(searchService,paymentService);


    }


    @Test
    @DisplayName("Test if book is bookable")
    public void bookingIsPossibleIfBookAvailable(){
        BorrowRequest borrowRequest = new BorrowRequest("title1");

        assertTrue(borrowService.checkAvailability(borrowRequest));
    }
}
