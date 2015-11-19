import java.io.*;
import java.util.*;

public class FileIO {
	public static LinkedList<String> ReadInputFile(String fileName) throws IOException {
		FileReader file = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(file);
		LinkedList<String> pList = new LinkedList<String>();
		String currentLine;
		while((currentLine = reader.readLine()) != null) {
			pList.add(currentLine);
		}
		reader.close();
		file.close();
		return pList;
	}
	public static void WriteListToFile(Queue<String> list, String name) throws IOException {
		FileWriter file = new FileWriter(name);
		while(!list.isEmpty()){
			file.write(list.poll());
		}
		file.close();
	}
}