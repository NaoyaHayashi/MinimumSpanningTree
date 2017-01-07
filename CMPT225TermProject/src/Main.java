//----------------------------------------------------------------------------------
// CMPT 225 Term Project / 2014 Summer
// Group Name: NaoyaHayashi and ZhihaoZhou
// Group Members
// Name: Naoya Hayashi || computing ID: nhayashi@sfu.ca || Student No: 301233985
// Name: Zhihao Zhou   || Computing ID: zhihaoz@sfu.ca  || Student No: 301228885
//----------------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Type a file name that represents a graph (eg. graph1.txt,graph2.txt) and then, hit Enter: ");
		String fileName = input.nextLine();
		
		try{
			In inputStream = new In(fileName);
			EdgeWeightedGraph graph = new EdgeWeightedGraph(inputStream);
			PrimMST mst = new PrimMST(graph);
			
			System.out.println(graph.toString() + "\n");
			mst.printInfo();
			System.out.println();
			System.out.println(mst.MSTinDotFormat());
			
			drawGraph(fileName, graph, mst);
		}
		catch(Exception e){
			System.out.println("The file is corrupted or doesn't exist!!.");
			System.exit(1);
		}
		input.close();
		System.exit(0);
	}
	
	// fileName extension can be either 
	// 1: .txt (not dot format but the format in the textbook (eg. tinyGW.txt)) 
	// 2: .dot (contents must be in the dot format)
	public static void drawGraph(String fileName, EdgeWeightedGraph aGraph, PrimMST mst){
		// if it is not .dot file, it means the file should be .txt (the case 1 above)
		if(!(isDotFile(fileName))){
			convert(fileName, aGraph, mst);
		}
		
		String pathToDotCommand = "/usr/local/Cellar/Graphviz/2.40.1/bin/dot";
		String fileNameWithoutExtension = fileName.substring(0, fileName.length()-4);
		String dotFileName = fileNameWithoutExtension + ".dot";
		String pngFileName = fileNameWithoutExtension + ".png";
		String createCommand[] = {pathToDotCommand, "-Tpng", dotFileName, "-o", pngFileName};
		String openCommand[] = {"open", pngFileName};
		
		ProcessBuilder PB_createPNG = new ProcessBuilder(createCommand);
		ProcessBuilder PB_openPNG = new ProcessBuilder(openCommand);
		try{
			PB_createPNG.start();
		}
		catch(Exception e){
			System.out.println("CreatePNG: Process Error!!");
		}
		try{
			Thread.sleep(1000);
			PB_openPNG.start();
		}
		catch(Exception e){
			System.out.println("OpenPNG: Process Error!!");
		}
	}
	
	
	public static boolean isDotFile(String fileName){
		int lastIndex = fileName.length() - 1;
		String str = "";
		// getting the last 4 characters of fileName
		for(int i=0; i<4; i++){
			char ch = fileName.charAt(lastIndex);
			str = ch + str;
			lastIndex--;
		}
		
		if(str.equals(".dot")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	// This method does the following tasks
	// 1: crate a .txt file "in dot format" from the other .txt file "in the format specified in the textbook"
	// 2: convert the file into .dot
	public static void convert(String fileName, EdgeWeightedGraph aGraph, PrimMST mst){
		//String fileContents = aGraph.toString();
		String fileContents = mst.MSTinDotFormat();
		String newFileName = "Dot" + fileName;
		String fileNameWithoutExtension = fileName.substring(0, fileName.length()-4);
		String dotFileName = fileNameWithoutExtension + ".dot";
		// changing the extension of the file from .txt into .dot
		String command[] = {"mv", newFileName, dotFileName};
		
		File file = new File(newFileName);
		try{
			PrintWriter PW = new PrintWriter(file);
			PW.print(fileContents);
			PW.close();
		}
		catch(Exception e){
			System.out.println("Error! Can't write in the file!!");
			System.exit(1);
		}
		
		//Runtime r = Runtime.getRuntime();
		ProcessBuilder PB = new ProcessBuilder(command);
		try{
			//Process process = r.exec(command);
			PB.start();
		}
		catch(Exception e){
			System.out.println("Command Error! Can't process the terminal command!!");
			System.exit(1);
		}
	}
}