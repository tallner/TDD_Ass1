package com.cte;

import com.cte.models.Book;
import com.cte.services.DatabaseService;
import com.cte.services.DatabaseService;
import com.cte.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SearchTests {

    private SearchService searchService;
    private DatabaseService databaseService;
    private ArrayList<Book> bookArrayList;

    @BeforeEach
    public void  setUp() {


        bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("title1","Book genre","Book writer","Book publicity date","Book rating",4,true));
        bookArrayList.add(new Book("title2","Drama","Joe2","2020-02-19","2",55,true));
        bookArrayList.add(new Book("title3","Drama","Joe3","2020-03-19","3",34,true));
        bookArrayList.add(new Book("title4","Drama","Joe4","2020-04-19","4",12,true));




        databaseService = mock(DatabaseService.class);
        when(databaseService.readBooksFromDatabase()).thenReturn(bookArrayList);

        searchService = new SearchService(databaseService);

    }

    @Test
    @DisplayName("Search using title")
    public void searchForBookUsingTitle() {

        String searchField = "title1";
        String expected = "title1";
        String actual = searchService.searchTitle(searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using genre")
    public void searchForBookUsingGenre(){

        String searchField = "Book genre";
        String expected = "title1";
        String actual = searchService.searchGenre(searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using writer")
    public void searchForBookUsingWriter(){

        String searchField = "Book writer";
        String expected = "title1";
        String actual = searchService.searchWriter(searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using publicity date")
    public void searchForBookUsingPublicityDate(){

        String searchField = "Book publicity date";
        String expected = "title1";
        String actual = searchService.searchPublicityDate(searchField).getTitle();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("Search using rating")
    public void searchForBookUsingRating(){

        String searchField = "Book rating";
        String expected = "title1";
        String actual = searchService.searchRating(searchField).getTitle();

        assertEquals(expected, actual);

    }


}
