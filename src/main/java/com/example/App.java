package com.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class App {
    static List<Path> allFiles = new ArrayList<>();
    static List<String> files = new ArrayList<>();
    static Calculator deviation;
    static Calculator mean;
    static List<LinkedList<String>> data;
    static List<LinkedList<String>> resp = new ArrayList<>();

    private App() {}

    public static void main(String[] args) throws Exception {
        prepareFiles();
        selectFiles(args[0]);
        generateLambdas();
        generateResponse();
    }

    private static void prepareFiles() throws IOException {
        Path rootPath = Paths.get(System.getProperty("user.dir"));
        allFiles = Lister.listAllFiles(rootPath, allFiles);
    }

    private static void selectFiles(String filename) throws Exception {
        for (Path path : allFiles) {
            if (path.getFileName().toString().equals(filename)) {
                files.add(path.toString());
            }
        }
        if (files.size() != 1) throw new Exception("Invalid number of files with the given name");
        data = CsvArray.getCsv(files.get(0));
    }

    private static void generateLambdas() {
        mean = data -> {
            double sum = 0.0;
            for (int i = 0; i < data.size(); i++) {
                sum += data.get(i);
            }
            return Double.toString(sum / data.size());
        };

        deviation = data -> {
            double sum = 0.0;
            for (int i = 0; i < data.size(); i++) {
                sum += data.get(i);
            }
            double mean = sum / data.size();
            double sumOfSquares = 0.0;
            for (int i = 0; i < data.size(); i++) {
                sumOfSquares += Math.pow(data.get(i) - mean, 2);
            }
            return Double.toString(Math.sqrt(sumOfSquares / (data.size() - 1)));
        };
    }

    private static void generateResponse() {
        List<LinkedList<Double>> convertedData = convertData();
        LinkedList<String> header = new LinkedList<>();
        header.add("Mean");
        header.add("Standard Deviation");
        for (LinkedList<Double> column : convertedData) {
            LinkedList<String> results = new LinkedList<>();
            results.add(mean.operation(column));
            results.add(deviation.operation(column));
            resp.add(header);
            resp.add(results);
        }
        printResults();
    }

    private static List<LinkedList<Double>> convertData() {
        List<LinkedList<Double>> converted = new ArrayList<>();
        for (LinkedList<String> column : data) {
            LinkedList<Double> numericColumn = new LinkedList<>();
            for (int i = 1; i < column.size(); i++) {
                try {
                    numericColumn.add(Double.parseDouble(column.get(i)));
                } catch (NumberFormatException ignored) {}
            }
            converted.add(numericColumn);
        }
        return converted;
    }

    private static void printResults() {
        int index = 0;
        for (LinkedList<String> result : resp) {
            if (index % 2 == 0) {
                System.out.println("\n" + data.get(index / 2).get(0));
            }
            result.printList();
            index++;
        }
    }
}