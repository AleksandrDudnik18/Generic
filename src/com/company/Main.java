package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        Random random = new Random();
        int size = random.nextInt(10) + 10;
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }

        System.out.println("size = " + list.getSize());
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }

        list.remove(0);
        list.remove(list.getSize() - 1);
        list.remove(500);

        System.out.println("");
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

}
