package com.novostavsky;

public class Main {
    public static void doFizzBuzz(int n){
        for (int i = 0; i<=n; i++){
            boolean div3 = false;
            boolean div5 = false;

            if(i%3 == 0) {
                System.out.print("Fizz");
                div3 = true;
            }
            if(i%5 == 0) {
                System.out.print("Buzz");
                div5 = true;
            }
            if (!(div3 | div5)){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
	    Main.doFizzBuzz(100);
    }
}
