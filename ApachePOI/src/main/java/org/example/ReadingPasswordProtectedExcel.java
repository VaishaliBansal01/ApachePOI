package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReadingPasswordProtectedExcel {

    public static void main(String[] args) throws FileNotFoundException
    {
        FileInputStream stream = new FileInputStream(".\\datafiles\\salary.xlsx");
        String password = "123";

//WorkbookFactory.create(stream, password)
    }
}
