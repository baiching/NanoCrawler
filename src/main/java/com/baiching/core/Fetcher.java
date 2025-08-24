package com.baiching.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Fetcher {
    public String fetch(String url) {
        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Connection connection = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                        .timeout(30000)
                        .ignoreHttpErrors(true) // Don't throw exception on HTTP errors
                        .followRedirects(true);

                Connection.Response response = connection.execute();
                Document doc = response.parse();
                if (response.statusCode() != 200) {
                    System.err.println("HTTP " + response.statusCode() + " for: " + url);

                    return doc.html();
                }
                else {
                    System.err.println("Failed to fetch "+ doc.connection().response().statusCode() + " for: " + url );
                    return null;
                }

                //return;
            } catch (IOException e) {
                System.err.println("Attempt " + attempt + " failed for '" + url + "': " + e.getMessage());
            }
        }
        return null;
    }
}
