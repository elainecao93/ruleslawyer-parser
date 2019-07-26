import java.io.*;
import java.net.URL;

public class FileParser {

    public static void main(String[] args) {

        /*String CR = parseCR("/CR.txt");
        System.out.println(CR);*/

        String JAR = parse("/IPG.txt");
        System.out.println(JAR);

    }

    private static char[] getData(String filename) {

        InputStream in = FileParser.class.getResourceAsStream(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(filename);
            System.exit(1);
        }
        char[] buffer = new char[1000000];
        try {
            br.read(buffer);
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println(filename);
            System.exit(1);
        }

        return buffer;
    }

    private static String parseCR(String filename) {

        StringBuilder output = new StringBuilder();
        String[] inputArray = new String(getData(filename)).split("\\r?\\n\\r?\\n");
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].length() > 1) {
                String elem = inputArray[i];
                int breakIndex = elem.indexOf(" ");
                String citation;
                String ruleText;
                try {
                    citation = elem.substring(0, breakIndex).trim();
                    ruleText = elem.substring(breakIndex + 1).trim();
                    ruleText = ruleText.replace("\r\n", "**");
                    output.append(citation + "&&" + ruleText + "\n");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("error: " + elem.length());
                    System.out.println(elem);
                }
            }
        }

        return output.toString();
    }

    private static String parse(String filename) {

        StringBuilder output = new StringBuilder();
        String[] inputArray = new String(getData(filename)).split("\\r?\\n");

        for (int i=0; i<inputArray.length; i++) {
            if (inputArray[i].length() > 3) {
                output.append(inputArray[i].length() + " " + inputArray[i] + "\n");
            } else if (inputArray[i].length() == 0){
                output.append("\n");
            }
        }

        return output.toString();
    }
}
