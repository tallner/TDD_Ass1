package com.cte.services;

import com.cte.models.Book;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchService {


    public SearchService() {
    }

    public Book searchName(ArrayList<Book> books, String searchField){
    /*    System.out.println(books.contains(searchField));
        for (Book item: books) {
            if (item.getName().equals(searchField))
                return item;
        }
        return null;
*/

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book currentBook = iterator.next();
            if (currentBook.getName().equals(searchField)) {
                return currentBook;
            }
        }
        return null;
    }

    public Book searchGenre(ArrayList<Book> books, String searchField){
        for (Book item: books) {
            if (item.getGenre().equals(searchField))
                return item;
        }
        return new Book();
    }

    public Book searchWriter(ArrayList<Book> books, String searchField){
        for (Book item: books) {
            if (item.getWriter().equals(searchField))
                return item;
        }
        return new Book();
    }

    public Book searchPublicityDate(ArrayList<Book> books, String searchField){
        for (Book item: books) {
            if (item.getPublicityDate().equals(searchField))
                return item;
        }
        return new Book();
    }

    public Book searchRating(ArrayList<Book> books, String searchField){
        for (Book item: books) {
            if (item.getRating().equals(searchField))
                return item;
        }
        return new Book();
    }


}
