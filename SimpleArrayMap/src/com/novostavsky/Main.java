package com.novostavsky;

public class Main {

    public static void main(String[] args) {
	// let's test
        SimpleArrayMap<Integer, String> testMap = new  SimpleArrayMap<Integer, String>();
        testMap.put(1, "one");
        testMap.put(2, "two");
        testMap.put(10, "ten");
        testMap.remove(2);
        testMap.put(1, "changed one");

        System.out.println("1 - " + testMap.get(1));
        System.out.println("2 - " + testMap.get(2));
        System.out.println("3 - " + testMap.get(3));
        System.out.println("10 - " + testMap.get(10));
    }
}
