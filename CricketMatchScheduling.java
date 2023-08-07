import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CricketMatchScheduling {
	
	/*
	 Key 6 Value: [7, 8, 9, 10]
     Key 7 Value: [8, 9, 10]
     Key 8 Value: [9, 10]
	 */
    private static void addToList(HashMap<Integer, List<Integer>> map, int key, int value) {
        if (map.containsKey(key)) {
            List<Integer> existingList = map.get(key);
            existingList.add(value);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }

    public static void main(String[] args) {
        int teams = 10;
        int day = 1;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < teams; i++) {
            for (int j = i + 1; j <= teams; j++) {
                addToList(map, i, j);
            }
        }
        //Printing the map
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet())
        {
        	System.out.println("Key "+ entry.getKey() +" Value: "+entry.getValue());
        }
        
        //Final output days:matches 
        HashMap<Integer,Set<List<Integer>>> days_map = new HashMap<Integer, Set<List<Integer>>>();
        //iteration -> [2, 3, 4, 5, 6, 7, 8, 9, 10]
        int size = map.get(1).size();
        
        //[1,2],[2,3] matches in one day
        boolean isThere = false;

    	Set<List<Integer>> matches = new HashSet();	
        for(int i=0;i<size;i++) 
        {
        	for(Map.Entry<Integer, List<Integer>> entry: map.entrySet())
        	{
                List<Integer> set = new ArrayList();
                isThere = false;
                if(matches.size()!=0) {
	                for(List<Integer> match : matches)
	                {
	                	if(match.contains(entry.getKey()))
	                	{	
	                		System.out.println(entry.getKey()+" was there in the "+match);
	                		isThere = true;
	                		break;
	                	}
	                }
                }	
                if(isThere) {
                	isThere = false;
                	System.out.println("Skip this key "+entry.getKey());
                	continue;
                }
                
        		set.add(entry.getKey());

        		System.out.println("Current key: "+entry.getKey());
        		System.out.println("SET: "+set);
        		List<Integer> opponents = entry.getValue();
        		for(Integer opponent : opponents)
        		{
        			 isThere = false;
        			 if(matches.size()!=0) {
     	                for(List<Integer> match : matches)
     	                {
     	                	if(match.contains(opponent))
     	                	{
     	                		isThere = true;
     	                		continue;
     	                	}
     	                }
                     }	
        			 if(isThere)
        			 {
                      	continue;
        			 }
        			set.add(opponent);
        			System.out.println("SET: "+set);
 	
        			boolean found = false;

        	        for (Set<List<Integer>> setOfLists : days_map.values()) {
        	            if (setOfLists.contains(set)) {
        	                found = true;
        	                break;
        	            }
        	        }
        			if(found)
        			{
        				found = false;
        				System.out.println("Already the match was held between them so skip the opponent");
        				set.remove(1);
        				continue;
        			}
        			
        			matches.add(new ArrayList<>(set));
        			if(matches.size()>0 && matches.size()%3==0) {
        				System.out.println("Inaiku match mudunchhhhhhhh");
        				Set<List<Integer>> list = new HashSet<>(matches);
        				days_map.put(day, list);
        				System.out.println("Day"+day+" matches "+matches);
        				matches.clear();
        				day++;
        			}
        			System.out.println("**************"+matches);
        			break;
        		}
        		
        	}
        
        }
        
        
        
        System.out.println();
        System.out.println();
        
        System.out.println("Final result");
        System.out.println();
        System.out.println();
        for(Map.Entry<Integer, Set<List<Integer>>> entry: days_map.entrySet())
        {
        	System.out.println("Day "+ entry.getKey()+":");
        	for(List<Integer>  ans : entry.getValue())
        	{
        		System.out.println("   Team "+ans.get(0)+" vs Team "+ans.get(1));
        	}
        }

    }
        			
}
