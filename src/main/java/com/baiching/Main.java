package com.baiching;

import com.baiching.core.Fetcher;

public class Main {
    public static void main(String[] args) {
        new Fetcher().fetch("https://github.com/search?q=search+engine+language%3AJava&type=repositories&l=Java");
    }
}