package com.generalassembly.oop;

class Lion extends Cat {
    protected int lives = 1;
    @Override
    public int getLives() {
        return lives;
    }
    public static void main(String[] args) {
        Cat catCat = new Cat();
        Cat lionCat = new Lion();
        System.out.println("a. " + catCat.lives);
        System.out.println("b. " + lionCat.lives);
        System.out.println("c. " + catCat.getLives());
        System.out.println("d. " + lionCat.getLives());
    }
}
