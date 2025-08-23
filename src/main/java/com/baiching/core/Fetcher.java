package com.baiching.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Fetcher {
    public void fetch(String url) {
        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Connection connection = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                        .timeout(30000)
                        .ignoreHttpErrors(true) // Don't throw exception on HTTP errors
                        .followRedirects(true);

                //Document docs = Jsoup.connect(url).get();
                Connection.Response response = connection.execute();
                if (response.statusCode() != 200) {
                    System.err.println("HTTP " + response.statusCode() + " for: " + url);
                    return; // Or handle differently
                }

                Document doc = response.parse();
                System.out.println("Successfully fetched: " + url);
                System.out.println("Title: " + doc.title());
                return;
            } catch (IOException e) {
                System.err.println("Attempt " + attempt + " failed for '" + url + "': " + e.getMessage());

                if (attempt == maxRetries) {
                    System.err.println("All attempts failed for: " + url);
                    return;
                }

                // Wait before retrying
                try {
                    Thread.sleep(2000 * attempt);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
