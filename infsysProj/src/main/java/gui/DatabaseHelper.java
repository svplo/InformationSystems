package gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.*;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Sorts.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Aggregates.*;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import infsysProj.infsysProj.Conference;
import infsysProj.infsysProj.ConferenceEdition;
import infsysProj.infsysProj.InProceedings;
import infsysProj.infsysProj.Person;
import infsysProj.infsysProj.Proceedings;
import infsysProj.infsysProj.Publication;
import infsysProj.infsysProj.Publisher;
import infsysProj.infsysProj.Series;

public class DatabaseHelper {
	private static MongoDatabase database;
	private static String dbStandardName = "TheNoSQLDatabase9";
	private static MongoClient mongoClient;

	// source: http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
	public static void connectToDB() {
		// if using the default port
		// mongoClient = new MongoClient();
		// MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		Builder builder = MongoClientOptions.builder().connectTimeout(3000);
		mongoClient = new MongoClient("localhost", 27017);
		try {
			mongoClient.getAddress();
		} catch (Exception e) {
			System.out.println("Database unavailable!");
			mongoClient.close();
			return;
		}

		// DB db = mongoClient.getDB( "mydb" );
	}

	public static void closeConnectionDB() {
		mongoClient.close();
	}

	public static void createDB() {
		// database will be created automatically if it does not exist
		database = mongoClient.getDatabase(dbStandardName);
	}

	public static List<Publication> getAllPublications() {
		List<Publication> result = getAllInProceedings();
		result.addAll(getAllProceedings());
		return result;
	}

	public static List<Publication> getAllProceedings() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Proceedings");
		List<Publication> result = new ArrayList<Publication>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toProceeding(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<Person> getAllPeople() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Person");
		List<Person> result = new ArrayList<Person>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toPerson(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<Conference> getAllConference() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Conference");
		List<Conference> result = new ArrayList<Conference>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toConference(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<Series> getAllSeries() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Series");
		List<Series> result = new ArrayList<Series>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toSeries(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<Publisher> getAllPublisher() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Publisher");
		List<Publisher> result = new ArrayList<Publisher>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toPublisher(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<ConferenceEdition> getAllConferenceEdition() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("ConferenceEdition");
		List<ConferenceEdition> result = new ArrayList<ConferenceEdition>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toConferenceEdition(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static String getPublisherName(String proceedingName) {
		DatabaseHelper.connectToDB();

		DatabaseHelper.closeConnectionDB();
		return "";
	}

	public static String getSeriesName(String proceedingName) {
		DatabaseHelper.connectToDB();

		DatabaseHelper.closeConnectionDB();
		return "";
	}

	public static String getConferenceName(String proceedingName) {
		DatabaseHelper.connectToDB();

		DatabaseHelper.closeConnectionDB();
		return "";
	}

	public static String getConferenceEditionName(ConferenceEdition proceedingName) {
		DatabaseHelper.connectToDB();

		DatabaseHelper.closeConnectionDB();
		return "";
	}

	public static String getConferenceEditionProceeding(ConferenceEdition proceedingName) {
		DatabaseHelper.connectToDB();

		DatabaseHelper.closeConnectionDB();
		return "";
	}

	public static String getConferenceYear(String proceedingName) {
		DatabaseHelper.connectToDB();

		Collection<Proceedings> proceedings = new HashSet<Proceedings>();

		String result = null;
		if (proceedings.isEmpty()) {
			System.out.println("Error: Did not find a publication with name: " + proceedingName);

		} else {
			result = String.valueOf(proceedings.iterator().next().getConferenceEdition().getYear());
		}

		DatabaseHelper.closeConnectionDB();
		return result;
	}

	public static List<String> getAuthorsOfProceedings(String proceedingsName) {
		DatabaseHelper.connectToDB();

		Collection<Proceedings> proceedings = new HashSet<Proceedings>();
		List<String> result = new ArrayList<String>();
		if (proceedings.isEmpty()) {
			System.out.println("Error: Did not find a publication with ID: " + proceedingsName);

		} else {
			for (Person i : proceedings.iterator().next().getAuthors()) {
				result.add(i.getName());
			}
		}
		DatabaseHelper.closeConnectionDB();
		return result;
	}

	public static List<String> getIDCollection(Document doc, String key) {
		List<String> IDs = new ArrayList<String>();

		List<Document> inProceedingsIDsDoc = ((List<Document>) doc.get(key));
		for (Document d : inProceedingsIDsDoc) {
			IDs.add((String) d.get("_id"));
		}
		return IDs;
	}

	public static List<String> getInProceedingsOfProceedings(String proceedingId) {

		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Proceedings");
		BasicDBObject query = new BasicDBObject("_id", proceedingId);
		FindIterable<Document> iterable = collection.find(query);
		List<String> idList = getIDCollection(iterable.first(), "inProceedings");
		List<String> result = new ArrayList<String>();
		collection = database.getCollection("InProceedings");
		for (String inProceedingID : idList) {
			query = new BasicDBObject("_id", inProceedingID);
			iterable = collection.find(query);
			result.add((Adaptor.toInProceedings(iterable.first())).getTitle());
		}

		closeConnectionDB();
		return result;

	}

	public static List<String> getAuthoredPublicationsForPerson(String personName) {
		DatabaseHelper.connectToDB();

		Collection<Person> persons = new HashSet<Person>();
		List<String> result = new ArrayList<String>();
		if (persons.isEmpty()) {
			System.out.println("Error: Did not find a publication with ID: " + personName);

		} else {
			for (Publication i : persons.iterator().next().getAuthoredPublications()) {
				result.add(i.getTitle());
			}
		}

		DatabaseHelper.closeConnectionDB();
		return result;
	}

	public static List<String> getEditedPublicationsForPerson(String personName) {
		DatabaseHelper.connectToDB();

		Collection<Person> persons = new HashSet<Person>();
		List<String> result = new ArrayList<String>();
		if (persons.isEmpty()) {
			System.out.println("Error: Did not find a publication with ID: " + personName);

		} else {
			for (Publication i : persons.iterator().next().getEditedPublications()) {
				result.add(i.getTitle());
			}
		}

		DatabaseHelper.closeConnectionDB();
		return result;
	}

	public static List<Publication> getAllInProceedings() {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("InProceedings");
		List<Publication> result = new ArrayList<Publication>();
		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				result.add(Adaptor.toInProceedings(document));
			}
		});

		closeConnectionDB();
		return result;
	}

	public static List<Publication> searchForPublication(String search) {
		List<Publication> result = searchForProceedings(search);
		result.addAll(searchForInProceedings(search));
		return result;

	}

	public static List<Person> searchForPeople(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Person");
		BasicDBObject query = new BasicDBObject();
		query.put("name", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Person> result = new ArrayList<Person>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toPerson((Document) it.next()));
		}
		closeConnectionDB();

		return result;

	}

	public static List<Conference> searchForConference(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Conference");
		BasicDBObject query = new BasicDBObject();
		query.put("name", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Conference> result = new ArrayList<Conference>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toConference((Document) it.next()));
		}
		closeConnectionDB();

		return result;
	}

	public static List<Series> searchForSeries(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Series");
		BasicDBObject query = new BasicDBObject();
		query.put("name", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Series> result = new ArrayList<Series>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toSeries((Document) it.next()));
		}
		closeConnectionDB();

		return result;
	}

	public static List<ConferenceEdition> searchForConferenceEdition(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("ConferenceEdition");
		BasicDBObject query = new BasicDBObject();
		query.put("title", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<ConferenceEdition> result = new ArrayList<ConferenceEdition>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toConferenceEdition((Document) it.next()));
		}
		closeConnectionDB();

		return result;
	}

	public static List<Publisher> searchForPublisher(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Publisher");
		BasicDBObject query = new BasicDBObject();
		query.put("name", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Publisher> result = new ArrayList<Publisher>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toPublisher((Document) it.next()));
		}
		closeConnectionDB();

		return result;
	}

	public static List<Publication> searchForProceedings(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("Proceedings");
		BasicDBObject query = new BasicDBObject();
		query.put("title", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Publication> result = new ArrayList<Publication>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toProceeding((Document) it.next()));
		}
		closeConnectionDB();

		return result;

	}

	public static List<Publication> searchForInProceedings(String search) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("InProceedings");
		BasicDBObject query = new BasicDBObject();
		query.put("title", java.util.regex.Pattern.compile(search));

		FindIterable<Document> iterable = collection.find(query);
		List<Publication> result = new ArrayList<Publication>();
		Iterator it = iterable.iterator();
		while (it.hasNext()) {
			result.add(Adaptor.toProceeding((Document) it.next()));
		}
		closeConnectionDB();

		return result;
	}

	public static void addInProceedings(List<InProceedings> list) {
		MongoCollection<Document> collection = database.getCollection("InProceedings");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));

			if (i % 500 == 0) {
				System.out.println(i + " / " + length + " InProceedings added to Database.");
			}
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (InProceedings) created.");
		}
		System.out.println("All InProceedings added to Database");
	}

	public static void addPersons(List<Person> list) {
		// Add Persons
		MongoCollection<Document> collection = database.getCollection("Person");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (Persons) created.");
		}

	}

	public static void addSeries(List<Series> list) {
		// Add Series
		MongoCollection<Document> collection = database.getCollection("Series");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (Series) created.");
		}

	}

	public static void addPublishers(List<Publisher> list) {
		// Add Publisher
		MongoCollection<Document> collection = database.getCollection("Publisher");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (Publisher) created.");
		}

	}

	public static void addConferences(List<Conference> list) {
		// Add Conference
		MongoCollection<Document> collection = database.getCollection("Conference");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (Conference) created.");
		}

	}

	public static void addConferenceEditions(List<ConferenceEdition> list) {
		// Add Conference Editions
		MongoCollection<Document> collection = database.getCollection("ConferenceEdition");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (ConferenceEdition) created.");
		}

	}
/*
 * Person Add/Del/Upd
 */
	public static void addProceedings(List<Proceedings> list) {

		// Add Proceedings
		MongoCollection<Document> collection = database.getCollection("Proceedings");
		int length = list.size();
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < length; i++) {
			documents.add(Adaptor.toDBDocument(list.get(i)));

			if (i % 500 == 0) {
				System.out.println(i + " / " + length + " Proceedings added to Database.");
			}
		}
		if (!documents.isEmpty()) {
			collection.insertMany(documents);
		} else {
			System.out.println("No documents (Proceedings) created.");
		}
		System.out.println("All Proceedings added to Database");

	}

	public static void updatePerson(String id, String name, List<String> authoredPublications, List<String> editedPublications) {
		connectToDB();
		createDB();
		
		MongoCollection<Document> collection = database.getCollection("Person");
		BasicDBObject whereQuery = new BasicDBObject();
		Iterator<String> aPubIter = authoredPublications.iterator();
		Iterator<String> ePubIter = authoredPublications.iterator();
		Set<Publication> aPublications = new HashSet<Publication>();
		Set<Publication> ePublications = new HashSet<Publication>();
		while (aPubIter.hasNext()) {
			whereQuery.put("title", aPubIter.next());
			Iterator<Document> cursor = collection.find(whereQuery).iterator();
			while (cursor.hasNext()) {
				aPublications.add(Adaptor.toPublication(cursor.next()));
			}
		}
		while (ePubIter.hasNext()) {
			whereQuery.put("title", ePubIter.next());
			Iterator<Document> cursor = collection.find(whereQuery).iterator();
			while (cursor.hasNext()) {
				ePublications.add(Adaptor.toPublication(cursor.next()));
			}
		}
		Person p = new Person(id, name, aPublications, ePublications);
		BasicDBObject findPerson = new BasicDBObject();
		findPerson.put("_id", id);
		//instead of updating only specific fields, replace them all
		collection.replaceOne(findPerson, Adaptor.toDBDocument(p));
		closeConnectionDB();
	}

	public static void deletePerson(String id) {
		connectToDB();
		createDB();
		MongoCollection<Document> collection = database.getCollection("Person");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", id);
		collection.deleteOne(whereQuery);
		closeConnectionDB();
	}

	public static void addPerson(String newName, List<String> authoredPublications, List<String> editedPublications) {
			connectToDB();
			createDB();
			
			MongoCollection<Document> collection = database.getCollection("Person");
			BasicDBObject whereQuery = new BasicDBObject();
			Iterator<String> aPubIter = authoredPublications.iterator();
			Iterator<String> ePubIter = editedPublications.iterator();
			Set<Publication> aPublications = new HashSet<Publication>();
			Set<Publication> ePublications = new HashSet<Publication>();
			while (aPubIter.hasNext()) {
				whereQuery.put("title", aPubIter.next());
				Iterator<Document> cursor = collection.find(whereQuery).iterator();
				while (cursor.hasNext()) {
					aPublications.add(Adaptor.toPublication(cursor.next()));
				}
			}
			while (ePubIter.hasNext()) {
				whereQuery.put("title", ePubIter.next());
				Iterator<Document> cursor = collection.find(whereQuery).iterator();
				while (cursor.hasNext()) {
					ePublications.add(Adaptor.toPublication(cursor.next()));
				}
			}
			Person p = new Person(newName, aPublications, ePublications);
			
			p.setId((new ObjectId()).toString());
			//instead of updating only specific fields, replace them all
			try{
				collection.insertOne(Adaptor.toDBDocument(p));
			}
			catch(com.mongodb.MongoWriteException e){
				//there is probably a duplicate key
			}
			closeConnectionDB();
		}
	/*
	 * InProceedings stuff
	 */
	
	public static String getID(Document doc, String key) {
		//TODO Maybe someone know how to get _id of sub document
		
		DBObject  refer = (DBObject)doc.get(key);
		return ((String) refer.get("_id"));
	}

	public static Proceedings getProceedingOfInproceeding(String InProceedingId) {
		connectToDB();
		createDB();
		Proceedings p;

		MongoCollection<Document> collection = database.getCollection("InProceedings");
		BasicDBObject query = new BasicDBObject("_id", InProceedingId);
		FindIterable<Document> iterable = collection.find(query);
		collection = database.getCollection("Proceedings");
		p = Adaptor.toProceeding(iterable.first());
		
		closeConnectionDB();
		return p;
	}
	
	public static List<String> getAuthorsOfInProceeding(String inProceedingId){
		connectToDB();
		createDB();
		
		MongoCollection<Document> collection = database.getCollection("InProceedings");
		BasicDBObject query = new BasicDBObject("_id", inProceedingId);
		FindIterable<Document> iterable = collection.find(query);
		List<String> idList = getIDCollection(iterable.first(), "authors");
		List<String> result = new ArrayList<String>();
		collection = database.getCollection("Person");
		for (String authorID : idList) {
			query = new BasicDBObject("_id", authorID);
			iterable = collection.find(query);
			result.add((Adaptor.toPerson(iterable.first())).getName());
		}
		
		closeConnectionDB();
		return result;
	}
	
	public static void deleteInProceeding(String id) {
		connectToDB();
		createDB();
		MongoCollection<Document> collection = database.getCollection("InProceeding");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", id);
		collection.deleteOne(whereQuery);
		closeConnectionDB();
	}

	public static void updateInProceeding(String id, InProceedings newInProceeding, String procId, List<String> authors) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("InProceeding");
		BasicDBObject whereQuery = new BasicDBObject();
		Iterator<String> authorsIter = authors.iterator();
		List<Person> authorsN = new LinkedList<Person>();
		while (authorsIter.hasNext()) {
			whereQuery.put("name", authorsIter.next());
			Iterator<Document> cursor = collection.find(whereQuery).iterator();
			while (cursor.hasNext()) {
				authorsN.add(Adaptor.toPerson(cursor.next()));
			}
		}

		MongoCollection<Document> collection1 = database.getCollection("Proceedings");
		BasicDBObject query = new BasicDBObject("_id", procId);
		FindIterable<Document> iterable = collection1.find(query);
		Proceedings p = Adaptor.toProceeding(iterable.first());
		InProceedings inProc = new InProceedings(id, newInProceeding.getTitle(), authorsN, newInProceeding.getYear(), newInProceeding.getElectronicEdition(), newInProceeding.getNote(), newInProceeding.getPages(), p);
		BasicDBObject findInProceeding = new BasicDBObject();
		findInProceeding.put("id", id);
		// instead of updating only specific fields, replace them all
		collection.replaceOne(findInProceeding, Adaptor.toDBDocument(inProc));
		closeConnectionDB();
	}

	public static void addInProceeding(InProceedings newInProceeding, String procId, List<String> authors) {
		connectToDB();
		createDB();

		MongoCollection<Document> collection = database.getCollection("InProceeding");
		BasicDBObject whereQuery = new BasicDBObject();
		Iterator<String> authorsIter = authors.iterator();
		List<Person> authorsN = new LinkedList<Person>();
		while (authorsIter.hasNext()) {
			whereQuery.put("name", authorsIter.next());
			Iterator<Document> cursor = collection.find(whereQuery).iterator();
			while (cursor.hasNext()) {
				authorsN.add(Adaptor.toPerson(cursor.next()));
			}
		}

		MongoCollection<Document> collection1 = database.getCollection("Proceedings");
		BasicDBObject query = new BasicDBObject("_id", procId);
		FindIterable<Document> iterable = collection1.find(query);
		Proceedings p = Adaptor.toProceeding(iterable.first());
		InProceedings inProc = new InProceedings(newInProceeding.getTitle(), authorsN, newInProceeding.getYear(), newInProceeding.getElectronicEdition(), newInProceeding.getNote(), newInProceeding.getPages(), p);
		collection.insertOne(Adaptor.toDBDocument(inProc));
		closeConnectionDB();

	}

	/**
	 * 
	 * Queries
	 * 
	 **/
	
	//TODO: add Publication table
	// find publication by key (according to xml file)
	public static void query1(String id) {
		String thisQuery = "Query 1";
		connectToDB();
		createDB();

		Document pub;
		MongoCollection<Document> proc = database.getCollection("Proceedings");
		FindIterable<Document> resultProc = proc.find(Filters.eq("_id", id));
		MongoCollection<Document> inProc = database.getCollection("InProceedings");
		FindIterable<Document> resultInProc = inProc.find(Filters.eq("_id", id));

		Iterator<Document> itrProc = resultProc.iterator();
		Iterator<Document> itrInProc = resultInProc.iterator();
		try {
			PrintWriter writer = new PrintWriter(thisQuery + ".txt", "UTF-8");
			writer.println(thisQuery);
			if ((!itrProc.hasNext()) && (!itrInProc.hasNext())) {
				System.out.println("Error: Did not find a publication with ID: " + id);
			} else if ((itrProc.hasNext()) && (itrInProc.hasNext())){
				System.out.println("Error: Query is ambigous.");
			} else {
				if(itrProc.hasNext()){
					pub = itrProc.next();
				} else {
					pub = itrInProc.next();
				}
				writer.println("The title of the publication with id " + id + " is " + Adaptor.toPublication(pub).getTitle() + ".");
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("Could not print to file.");
		}
		closeConnectionDB();
	}
	
	// Find publications by title, returning only a subset of all found publications
	public static void query2(String title, int startOffset, int endOffset) {
		String thisQuery = "Query 2";
		connectToDB();
		createDB();

		MongoCollection<Document> proc = database.getCollection("Proceedings");
		FindIterable<Document> resultProc = proc.find(Filters.regex("title", title));
		MongoCollection<Document> inProc = database.getCollection("InProceedings");
		FindIterable<Document> resultInProc = inProc.find(Filters.regex("title", title));

		int i = 0;
		Iterator<Document> itrProc = resultProc.iterator();
		Iterator<Document> itrInProc = resultInProc.iterator();
		try {
			PrintWriter writer = new PrintWriter(thisQuery + ".txt", "UTF-8");
			writer.println(thisQuery);
			if ((!itrProc.hasNext()) && (!itrInProc.hasNext())) {
				System.out.println("Error: Did not find a publication with title: " + title);
			} else {
				while ((itrProc.hasNext() || itrInProc.hasNext()) && i <= endOffset){
					if(i < startOffset){
						if(itrInProc.hasNext()){
							itrInProc.next();
						} else {
							itrProc.next();
						}
						i++;
						continue;
					}
					else if(itrProc.hasNext()){
						writer.println(Adaptor.toPublication(itrProc.next()).getTitle());
						i++;
						
					} else if (itrInProc.hasNext()){
						writer.println(Adaptor.toPublication(itrInProc.next()).getTitle());
						i++;
					}
				}
			}
				
				writer.close();
		} catch (IOException e) {
			System.out.println("Could not print to file.");
		}
		closeConnectionDB();
	}
	

	// Find publications by title, returning only a subset of all found publications ORDERED by title
	public static void query3(String title, int startOffset, int endOffset) {
		String thisQuery = "Query 3";
		connectToDB();
		createDB();
		
		MongoCollection<Document> proc = database.getCollection("Proceedings");
		FindIterable<Document> resultProc = proc.find(Filters.regex("title", title));
		MongoCollection<Document> inProc = database.getCollection("InProceedings");
		FindIterable<Document> resultInProc = inProc.find(Filters.regex("title", title));
		ArrayList<Publication> all = new ArrayList<Publication>();
		Iterator<Document> itrProc = resultProc.iterator();
		Iterator<Document> itrInProc = resultInProc.iterator();
		boolean non = true;
		while(itrProc.hasNext()){
			all.add(Adaptor.toPublication(itrProc.next()));
			non = false;
		}
		while (itrInProc.hasNext()){
			non = false;
			all.add(Adaptor.toPublication(itrInProc.next()));
		}

		try {
			PrintWriter writer = new PrintWriter(thisQuery + ".txt", "UTF-8");
			writer.println(thisQuery);
			if (non) {
				System.out.println("Error: Did not find a publication with title: " + title);
			} else {
				Collections.sort(all, new Comparator<Publication>() {
				public int compare(Publication s1, Publication s2) { 
					return s1.getTitle().compareToIgnoreCase(s2.getTitle()); } 
				});
				for (int i = startOffset ; i <= endOffset; i++){
						writer.println(all.get(i).getTitle());
					}
				}
				writer.close();
		} catch (IOException e) {
			System.out.println("Could not print to file.");
		}
		closeConnectionDB();
		// our own sorting algorithm, for sorting afterwards
		/*
		 * Iterator<Publication> itr = proceedings.iterator(); List<Publication> listOfPublications = new ArrayList<Publication>(); for (int i = 0; itr.hasNext(); i++) { listOfPublications.add(itr.next()); }
		 *
		 * Collections.sort(listOfPublications, new Comparator<Publication>() {
		 *
		 * public int compare(Publication s1, Publication s2) { return s1.getTitle().compareToIgnoreCase(s2.getTitle()); } }); for (Publication p : listOfPublications) { System.out.println(p.getTitle()); } }
		 */
	}
	
	//TODO: find gui bug
	// returns name of the co-authors of a given author
	public static void query4(String author) {
		String thisQuery = "Query 4";
		connectToDB();
		createDB();

		MongoCollection<Document> pers = database.getCollection("Person");
		FindIterable<Document> resultPers = pers.find(Filters.regex("name", author));
		//using HashSet to avoid duplicates
		
		Iterator<Document> itr = resultPers.iterator();
		try {
			PrintWriter writer = new PrintWriter(thisQuery + ".txt", "UTF-8");
			writer.println(thisQuery);
			if(!itr.hasNext()) {
				writer.println("Error: Did not find any person named: " + author);
			}
			else if (itr.hasNext()) {
				Person a = Adaptor.toPerson(itr.next());
				Set<Publication> result = a.getAuthoredPublications();
				result.addAll(a.getEditedPublications());
				Iterator<Publication> itrRes = result.iterator();
				while(itrRes.hasNext()){
					Publication pub = itrRes.next();
					List<Person> coAuthors = pub.getAuthors();
					for (Person p : coAuthors) {
						System.out.println("a");
						//if(p.getName() != a.getName()){
							writer.println(p.getName());
						//}
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not print to file.");
		}
		closeConnectionDB();
	}

}