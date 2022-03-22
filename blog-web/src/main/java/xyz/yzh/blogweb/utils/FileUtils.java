package xyz.yzh.blogweb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author simple
 */
public class FileUtils {
    public final static String separator = "/";

    public static String read(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
        String strLine = "";
        StringBuilder allLine = new StringBuilder();
        while ((strLine = br.readLine()) != null) {
            allLine.append(strLine).append("\n");

        }
        return allLine.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(FileUtils.read("C:\\Users\\simple\\Desktop\\docker-compose.yml"));
    }
}
