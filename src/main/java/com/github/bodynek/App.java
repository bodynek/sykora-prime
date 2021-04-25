package com.github.bodynek;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigInteger;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        InputStream file = System.in;
        try {
            if (args.length != 0) {
                file = new FileInputStream(args[0]);
            }
        } catch (FileNotFoundException e) {
            System.err.printf("File %s is not found: \n", args[0]);
            System.exit(1);
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            System.err.printf("Reading of %s failed\n", file);
            System.exit(1);
        }
        if (workbook.getNumberOfSheets() < 1) {
            System.err.println("Workbook doesn't contain first sheet");
            System.exit(1);
        }
        Sheet sheet = workbook.getSheetAt(0);

        BigInteger max = BigInteger.ZERO;
        // first scan for finding the greatest number for sieve preparation
        for (Row row : sheet) {
            Cell cell = row.getCell(1);
            if (cell != null) {
                String s = cell.getStringCellValue();
                try {
                    BigInteger number = new BigInteger(s);
                    max = max.max(number);
                } catch (NumberFormatException ignored) {
                }
            }
        }

        Sieve sieve = new Sieve(max);
        // second scan for filtering the primes
        for (Row row : sheet) {
            Cell cell = row.getCell(1);
            if (cell != null) {
                String s = cell.getStringCellValue();
                try {
                    BigInteger number = new BigInteger(s);
                    if (sieve.isPrime(number)) {
                        System.out.println(number);
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }
}
