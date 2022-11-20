package com.cte.services;

import com.cte.BorrowRequest;
import com.cte.models.Book;


public class BorrowService {

    private SearchService searchService;
    private PaymentService paymentService;

    public BorrowService(SearchService searchService,PaymentService paymentService) {
      this.searchService = searchService;
      this.paymentService = paymentService;
    }

    public boolean checkAvailability(BorrowRequest borrowRequest) {
        Book book = searchService.searchTitle(borrowRequest.getTitle());

        return book.getTitle()==borrowRequest.getTitle();

    }

}
