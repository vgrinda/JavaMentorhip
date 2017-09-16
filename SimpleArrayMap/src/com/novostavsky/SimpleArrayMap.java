package com.novostavsky;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of Map based on Arrays,
 * there's default array size
 * Created by Volodymyr on 16.09.2017.
 */
public class SimpleArrayMap <K, V>{
    //initial size of the map, and a size to increase/decrease map
    private static int defaultSize = 10;
    private static int cleanThreshold = 33;

    private int mapSize;
    private int nullNumber;
    private MapItem<K,V>[] items;

    public SimpleArrayMap(){
        this.mapSize = 0;
        this.nullNumber = 0;
        this.items = new MapItem[SimpleArrayMap.defaultSize];
    }

    public int getMapSize(){
        return this.mapSize - this.nullNumber;
    }
    private boolean needMoreSpace(){
        if((this.items.length - this.mapSize) <= 0){
            return true;
        }
        return false;
    }
    private boolean needCleanUp(){
        if ( ((this.nullNumber / this.mapSize) * 100) >=  SimpleArrayMap.cleanThreshold){
            return true;
        }
        return false;
    }
    private SimpleArrayMap doIncreaseCapacity(){
        this.items = Arrays.copyOf(items, (this.mapSize + SimpleArrayMap.defaultSize));
        return this;
    }
    private SimpleArrayMap doCleanUp(){
        MapItem[] newItems = new MapItem[this.getMapSize()];
        int k =0;

        for(int i = 0; i<this.items.length; i++){
            if(this.items[i].getKey() != null){
                newItems[k] = this.items[i];
            }
        }
        this.mapSize = newItems.length;
        this.nullNumber = 0;
        this.items = newItems;

        return this;
    }

    //public methods - put, get, remove
    public V get(K key){
        if(key == null)
            return null;

        for(int i = 0; i<this.items.length; i++){
            if ((this.items[i] != null) && (this.items[i].getKey() != null) ){
                if(this.items[i].getKey().equals(key)){
                    return this.items[i].getValue();
                }
            }
        }
        return null; //should never happen!
    }

    public boolean put(K key, V value){
        if(key == null){
            return false;
        }
        //exists, no need to increase size, just replace value
        for(int i = 0; i<this.items.length; i++){
            if (this.items[i] != null){
                if (this.items[i].getKey().equals(key)){
                    this.items[i].setValue(value);
                    return true;
                }
            }
        }
        //doesn't exist, need to insert into the end
        if(this.needMoreSpace()){
            this.doIncreaseCapacity();
        }
        this.items[this.mapSize + this.nullNumber +1] = new MapItem(key, value);
        this.mapSize++;
        return true;
    }
    public boolean remove(K key){
        if(key == null){
            return false;
        }
        //exists, need to remove one
        for(int i = 0; i<this.items.length; i++){
            if (this.items[i] != null){
                if(this.items[i].getKey().equals(key)){
                    this.items[i].setValue(null);
                    this.items[i].setKey(null);

                    this.nullNumber++;
                    this.mapSize--;

                    return true;
                }
            }
        }
        //let's check if we need a cleanup
        if(this.needCleanUp()){
            this.doCleanUp();
        }
        //doesn't exist, no need to remove
        return false;
    }

}
