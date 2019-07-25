package com.ga.subclasses;

public class Reptile extends Animal {
	
	private boolean hasShell;

    public Reptile(boolean hasLegs, int topSpeed, boolean isEndangered, String name){
        super(topSpeed, isEndangered, name);
        this.hasShell = hasLegs;
    }

    public boolean getHasShell() {
        return hasShell;
    }
    
    @Override
    public String toString() {
        return super.getName() + (super.getIsEndangered()? " (Endangered)" : "");
    }
}
