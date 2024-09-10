package com.example.s0.viewmodels;

abstract public class PowerDecorator implements Powers {
    protected Powers tempPowers;

    public PowerDecorator(Powers newPower){
        tempPowers = newPower;
    }
    public String getDescription() {
        return tempPowers.getDescription();
    }



}
