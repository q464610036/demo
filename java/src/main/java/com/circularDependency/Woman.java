package com.circularDependency;

public class Woman
{
    private Man man;
    public void setMan(Man man) {
        this.man = man;
    }
    public Man getMan(){
        return this.man;
    }
}
