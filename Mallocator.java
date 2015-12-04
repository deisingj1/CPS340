import java.io.*;
import java.util.*;

public class Mallocator {
	static final String INPUT_FILE = "Minput.data";
	public static LinkedList<String[]> parseString() throws IOException{
	    LinkedList<String> inputContents = FileIO.ReadInputFile(INPUT_FILE);
	    LinkedList<String[]> contents = new LinkedList<String[]>();
	    String[] lC;
	    while(!inputContents.isEmpty()) {
	        lC = inputContents.poll().split(" ");
	        contents.add(lC);
	    }
	    return contents;
	}
	public static LinkedList<Memory> loadMemory(LinkedList<String[]> list){
		LinkedList<Memory> memories = new LinkedList<Memory>();
		String[] item;
		int size = Integer.parseInt(list.poll()[0]);
	    for(int i=0; i < size; i++) {
			item = list.poll();
			memories.add(new Memory(Integer.parseInt(item[0]), Integer.parseInt(item[1])));
		}
		return memories;
	}
	public static LinkedList<Process> loadProcesses(LinkedList<String[]> list){
		LinkedList<Process> processes = new LinkedList<Process>();
		String[] item;
		int size = Integer.parseInt(list.poll()[0]);
	    for(int i=0; i < size; i++) {
			item = list.poll();
			processes.add(new Process(Integer.parseInt(item[0]), Integer.parseInt(item[1])));
		}
		return processes;
	}
	public static void doFirstFit(LinkedList<Process> pList, LinkedList<Memory> mList) {
		LinkedList<String> output = new LinkedList<String>();
		for(Process p : pList) {
			for(Memory m : mList) {
				if( m.getSize() > p.getSize()) {
					p.setStart(m.getStart());
					p.setEnd(m.getStart() + p.getSize());
					m.setSize(p.getSize());
					output.add("" + p.getStart() + " " + p.getEnd() + " " + p.getpID());
					break;
				}
			}
		}
		try {
			FileIO.WriteListToFile(output, "FFoutput.data");
		}
		catch(IOException e) {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
	    LinkedList<Process> proc = null;
	    LinkedList<Memory> mem = null;
	    try {
	    	LinkedList<String[]> fContents = parseString();
			mem = loadMemory(fContents);
			proc = loadProcesses(fContents);
	    }
	    catch(IOException e) {
	        System.out.println("File not found");
	        System.exit(1);
	    }
	    System.out.println(proc.poll().getSize());
	    doFirstFit(new LinkedList<Process>(proc), new LinkedList<Memory>(mem));
	}
	
}

class Process {
	private int pID, size, start, end;
	public Process(int ppID, int pSize) {
		size = pSize;
		pID = ppID;
		start = -1;
		end = -1;
	}
	
	public int getSize() {
		return size;
	}

	public int getpID() {
		return pID;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public void setStart(int s) {
		start = s;
	}
	public void setEnd(int e) {
		end = e;
	}
}
class Memory {
	private int start, end, size, free; 
	public Memory(int mStart, int mEnd) {
		start = mStart; 
		end = mEnd;
		size = mEnd - mStart;
		free = size;
	}	
	public int getSize() {
		return size;
	}
	public int getStart(){
		return start;
	}
	public void setSize(int nSize) {
		size = nSize;
	}
}