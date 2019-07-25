package com.ga.subclasses;

public class Lion extends Mammal {
	
	private boolean isAlpha;

    public Lion(boolean isAlpha){
        super(4, 50, false, "Lion");
        this.isAlpha = isAlpha;
    }

    public boolean isAlpha() {
        return isAlpha;
    }
    
    @Override
    public String toString() {
        return "Lion" + (isAlpha? " (Alpha)" : "");
    }

}
