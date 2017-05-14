//We adhered to the honor code on this assignment
import java.io.IOException;
import java.util.*;

public class WebPageIndex {


	int index = 0;
	MyTreeMap<String, LinkedList<Integer>> treez = new MyTreeMap<String, LinkedList<Integer>>();
	MyTreeSet<String> setz = new MyTreeSet<String>();
	String url ="";

	public WebPageIndex(String baseUrl) throws IOException{
		url = baseUrl;
		HTMLScanner scan = new HTMLScanner(baseUrl);
		while (scan.hasNext()){
			String temp = scan.next();
			temp= temp.toLowerCase();
			if (treez.get(temp)!=null){
				LinkedList<Integer> meh =treez.get(temp);
				meh.add(index);
			}
			else{
				//System.out.println(temp);
				LinkedList<Integer> indeces = new LinkedList<Integer>();
				indeces.add(index); 
				treez.put(temp, indeces);
			}
			++index;
		}
		while (scan.hasNextLink()){
			String linkTemp= scan.nextLink();
			setz.add(linkTemp);
		}

	}

	public ArrayList<String> getLinks(){
		ArrayList<String> List = new ArrayList<String>();
		Iterator<String> it = setz.iterator();
		while (it.hasNext()){
			String temp = it.next();
			List.add(temp);
		}
		return List;
	}

	
	public String getUrl(){
		return url;
	}

	public int getWordCount() {
		return index;
	}

	public boolean contains(String s) {
		s=s.toLowerCase();
		if (treez.get(s)!=null){
			return true;
		}
		return false;
	}

	public int getCount(String s) {
		if (contains(s)){
			LinkedList<Integer> list = treez.get(s);
			return list.size();
		}
		return 0;
	}

	public double getFrequency(String s) {
		double count = (double)getCount(s);
		double freq = count/index;
		return freq;
	}

	public List<Integer> getLocations(String s) {
		LinkedList<Integer> loc = treez.get(s);
		if (loc==null){
			return new LinkedList<Integer>();
		}
		return loc;
	}

	public Iterator<String> words() {
		Iterator<String> It = treez.keys();
		return It;
	}

	public String toString() {
		return treez.toString();
	}

	/* 
	 * Multi-word Phrases
	 * Work on these after you have the previous methods working correctly
	 */

	public boolean containsPhrase(String s) {
		
		s= s.toLowerCase();
		
		String[] strings = s.split("\\s+");
		
		List<Integer> indeces = getLocations(strings[0]);
		
		
		if (consecutive(strings, 1, indeces) == null)
			return false;
		
		return true;
		
	}
	
	public List<Integer> consecutive(String[] strings, int counter, List<Integer> indeces){
		
		if (indeces.isEmpty())
			return null;
		
		if (counter == strings.length)
			return indeces;
		
		
		List<Integer> indecesCompare = getLocations(strings[counter]);
		
		
		Iterator<Integer> it = indeces.iterator();
		
		
		List<Integer> newIndeces = new LinkedList<Integer>();
		
		while(it.hasNext()){
			
			int a = it.next();
			
			Iterator<Integer> compareIt = indecesCompare.iterator();
			
			while(compareIt.hasNext()){
				
				int b = compareIt.next();
				
				if (b == a+1)
				{
					
					newIndeces.add(b);
				}
			}
		}
		return consecutive(strings, counter+1, newIndeces);
		
	}

	public int getPhraseCount(String s) {
		s=s.toLowerCase();
		List<Integer> locations = getPhraseLocations(s);
		
		if (locations == null)
			return 0;
		
		return locations.size();
		
	}

	public double getPhraseFrequency(String s) {
		
		return (double) getPhraseCount(s) / getWordCount();
		
	}

	public List<Integer> getPhraseLocations(String s) {
		String[] strings = s.split("\\s+");
		
		List<Integer> indeces = getLocations(strings[0]);
		
		List<Integer> endLocations = consecutive(strings, 1, indeces);
		
		
		if (endLocations==null){
			return new LinkedList<Integer>();
		}
		
		Iterator<Integer> it = endLocations.iterator(); 
		
		List<Integer> startLocations = new LinkedList<Integer>();
		
		while(it.hasNext())
		{
			int loc = it.next();
			loc -= (strings.length -1);
			startLocations.add(loc);
		}
		
		return startLocations;
	}

	
	public static void main(String[] url1){

		try {
			WebPageIndex webb = new WebPageIndex(url1[0]);
			Iterator<String> it = webb.words();
			System.out.println("Frequency and index of words in " + url1[0]);
			while (it.hasNext()){
				String temp = it.next();
				double freq= webb.getFrequency(temp);
				System.out.printf("%-18s %.6f     %s", temp, freq, webb.getLocations(temp));
				System.out.println();
			}
			System.out.println();
			System.out.println("Links:");
			ArrayList<String> links = webb.getLinks();
			for (int x=0; x<links.size(); x++){
				System.out.println(links.get(x));
			}
			
			
		} 
		catch (IOException e) {
			System.out.print("problem with Url");
			e.printStackTrace();
			System.exit(1);
		}
		}

}

