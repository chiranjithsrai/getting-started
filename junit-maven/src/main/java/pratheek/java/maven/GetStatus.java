/**
 * 
 */
package pratheek.java.maven;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author chiranjith
 *
 */
public class GetStatus extends LoadRecord {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String newFilePath = "src/java/resources/newEntry.json";
		String resultFilePath = "src/java/resources/record.json";

		checkForNewEntry(newFilePath);

		File existingRecord = readFile(resultFilePath);
		byte[] existing = getBytes(existingRecord);
		JSONArray existingRecordJsonObject = new JSONArray(new String(existing));

		List<JSONObject> jsonList = new LinkedList<JSONObject>();
		for (int i = 0; i < existingRecordJsonObject.length(); i++) {
			jsonList.add(existingRecordJsonObject.getJSONObject(i));
		}
		Collections.sort(jsonList, new MyJSONComparator());
		List<JSONObject> topRecords = getTopRecords(jsonList, 1);
		System.out.println("");
	}

	private static List<JSONObject> getTopRecords(List<JSONObject> jsonList, int i) {
		List<JSONObject> topRecords = new LinkedList<JSONObject>();
		for (int j = 0; j < i; j++) {
			if (j == jsonList.size()) {
				break;
			}
			topRecords.add(jsonList.get(j));
		}
		return topRecords;
	}

	/**
	 * @param newFilePath
	 * @throws IOException
	 */
	private static void checkForNewEntry(String newFilePath) throws IOException {
		File newEntry = readFile(newFilePath);
		byte[] newData = getBytes(newEntry);
		JSONObject newDataJsonObject = new JSONObject(new String(newData));

		if (newDataJsonObject.length() > 0) {
			System.out.println("There is new data to load... please check...");
			System.exit(0);
		}
	}

}

class MyJSONComparator implements Comparator<JSONObject> {

	public int compare(JSONObject arg0, JSONObject arg1) {
		String v1 = arg0.getString("date");
		String v2 = arg1.getString("date");
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(v1);
			date2 = df.parse(v2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date2.compareTo(date1);
	}

}
