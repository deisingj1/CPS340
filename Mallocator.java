import java.io.*;
import java.util.*;

public class Mallocator {
	static final String INPUT_FILE = "Minput.data";
	static int numOfProcesses; 
	public static LinkedList<Process> loadProcesses() throws IOException{
	    LinkedList<String> inputContents = FileIO.ReadInputFile(INPUT_FILE);
	    numOfProcesses = Integer.parseInt(inputContents.poll());
	    System.out.println(numOfProcesses);
	    LinkedList<Process> processes = new LinkedList<Process>();
	    String[] lC;
	    while(!inputContents.isEmpty()) {
	        lC = inputContents.poll().split(" ");
	        System.out.println(lC[0]);
	        processes.add(new Process(Integer.parseInt(lC[0]), Integer.parseInt(lC[1])));
	    }
	    return processes;
	}
	
	public static void main(String[] args) {
	    LinkedList<Process> proc = null;
	    try {
	        proc = loadProcesses();
	    }
	    catch(IOException e) {
	        System.out.println("File not found");
	        System.exit(1);
	    }
	    System.out.println(proc.poll().getSize());
	}
	
}

class Process {
	private int pID, size;
	public Process(int ppID, int pSize) {
		size = pSize;
		pID = ppID;
	}
	
	public int getSize() {
		return size;
	}

	public int getpID() {
		return pID;
	}
}