package com.ga.subclasses;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<Animal>();
		
		Mammal lion = new Lion(true);
		Mammal dog = new Mammal(4, 30, false, "Dog");
		
		Reptile snake = new Snake(false);
		Reptile lizard = new Reptile(true, 10, false, "Lizard");
		
		animals.add(lion);
		animals.add(dog);
		animals.add(snake);
		animals.add(lizard);
		
		animals.stream().forEach(System.out::println);
	}
}
