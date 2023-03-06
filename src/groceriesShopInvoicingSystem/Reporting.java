package groceriesShopInvoicingSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
public class Reporting  implements Serializable{

	
	static void createProgramStatisticsReport(ProgramStatistics PS) throws IOException {
		try {
		

			File oldFile = new File( "ProgramStatisticsReport.txt");
			
			FileOutputStream ff = new FileOutputStream(oldFile);
			ObjectOutputStream o = new ObjectOutputStream(ff);

			// Write objects to file
			o.writeObject(PS);

			o.close();
			ff.close();
		} catch (Exception error) {
			error.getMessage();
		}

	}
	static void statics(String name) throws IOException {

		

		FileWriter w = new FileWriter("Statistics .txt");
		w.write("\t\tAllInvoiceReport");
		w.write(name);
		w.close();

	}

		static void createAllInvoiceReport(String name) throws IOException {

			
			FileWriter w = new FileWriter("AllInvoiceReport.txt");
			w.write("\t\tAllInvoiceReport");
			w.write(name);
			w.close();

		}
		
		static void createStatisticsReport(String name) throws IOException {

			
			FileWriter w = new FileWriter("Statistics .txt");
			w.write("\t\tStatistics\n\n");
			w.write(name);
			w.close();

		}
		static ProgramStatistics getProgramStatisticsReport() {
			ProgramStatistics in=null;
		
			try {
				FileInputStream fi = new FileInputStream(new File("ProgramStatisticsReport.txt"));
				ObjectInputStream oi = new ObjectInputStream(fi);

				// Read objects
				 in = (ProgramStatistics) oi.readObject();

			

				oi.close();
				fi.close();
			} catch (Exception error) {
				error.getMessage();
			}
	return in;
		}
}
