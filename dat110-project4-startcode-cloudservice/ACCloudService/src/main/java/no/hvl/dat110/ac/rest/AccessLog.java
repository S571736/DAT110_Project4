package no.hvl.dat110.ac.rest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	private Gson gson;

	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
		gson = new Gson();
	}

	// TODO: add an access entry for the message and return assigned id
	public int add(String message) {

		int id = cid.incrementAndGet();
		log.put(id, gson.fromJson(message, AccessEntry.class));
		return id;
	}
		
	// TODO: retrieve a specific access entry 
	public AccessEntry get(int id) {
		return log.get(id);
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		log.clear();
		cid.set(0);
		
	}
	
	// TODO: JSON representation of the access log

	public String toJson () {
    	
		String json = gson.toJson(log);
    	
    	return json;
    }
}
