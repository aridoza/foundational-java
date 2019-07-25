package com.ga.subclasses;

public class Mammal extends Animal {

	private int numLegs;

	public Mammal(int numLegs, float topSpeed, Boolean isEndangered, String name) {
		super(topSpeed, isEndangered, name);
		this.numLegs = numLegs;
		
	}
	
	public int getNumLegs() {
		return numLegs;
	}
	
	@Override
    public String toString() {
        return super.getName() + (super.getIsEndangered()? " (Endangered)" : "");
    }
}
