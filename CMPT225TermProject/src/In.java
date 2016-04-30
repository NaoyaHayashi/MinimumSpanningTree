import java.util.Scanner;

public class In {
	private Scanner input;
	
	public In(String fileName) throws Exception{
		try{
			java.io.File file = new java.io.File(fileName);
			input = new Scanner(file);
		}
		catch(Exception e){
			throw new Exception("The file is either corrupted or doesn't exist!!");
		}
	}
	
	
	public int readNextInt(){
		try{
			if(input.hasNext()){
				String str = input.next();
				return Integer.parseInt(str);
			}
			// return -1 which never shows up for the graph representation if there is no more contents to read in the file.
			else{
				return -1;
			}
		}
		catch(Exception e){
			System.out.println("The file is corrupted!! The program ends.");
			System.exit(1);
			return 0;
		}
	}
	
	
	public double readNextDouble(){
		try{
			if(input.hasNext()){
				String str = input.next();
				return Double.parseDouble(str);
			}
			// return -1.0 which never shows up for the graph representation if there is no more contents to read in the file.
			else{
				return -1.0;
			}
		}
		catch(Exception e){
			System.out.println("The file is corrupted!! The program ends.");
			System.exit(1);
			return 0.0;
		}
	}
	
}
