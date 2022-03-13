package xyz.yzh.blogweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author simple
 */
public class FileReadUtils {
    private static final ClassLoader loader = FileReadUtils.class.getClassLoader();

    public static String read(String filePath) throws IOException {
        InputStreamReader fr = new InputStreamReader(Objects.requireNonNull(loader.getResourceAsStream(filePath)));
        BufferedReader br = new BufferedReader(fr);
        String strLine = "";
        StringBuilder allLine = new StringBuilder();
        while ((strLine = br.readLine()) != null) {
            allLine.append(strLine).append("\n");

        }
        return allLine.toString();
    }

    public static String readtoOneLine(String filePath) throws IOException {
        InputStreamReader fr = new InputStreamReader(Objects.requireNonNull(loader.getResourceAsStream(filePath)));
        BufferedReader br = new BufferedReader(fr);
        String strLine = "";
        StringBuilder allLine = new StringBuilder();
        while ((strLine = br.readLine()) != null) {
            allLine.append(strLine);
        }
        return allLine.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println( MarkDown2HtmlUtils.markdown2Html(FileReadUtils.read("static/锁.md")));
        System.out.println( FileReadUtils.read("static/锁.md"));
    }
}
