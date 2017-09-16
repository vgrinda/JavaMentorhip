package com.novostavsky;

/**
 * Class that describes objects in a Map.
 * To be used in SimpleArrayMap class.
 * Created by Volodymyr on 16.09.2017.
 */
public class MapItem <K, V>{
    private K key;
    private V value;

    public MapItem(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return this.key;
    }
    public V getValue(){
        return this.value;
    }
    protected MapItem setValue(V value){
        this.value = value;
        return this;
    }
    protected MapItem setKey(K key){
        this.key = key;
        return this;
    }
}
