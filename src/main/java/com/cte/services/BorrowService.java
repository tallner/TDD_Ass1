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

    public Boolean checkAvailability(BorrowRequest borrowRequest) {
        Book book = searchService.searchTitle(borrowRequest.getTitle());

        return book.getAvailability();
    }

    public String bookOneBook(BorrowRequest borrowRequest) {
        Book book = searchService.searchTitle(borrowRequest.getTitle());
        String result = "Book not available";
        if (book.getAvailability()==true){
            book.setAvailability(false);
            paymentService.pay(book.getPrice());
            result = "Book borrowed";
        }

        return result;


    }
}
