import java.util.*;
import java.net.*;
import java.io.*;

public class Task4 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Base currency (e.g. USD): ");
        String base = sc.next().toUpperCase();

        System.out.print("Target currency (e.g. INR): ");
        String target = sc.next().toUpperCase();

        System.out.print("Amount to convert: ");
        double amount = sc.nextDouble();

        // Getting data from API
        String path = "https://api.exchangerate-api.com/v4/latest/" + base;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            System.out.println("Could not get rates.");
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();

        String raw = sb.toString();
        String key = "\"" + target + "\":";
        if (!raw.contains(key)) {
            System.out.println("Currency not found.");
            return;
        }

        int start = raw.indexOf(key) + key.length();
        int end = raw.indexOf(",", start);
        if (end == -1)
            end = raw.indexOf("}", start);

        double rate = Double.parseDouble(raw.substring(start, end));
        double converted = amount * rate;

        System.out.println("\nResult: " + converted + " " + target);
        System.out.println("Rate: 1 " + base + " = " + rate + " " + target);

        sc.close();
    }
}
