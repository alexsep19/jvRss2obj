package jvRss2obj.rss;

import java.util.Date;

public class FeedMessage {
	  String title;
	  String description;
	  String link;
	  String author;
	  String guid;
	  Date pubDate;
//	  String item;
//	  Date pubSaved;
	  Integer itemId;
	  
    public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	//	  public Date getPubSaved() {
//		return pubSaved;
//	}
//
//	public void setPubSaved(Date pubSaved) {
//		this.pubSaved = pubSaved;
//	}
//
//	public String getItem() {
//		return item;
//	}
//
//	public void setItem(String item) {
//		this.item = item;
//	}
//
	public Date getPubDate() {
		return pubDate;
	  }

	  public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public String getLink() {
	    return link;
	  }

	  public void setLink(String link) {
	    this.link = link;
	  }

	  public String getAuthor() {
	    return author;
	  }

	  public void setAuthor(String author) {
	    this.author = author;
	  }

	  public String getGuid() {
	    return guid;
	  }

	  public void setGuid(String guid) {
	    this.guid = guid;
	  }
	  @Override
	  public String toString() {
	    return "FeedMessage title=" + title + ",\n description=" + description
	        + "\n, link=" + link + "\n, author=" + author + "\n, guid=" + guid + "\n, pubDate="+pubDate;
	  }
}
