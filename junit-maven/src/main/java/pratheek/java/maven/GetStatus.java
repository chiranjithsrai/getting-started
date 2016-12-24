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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author chiranjith
 *
 */
public class GetStatus extends LoadRecord {

	private static int MAX_NUMBER = 70;
	private static int TOP = 5;

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
		List<Map<String, Object>> topRecords = getTopRecords(jsonList, TOP);
		Map<String, Object> statusObject = getStatus(topRecords);
		System.out.println("***************STATUS**********************");
		System.out.println("Total available numbers :: ");
		for (int i = 1; i <= MAX_NUMBER; i++) {
			System.out.print(i);
			System.out.print(", ");
		}
		System.out.println("\n");
		System.out.println("\nStatus of top " + TOP + " records \n");

		System.out.println("Repeated numbers and count");
		Map<String, Object> repeatedNumbers = (Map<String, Object>) statusObject.get("repeatedNumbers");
		Set<String> keySet = repeatedNumbers.keySet();
		System.out.println("Number    Count");
		for (String key : keySet) {
			System.out.println(key + "      :     " + repeatedNumbers.get(key));
		}

		System.out.println("\nNumbers have not been called yet");
		List<Integer> nonRepeatedNumbers = (List<Integer>) statusObject.get("nonRepeatedNumbers");
		for (Integer num : nonRepeatedNumbers) {
			System.out.print(num);
			System.out.print(",");
		}
		System.out.println("");
		System.out.println("*******************************************");
	}

	private static Map<String, Object> getStatus(List<Map<String, Object>> topRecords) {
		Map<String, Object> statusObject = new HashMap<String, Object>();
		statusObject.put("repeatedNumbers", new HashMap<String, Object>());

		Map<String, Object> repeatedNumbers = (Map<String, Object>) statusObject.get("repeatedNumbers");
		for (Map<String, Object> record : topRecords) {
			List<Integer> values = (List<Integer>) record.get("values");
			for (Integer value : values) {
				if (repeatedNumbers.containsKey(value.toString())) {
					int numbers = (Integer) repeatedNumbers.get(value.toString());
					repeatedNumbers.put(value.toString(), ++numbers);
				} else {
					repeatedNumbers.put(value.toString(), 1);
				}
			}
		}
		List<Integer> superSet = new LinkedList<Integer>();
		for (int i = 1; i <= MAX_NUMBER; i++) {
			if (!repeatedNumbers.containsKey(i + ""))
				superSet.add(i);
		}
		statusObject.put("nonRepeatedNumbers", superSet);
		return statusObject;
	}

	private static List<Map<String, Object>> getTopRecords(List<JSONObject> jsonList, int i) {
		List<Map<String, Object>> topRecords = new LinkedList<Map<String, Object>>();
		for (int j = 0; j < i; j++) {
			if (j == jsonList.size()) {
				break;
			}
			Map<String, Object> newMap = new HashMap<String, Object>();
			newMap.put("date", jsonList.get(j).get("date"));
			List<Integer> tempList = new LinkedList<Integer>();
			JSONArray array = (JSONArray) jsonList.get(j).get("values");
			for (int k = 0; k < array.length(); k++)
				tempList.add((Integer) array.get(k));
			newMap.put("values", tempList);
			topRecords.add(newMap);
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
