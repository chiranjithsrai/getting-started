/**
 * 
 */
package pratheek.java.maven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author chiranjithsrai
 *
 */
public class LoadRecord {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String emptyJson = "{}";
		String newFilePath = "src/java/resources/newEntry.json";
		String resultFilePath = "src/java/resources/record.json";

		File newEntry = readFile(newFilePath);
		byte[] newData = getBytes(newEntry);
		JSONObject newDataJsonObject = new JSONObject(new String(newData));
		if (newDataJsonObject.length() == 0) {
			System.out.println("no data");
			System.exit(0);
		}
		writeToFile(newFilePath, emptyJson);

		System.out.println("new entry :: " + newDataJsonObject);

		File existingRecord = readFile(resultFilePath);
		byte[] existing = getBytes(existingRecord);
		JSONArray existingRecordJsonObject = new JSONArray(new String(existing));
		existingRecordJsonObject.put(newDataJsonObject);
		writeToFile(resultFilePath, existingRecordJsonObject.toString());
		System.out.println("recorded");
	}

	/**
	 * @param emptyJson
	 * @param newFilePath
	 * @throws IOException
	 */
	private static void writeToFile(String newFilePath, String emptyJson) throws IOException {
		FileWriter fw = new FileWriter(newFilePath);
		fw.write(emptyJson);
		fw.close();
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	protected static byte[] getBytes(File file) throws IOException {
		byte[] data = Files.readAllBytes(file.toPath());
		return data;
	}

	/**
	 * @return
	 */
	protected static File readFile(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("file not found");
			System.exit(0);
		}
		return file;
	}

}
