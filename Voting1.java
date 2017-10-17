	import java.util.*;
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;

	public class Voting1 {
  
	  public static void main(String[] args)
	  {
	    char[] R1 = {'A', 'B', 'C', 'D'};
	    char[] R2 = {'G', 'A', 'D', 'B'};
	    
	    // to store the Region wise votes
	    Map<String, String> Region1 = new HashMap<>();
	    Map<String, String> Region2 = new HashMap<>();
	    
	    //to store the Region wise scores
	    Map<String, String> scores1 = new HashMap<>();
	    Map<String, String> scores2 = new HashMap<>();
	    
	    try {
	        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Shreshta\\eclipse-workspace\\Voting1\\src\\voting.dat"));
	        String line;

	        while((line = in.readLine()) != null) {
	           
	            
	            //checking for "/" and storing list of contestants contesting from region 1 in an array
	            if(line.contains("/") && line.contains("R1")) {
	                String contestantLine [] = line.split("/");
	                R1 = contestantLine[1].toCharArray();                
	            }
	            
	            //checking for "/" and storing list of contestants contesting from region 2 in an array
	            if(line.contains("/") && line.contains("R2")) {
	                String contestantLine [] = line.split("/");
	                R2 = contestantLine[1].toCharArray();                
	            }
	            
	            //storing information regarding votes from region 1 in a map 
	            while(!line.contains("/") && line.contains("R1")) {
	                String nextLine = in.readLine();
	                if(!nextLine.contains("R2")) {
	                
	                   char[] split = nextLine.toCharArray();
	            
	                   
	                   if(split.length==5) {
	                       Region1.put(Character.toString(split[0]), split[2]+""+split[4]);
	                   }
	                   
	                   if(split.length==7) {
	                       Region1.put(Character.toString(split[0]),split[2]+""+split[4]+""+split[6]);
	                   }
	                }                
	                else
	                    break;
	            }
	            
	            //storing information regarding votes from region 2 in a map 
	            while(!line.contains("/") && line.contains("G")) {                
	                //char[] splitLine = line.toCharArray();
	                
	                String nextLine = in.readLine();
	                if(!nextLine.contains("&&")) {
	                    
	                    char[] splitNextLine = nextLine.toCharArray();
	                    if(splitNextLine.length == 7)
	                        Region2.put(Character.toString(splitNextLine[0]), splitNextLine[2]+""+splitNextLine[4]+""+splitNextLine[6]);
	                    
	                }               
	                else
	                    break;
	            }      
		}
	        in.close();
	        }catch (IOException e) {
	            e.printStackTrace();
		}
	    
	    int flag=0; //to check if the vote is valid or not.. 0=invalid;
	    int finalScore;
	    int previous;
	    String value;
	    int length = 0;
	        
	    for(Map.Entry<String,String> entry:Region1.entrySet()) {
	      // String id = entry.getKey(); //test purpose
	      value = entry.getValue();  
	      
	      length = length + value.length(); //to calculate the total number of candidates that recieved votes from voters
	      
	      //Second condition
	      if(value.length() == 0 || value.length() > 3) {
	        System.out.println("invalid vote");
	        break;
	      }
	      
	      //Third Condition
	      for(int i=0; i<value.length(); i++) {
	        for(int j=0; j<R1.length; j++) {
	          if(value.charAt(i) == R1[j]) {
	            //System.out.println(value.charAt(i) + "valid with" +R1[j] + " moving to next.."); //test purpose            
	            ++flag;            
	            int score = 3-i;      
	            
	            //calculating and storing scores in a map
	            if(scores1.containsKey(Character.toString(value.charAt(i)))) {              
	                previous = Integer.parseInt(scores1.get(Character.toString(value.charAt(i))));
	                finalScore = previous + score;
	                scores1.put(Character.toString(value.charAt(i)), Integer.toString(finalScore));              

	              
	            }
	            
	            else {
	              scores1.put(Character.toString(value.charAt(i)), Integer.toString(score));
	              // System.out.println("Score of " +value.charAt(i) + "is "+score); //test purpose
	            }
	            break;
	          }
	        }
	      }
	    }
	    
	    for(Map.Entry<String,String> entry:Region2.entrySet()) {
	      String id = entry.getKey(); //test purpose
	      value = entry.getValue();  //test purpose
	     
	      
	      length = length + value.length(); //to calculate the total number of candidates that recieved votes from voters
	      
	      //Second condition
	      if(value.length() == 0 || value.length() > 3) {
	        System.out.println("invalid vote");
	        break;
	      }
	      
	      //Third Condition
	      for(int i=0; i<value.length(); i++) {
	        for(int j=0; j<R2.length; j++) {
	          if(value.charAt(i) == R2[j]) {            
	            ++flag;            
	            int score = 3-i;
	            if(scores2.containsKey(Character.toString(value.charAt(i)))) {
	              
	                previous = Integer.parseInt(scores2.get(Character.toString(value.charAt(i))));
	                finalScore = previous + score;
	                scores2.put(Character.toString(value.charAt(i)), Integer.toString(finalScore));              
	            }
	            
	            else {
	              scores2.put(Character.toString(value.charAt(i)), Integer.toString(score));
	            }
	            break;
	          }
	        }
	      }
	    }
	    
	    if(flag != 0 && (flag+2) == length) {
	      // System.out.println("checked.. all set"); //test purpose
	      
	      System.out.println("Region 1 Scores: ");
	      // printing scores from Region 1
	      for(Map.Entry<String,String> item:scores1.entrySet()) {
	        System.out.println(item.getKey() +" - " +item.getValue());
	      }
	      
	      System.out.println("Region 2 Scores: ");
	      // printing scores from Region 2
	      for(Map.Entry<String,String> item:scores2.entrySet()) {
	        System.out.println(item.getKey() +" - " +item.getValue());
	      }
	    }        
	  }
	}





	

