package com.cte;

import com.cte.models.Book;
import com.cte.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SearchTests {

    private SearchService searchService;
    private BookDatabase bookDatabase;
    private ArrayList<Book> bookArrayList;

    @BeforeEach
    public void  setUp() {
        searchService = new SearchService();

        bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("title1","Book name 01","Book genre","Book writer","Book publicity date","Book rating",true));
        bookArrayList.add(new Book("title2","Book name 11","Drama","Joe2","2020-02-19","2",true));
        bookArrayList.add(new Book("title3","Book name 21","Drama","Joe3","2020-03-19","3",true));
        bookArrayList.add(new Book("title4","Book name 31","Drama","Joe4","2020-04-19","4",true));

        bookDatabase = mock(BookDatabase.class);
        when(bookDatabase.readBooksFromDatabase()).thenReturn(bookArrayList);

    }

    @Test
    @DisplayName("Search using name")
    public void searchForBookUsingName() {

        String searchField = "Book name 01";
        String expected = "title1";
        String actual = searchService.searchName(bookArrayList,searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using genre")
    public void searchForBookUsingGenre(){

        String searchField = "Book genre";
        String expected = "title1";
        String actual = searchService.searchGenre(bookArrayList,searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using writer")
    public void searchForBookUsingWriter(){

        String searchField = "Book writer";
        String expected = "title1";
        String actual = searchService.searchWriter(bookArrayList,searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using publicity date")
    public void searchForBookUsingPublicityDate(){

        String searchField = "Book publicity date";
        String expected = "title1";
        String actual = searchService.searchPublicityDate(bookArrayList,searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using rating")
    public void searchForBookUsingRating(){

        String searchField = "Book rating";
        String expected = "title1";
        String actual = searchService.searchRating(bookArrayList,searchField).getTitle();

        assertEquals(expected, actual);

    }


}
