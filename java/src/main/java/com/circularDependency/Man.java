package com.circularDependency;




public class Man {
    private Woman woman;

    public void setWoman(Woman woman) {
        this.woman = woman;
    }
    public Woman getWoman(){
        return this.woman;
    }
}
