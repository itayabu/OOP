import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Dummy {
	static String itay = "ITAY";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//clean all white space for start
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String text = reader.readLine();
		text = text.trim();
		text = text.replaceAll("\\s+", " ");
		System.out.println(text);
	}
}
