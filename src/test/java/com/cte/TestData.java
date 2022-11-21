package com.cte;

import com.cte.models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestData {
    ObjectMapper mapper = new ObjectMapper();
    String filePath = "src/main/resources/BookDatabase.json";


    public TestData() {
    }

    public List<Book> getTestData() throws IOException {

        return Arrays.asList(mapper.readValue(Paths.get(filePath).toFile(), Book[].class));

    }
}
