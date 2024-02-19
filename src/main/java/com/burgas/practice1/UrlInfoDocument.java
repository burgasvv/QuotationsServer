package com.burgas.practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.System.*;

public class UrlInfoDocument {

    private static final String URL_NAME = "https://www.library.ru/";

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new URI(URL_NAME).toURL().openStream()))) {

            URL url = new URI(URL_NAME).toURL();
            out.println(url.getProtocol());
            out.println(url.getHost());

            reader.lines().forEach(out::println);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
