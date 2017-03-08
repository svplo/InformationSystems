package infsysProj.infsysProj;
//package ch.ethz.globis.isk.domain;


import java.util.HashSet;
import java.util.Set;

/**
 * A specialized type of publications, represents the proceedings released at
 * a certain conference edition. The proceedings contains all the articles published
 * at that conference edition.
 */
public class Proceedings extends Publication {
	String note;
	int number;
	Publisher publisher;
	String volume;
	String isbn;
	Series series;
	ConferenceEdition confEdition;
	Set<InProceedings> publications = new HashSet<InProceedings>();
	
    public String getNote(){
    	zooActivateRead();
    	return note;
    }

    public void setNote(String note){
    	zooActivateWrite();
    	this.note = note;
    }

    public int getNumber(){
    	zooActivateRead();
    	return number;
    }

    public void setNumber(int number){
    	zooActivateWrite();
    	this.number = number;
    }

    public Publisher getPublisher(){
    	zooActivateRead();
    	return publisher;
    }

    public void setPublisher(Publisher publisher){
    	zooActivateWrite();
    	this.publisher = publisher;
    }

    public String getVolume(){
    	zooActivateRead();
    	return volume;
    }

    public void setVolume(String volume){
    	zooActivateWrite();
    	this.volume = volume;
    }
    
    public String getIsbn(){
    	zooActivateRead();
    	return isbn;
    }
    
    public void setIsbn(String isbn){
    	zooActivateWrite();
    	this.isbn = isbn;
    }

    public Series getSeries(){
    	zooActivateRead();
    	return series;
    }

    public void setSeries(Series series){
    	zooActivateWrite();
    	this.series = series;
    }

    public ConferenceEdition getConferenceEdition(){
    	zooActivateRead();
    	return confEdition;
    }

    public void setConferenceEdition(ConferenceEdition conferenceEdition){
    	zooActivateWrite();
    	this.confEdition = conferenceEdition;
    }

    public Set<InProceedings> getPublications(){
    	zooActivateRead();
    	return publications;
    }

    public void setPublications(Set<InProceedings> publications){
    	zooActivateWrite();
    	this.publications = publications;
    }

}