import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class Project2 {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Wrong number of arguments.\nUsage: java Project2 dataset companyToFind");
			return;
		}

		String csvFilepath = args[0]

		File dataFile = new File(csvFilepath);
		String companyToFind = args[1];

		long start, end, duration;
		Company searchResult = null;

		start = System.currentTimeMillis();
		searchResult = Project2.findLinearSearch(companyToFind, dataFile);
		end = System.currentTimeMillis();
		duration = end - start;

		System.out.println("\n*********************\nLinear Search\n*********************\n");
		System.out.println("Search Time: " + duration + "ms");
		if (searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");

		System.out.println("\n*********************\nMy Hash: Separate Chaining\n*********************\n");
		start = System.currentTimeMillis();
		MapSeparateChaining<String, Company> mapOpenHash = Project2.makeMapSeparateChaining(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");

		start = System.currentTimeMillis();
		searchResult = mapOpenHash.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;

		System.out.println("Search Time: " + duration + "ms");
		if (searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");

		mapOpenHash = null; // release reference.
		System.out.println("\n*********************\nMy Hash: Open Addressing\n*********************\n");
		start = System.currentTimeMillis();
		MapOpenAddressing<String, Company> mapOpenAddress = Project2.makeMapOpenAddressing(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");

		start = System.currentTimeMillis();
		searchResult = mapOpenAddress.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;

		System.out.println("Search Time: " + duration + "ms");
		if (searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");

		mapOpenAddress = null; // release reference.
		System.out.println("\n*********************\nJava HashMap\n*********************\n");
		start = System.currentTimeMillis();
		HashMap<String, Company> map = Project2.javaHash(dataFile);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("Map Creation Time: " + duration + "ms");

		start = System.currentTimeMillis();
		searchResult = map.get(companyToFind);
		end = System.currentTimeMillis();
		duration = end - start;

		System.out.println("Search Time: " + duration + "ms");
		if (searchResult != null)
			System.out.println("Complaints: " + String.join(", ", searchResult.getIds()));
		else
			System.out.println("No Complaints found.");

		System.out.println("\n*********************\n");
	}

	public static Company findLinearSearch(String needle, File dataFile) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(dataFile));
		String[] nextLine;
		Company company = null;
		while ((nextLine = reader.readNext()) != null) {
			String companyName = nextLine[7];
			String complaintId = nextLine[17];

			if (needle.equals(companyName)) {
				if (company == null) {
					company = new Company(needle, complaintId);
				} else {
					company.addId(complaintId);
				}
			}
		}
		reader.close();
		return company;
	}

	public static MapSeparateChaining<String, Company> makeMapSeparateChaining(File dataFile) throws Exception {
		MapSeparateChaining<String, Company> result = new MapSeparateChaining<>(100000);
		CSVReader reader = new CSVReader(new FileReader(dataFile));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			String companyName = nextLine[7];
			String complaintId = nextLine[17];
			if (result.containsKey(companyName)) {
				result.get(companyName).addId(complaintId);
			} else {
				result.put(companyName, new Company(companyName, complaintId));
			}
		}
		reader.close();
		return result;
	}

	public static MapOpenAddressing<String, Company> makeMapOpenAddressing(File dataFile) throws Exception {
		MapOpenAddressing<String, Company> result = new MapOpenAddressing<>(100000);
		CSVReader reader = new CSVReader(new FileReader(dataFile));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			String companyName = nextLine[7];
			String complaintId = nextLine[17];
			if (result.containsKey(companyName)) {
				result.get(companyName).addId(complaintId);
			} else {
				result.put(companyName, new Company(companyName, complaintId));
			}
		}
		reader.close();
		return result;
	}

	public static HashMap<String, Company> javaHash(File dataFile) throws Exception {
		HashMap<String, Company> result = new HashMap<>();
		CSVReader reader = new CSVReader(new FileReader(dataFile));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			String companyName = nextLine[7];
			String complaintId = nextLine[17];
			if (result.containsKey(companyName)) {
				result.get(companyName).addId(complaintId);
			} else {
				result.put(companyName, new Company(companyName, complaintId));
			}
		}
		reader.close();
		return result;
	}
}