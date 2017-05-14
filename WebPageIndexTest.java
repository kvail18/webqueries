import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;


public class WebPageIndexTest {

	//@Test
	public void testWebPageIndex() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
	}

	//@Test
	public void testGetUrl() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		//System.out.println(webb.getUrl());
	}

	//@Test
	public void testGetWordCount() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getWordCount());
		
	}

	//@Test
	public void testContains() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.contains("hi"));
	}

	//@Test
	public void testGetCount() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getCount("is"));
	}

	//@Test
	public void testGetFrequency() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getFrequency("is"));
	}

	//@Test
	public void testGetLocations() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.print(webb.getLocations("hi"));
		
	}

//	@Test
	public void testWords() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		Iterator<String> meh = webb.words();
	}

	//@Test
	public void testToString() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.toString());
	}
	
	//@Test
	public void testContainsPhrase() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.containsPhrase("important if it"));
	}
	
	//@Test
	public void testGetFrequencyPhrase() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getPhraseFrequency("is it important"));
	}
	
	//@Test
	public void testPhraseCount() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getPhraseCount("is it"));
	}
	
	@Test
	public void testGetPhraseLocations() throws IOException {
		WebPageIndex webb =new WebPageIndex("testscannerfile");
		System.out.println(webb.getPhraseLocations("hi there"));
	}
	

}
