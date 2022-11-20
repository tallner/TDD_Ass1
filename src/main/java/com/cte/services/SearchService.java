package com.cte.services;

import com.cte.models.Book;

import java.util.Iterator;

public class SearchService {

    private Book bookNotFound;
    private DatabaseService databaseService;

    public SearchService(DatabaseService databaseService) {
        bookNotFound = new Book();
        bookNotFound.setTitle("Not found");

        this.databaseService = databaseService;
    }

    public Book searchTitle(String searchField){
    /*    System.out.println(books.contains(searchField));
        for (Book item: books) {
            if (item.getName().equals(searchField))
                return item;
        }
        return null;
*/

        Iterator<Book> iterator = databaseService.readBooksFromDatabase().iterator();
        while (iterator.hasNext()) {
            Book currentBook = iterator.next();
            if (currentBook.getTitle().equals(searchField)) {
                return currentBook;
            }
        }
        return bookNotFound;
    }

    public Book searchGenre(String searchField){
        for (Book item: databaseService.readBooksFromDatabase()) {
            if (item.getGenre().equals(searchField))
                return item;
        }
        return bookNotFound;
    }

    public Book searchWriter(String searchField){
        for (Book item: databaseService.readBooksFromDatabase()) {
            if (item.getWriter().equals(searchField))
                return item;
        }
        return bookNotFound;
    }

    public Book searchPublicityDate(String searchField){
        for (Book item: databaseService.readBooksFromDatabase()) {
            if (item.getPublicityDate().equals(searchField))
                return item;
        }
        return bookNotFound;
    }

    public Book searchRating(String searchField){
        for (Book item: databaseService.readBooksFromDatabase()) {
            if (item.getRating().equals(searchField))
                return item;
        }
        return bookNotFound;
    }


}
