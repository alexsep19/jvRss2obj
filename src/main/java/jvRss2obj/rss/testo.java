package jvRss2obj.rss;

import java.util.ArrayList;
import java.util.Arrays;

public class testo {

	public static void main(String[] args) {
//		RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");
		LostFilmParser parser = new LostFilmParser("http://www.lostfilm.tv/rssdd.xml", 
				                                   new ArrayList<String>(Arrays.asList(new String[]{"Jessica Jones"})) );
	    Feed feed = parser.readFeed();
	    System.out.println(feed);
	    for (FeedMessage message : feed.getMessages()) {
	      System.out.println(message);

	    }

	}
//br1
}
