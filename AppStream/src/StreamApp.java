import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class StreamApp {

	public static void main(String[] args){
		
		final String ADD = "Http://ip-api.com/csv";
		String ipdata  = null;
		String data = null;

		File CountriesFile = new File("D:\\Countries.txt");
		File IpFile = new File("D:\\ip.txt");
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(CountriesFile);
		} catch (FileNotFoundException e2) {
			System.err.println("File not found!");
		}
		 
		Scanner scanner = null;
		try {
			scanner = new Scanner(IpFile);
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		}

	while (scanner.hasNextLine()) {
        	
			ipdata = scanner.nextLine();
			pw.print(ipdata + ": ");
			System.out.print(ipdata + ": ");
			
			URL u = null;
			try {
				u = new URL (ADD + "/" + ipdata);
			} catch (MalformedURLException e) {								
				System.err.println("Link Error!");
				e.printStackTrace();
			}
			
			Scanner in = null;
			try {
				in = new Scanner(u.openStream());
			} catch (IOException e) {
				System.err.println("I/O Error!");
				e.printStackTrace();
			}
			
			data = in.nextLine();
			
			
			int commas = 0;
			for (int index = 0; index < data.length(); index++) {
				if (data.charAt(index) == ',') {
					commas++;
				}
				if (commas == 1 && data.charAt(index) != ',') {
					pw.print(data.charAt(index));
					System.out.print(data.charAt(index));
			    }
			}
			System.out.println();
			pw.println();
			in.close();		
		}
		scanner.close();
		pw.close();
	}
}
