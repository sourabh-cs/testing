package sour.test.jetty.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Greeter {
    public String getGreeting() throws Exception {
        InputStream greetingStr = getClass().getResourceAsStream("/greeting.txt");
        try {
			BufferedReader br = new BufferedReader(new InputStreamReader(greetingStr));
			return br.readLine();
        }
        finally {
            greetingStr.close();
        }
    }
}
