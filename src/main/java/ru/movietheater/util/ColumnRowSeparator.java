package ru.movietheater.util;

import java.util.HashMap;
import java.util.Map;

public class ColumnRowSeparator {

    public static Map<String, String> getColRow(String colRow) {
        Map<String, String> colRowMap = new HashMap<>();
        String[] array = colRow.split("/");
        colRowMap.put("column", array[0]);
        colRowMap.put("row", array[1]);
        return colRowMap;
    }
}
