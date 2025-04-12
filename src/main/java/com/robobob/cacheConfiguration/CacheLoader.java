package com.robobob.cacheConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLoader {
    Logger logger = LoggerFactory.getLogger(CacheLoader.class);
    private final int MAX_CAPACITY;
    private Map<String,CacheEntry> cache;
    public static class CacheEntry {
        String value;
        CacheEntry(String value) {
            this.value = value;
        }


    }
    public CacheLoader(int maxCapacity){
        this.MAX_CAPACITY = maxCapacity;
        this.cache = new LinkedHashMap<>(maxCapacity,0.75f,true);
    }

    //Load Data in cache or update it
    public synchronized void loadCache(String key, String value){
        try{
            if(cache.size() >= MAX_CAPACITY){
                String eldestKey = cache.keySet().iterator().next();
                cache.remove(eldestKey);
                logger.info("cache is full evicting entry" + eldestKey);
            }
            cache.put(key,new CacheEntry(value));
            logger.info("added/update in cache" + key + "=" + "value");
        }
        catch(Exception e){
            logger.error("Error loading data in cache" + e.getMessage());
        }
    }

    //Get Data from cache;
    public synchronized String getFromCache(String key){
        try{

            CacheEntry entry = cache.get(key);

            if(entry==null){
                logger.info("Cache Miss Question Asked is" + key);
                return null;
            }
            return entry.value;
        }
        catch(Exception e){
            logger.error("Error retrieving Data "+ e.getMessage());
            return null;
        }
    }



    //clear the cache
    public synchronized void clearCache(){
        cache.clear();
        logger.info("cache cleared");
    }
    //Display current cache content
    public void displaycache(){
        logger.info("current cache " + cache);
    }

}
