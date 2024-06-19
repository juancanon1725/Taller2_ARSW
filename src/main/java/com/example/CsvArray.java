package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvArray {
    static List<LinkedList<String>> records = new ArrayList<>();

    public static List<LinkedList<String>> getCsv(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(convertTo(line.split(",")));
            }
        }
        return organize(records);
    }

    private static LinkedList<String> convertTo(String[] values) {
        LinkedList<String> list = new LinkedList<>();
        for (String value : values) {
            list.add(value);
        }
        return list;
    }

    private static List<LinkedList<String>> organize(List<LinkedList<String>> data) {
        List<LinkedList<String>> organized = new ArrayList<>();
        int columns = data.get(0).size();
        for (int i = 0; i < columns; i++) {
            LinkedList<String> column = new LinkedList<>();
            for (LinkedList<String> row : data) {
                column.add(row.get(i));
            }
            organized.add(column);
        }
        return organized;
    }
}