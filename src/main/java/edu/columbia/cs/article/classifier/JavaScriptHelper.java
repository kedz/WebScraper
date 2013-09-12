package edu.columbia.cs.article.classifier;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaScriptHelper {

    private static JavaScriptHelper JS_HELPER;

    private final String jQueryScript;
    private String menuScript = null;
    private String highlightScript = null;

    private JavaScriptHelper() {

        jQueryScript = loadScript(JavaScriptHelper.class.getClassLoader().getResourceAsStream("jquery-1.10.2.min.js"));





    }

    public static JavaScriptHelper getInstance() {
        if (JS_HELPER == null)
            JS_HELPER = new JavaScriptHelper();
        return JS_HELPER;
    }

    public String getjQueryScript() {return jQueryScript;}
    public String getHighlightTextScript() {
        if (highlightScript == null) {
            highlightScript = loadScript(JavaScriptHelper.class.getClassLoader().getResourceAsStream("highlight-text.js"));
        }

        return highlightScript;
    }

    public String getMenuScript() {
        if (menuScript == null) {
            menuScript = loadScript(JavaScriptHelper.class.getClassLoader().getResourceAsStream("menu.js"));
        }

        return menuScript;

    }

    private String loadScript(InputStream inputStream) {
        StringBuilder buffer = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(inputStream);
        try {
            while (scanner.hasNextLine()){
                buffer.append(scanner.nextLine() + NL);
            }
        }
        finally {
            scanner.close();
        }

        return buffer.toString();
    }

}
