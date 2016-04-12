package jvRss2obj.rss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LostFilmParser {
	  static final String TITLE = "title";
	  static final String DESCRIPTION = "description";
	  static final String CHANNEL = "channel";
	  static final String LANGUAGE = "language";
	  static final String LINK = "link";
	  static final String AUTHOR = "author";
	  static final String ITEM = "item";
	  static final String PUB_DATE = "lastBuildDate";
	  static final String ITEM_PUB_DATE = "pubDate";
	  static final String GUID = "guid";

	  final URL url;
//	  ArrayList<Object> needItems;

	public LostFilmParser(String feedUrl/*, ArrayList<Object> items*/) {
	    try {
	      this.url = new URL(feedUrl);
//	      needItems = items;
	    } catch (MalformedURLException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public Feed readFeed() {
	    Feed feed = null;
	    try {
	      boolean isFeedHeader = true;
	      // Set header values intial to the empty string
	      String description = "";
	      String title = "";
	      String link = "";
	      String language = "";
	      String copyright = "";
	      String author = "";
	      Date pubdate = null;
	      Date itemPubDate = null;
//	      int curItem = 0;
	      boolean isPub = true;
	      String guid = "";

	      // First create a new XMLInputFactory
	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	      // Setup a new eventReader
	      InputStream in = read();
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      // read the XML document
	      while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();
	        if (event.isStartElement()) {
	          String localPart = event.asStartElement().getName()
	              .getLocalPart();
	          switch (localPart) {
	          case ITEM:
	            if (isFeedHeader) {
	              isFeedHeader = false;
	              feed = new Feed(title, link, description, language,
	                  copyright, pubdate);
	            }
	            event = eventReader.nextEvent();
	            break;
	          case TITLE:
	            title = getCharacterData(event, eventReader);
	            break;
	          case DESCRIPTION:
	            description = getCharacterData(event, eventReader);
	            break;
	          case LINK:
	            link = getCharacterData(event, eventReader);
	            break;
	          case GUID:
	            guid = getCharacterData(event, eventReader);
	            break;
	          case LANGUAGE:
	            language = getCharacterData(event, eventReader);
	            break;
	          case AUTHOR:
	            author = getCharacterData(event, eventReader);
	            break;
	          case PUB_DATE:
	            pubdate = procDate(getCharacterData(event, eventReader));
	            break;
	          case ITEM_PUB_DATE:
	        	  itemPubDate = procDate(getCharacterData(event, eventReader));
		            break;
	          }
	        } else if (event.isEndElement()) {
	          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
//	        	boolean isFind = false;
//	        	if (needItems != null){
//	        		for(int i = 0; i < needItems.size(); i++){
//	        		  if (title.indexOf(needItems.get(i)) >= 0){
////	        			  titleFound(String title)
//	        			curItem = needItems.get(i);
//	        			isFind = true;
//	        			needItems.remove(i);
//	        			break;
//	        		  }
//	        		}
//	        		if (!isFind) continue;
//	        	}
	        	Integer[] itemId = new Integer[1];
	        	if (!titleFoundInRss(title, itemId)) continue;
	            FeedMessage message = new FeedMessage();
	            message.setAuthor(author);
	            message.setDescription(procDescription(description));
	            message.setGuid(guid);
	            message.setLink(link);
	            message.setTitle(title);
	            message.setPubDate(itemPubDate);
	            message.setItemId(itemId[0]);
//	            message.setItem(curItem);
//	            setPublished();
	            feed.getMessages().add(message);
	            event = eventReader.nextEvent();
	            continue;
	          }
	        }
	      }
	    } catch (XMLStreamException e) {
	      throw new RuntimeException(e);
	    }
	    return feed;
	  }
	  
	  private Date procDate(String d){
		  Date ret = null;
		  try{
		    ret = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US).parse(d);
		  }catch(Exception e){
			 System.out.println(this.getClass().getSimpleName() + ".procDate "+ e.getMessage()); 
		  }
		  return ret;
	  }
	 
	  private String procDescription(String desc){
		  String teg = "strong";
		  int beg = desc.indexOf(teg);
		  if ( beg < 0 ) return "<strong> не найдено";
		  String tail = desc.substring(beg + teg.length());
		  beg = tail.indexOf(">") + 1;
		  int end = tail.indexOf("<");
		  return tail.substring(beg, end);
	  }
	  
	  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
		      throws XMLStreamException {
		    String result = "";
		    event = eventReader.nextEvent();
		    if (event instanceof Characters) {
		      result = event.asCharacters().getData();
		    }
		    return result;
		  }

	  private InputStream read() {
		    try {
		      return url.openStream();
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		  }
	  
//    public ArrayList<Integer> getNeedItems() {
//		return needItems;
//	}
//
//	public void setNeedItems(ArrayList<Integer> needItems) {
//		this.needItems = needItems;
//	}

	  public boolean titleFoundInRss(String title, Integer[] itemId){
		 return false; 
	  }
}
