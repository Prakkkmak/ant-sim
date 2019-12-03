package tools;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tools {

  public static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String readKeyboard() {
    String s = null;
    try {
      BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
      s = entree.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s;
  }

  // https://stackoverflow.com/questions/35692823/getting-a-color-by-name-in-java
  public static Color getColorByName(String name) {
    try {
      return (Color) Color.class.getField(name.toUpperCase()).get(null);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String[] tokenize(String s) {
    StringTokenizer nizer = new StringTokenizer(s);
    ArrayList<String> result = new ArrayList<String>();
    while (nizer.hasMoreElements()) {
      result.add((String) nizer.nextElement());
    }
    String[] arr = new String[result.size()];
    return result.toArray(arr);
  }

  public static String[] readCmdAndArgs() {
    String input = readKeyboard();
    String[] cmdAndargs = tokenize(input);
    return cmdAndargs;
  }
}
