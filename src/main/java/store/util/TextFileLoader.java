package store.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileLoader {
    private static final String productsPath = "src/main/resources/products.md";

    public static List<String> productsLoader() throws IOException {
        return loader(productsPath);
    }

    private static List<String> loader(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String str = reader.readLine();
        List<String> textLines = new ArrayList<>();
        while ((str = reader.readLine()) != null) {
            textLines.add(str);
        }
        reader.close();
        return textLines;
    }
}
