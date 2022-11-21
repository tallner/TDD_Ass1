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

    public void bookOneBook(BorrowRequest borrowRequest) {
        Book book = searchService.searchTitle(borrowRequest.getTitle());
        if (book.getAvailability()==true){
            book.setAvailability(false);
            paymentService.pay(book.getPrice());
            System.out.println("book borrowed");
        }
        else {
            System.out.println("not available");
        }


    }
}
