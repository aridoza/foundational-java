package com.ga.subclasses;

public class Snake extends Reptile {
	
	private boolean isPoisonous;

    public Snake(boolean isPoisonous){
        super(false,5,false,"Snake");
        this.isPoisonous = isPoisonous;
    }

    public boolean isPoisonous() {
		return isPoisonous;
	}

	@Override
    public String toString() {
        return "Snake" + (isPoisonous? " (Poisonous)" : "");
    }

}
