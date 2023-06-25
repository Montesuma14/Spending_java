package org.example;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class User {
    private int id;
    private String name;
    private String password;

    public Expenses expenses;



    public String getName(){
        return this.name;
    }
    public void setName(String name) { this.name = name;}


    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){ this.password = password;}


    public int getId(){
        return this.id;
    }
    public void setId(int id) {this.id = id;}


    public User(String name, String password, int id){
        this.name = name;
        this.password = password;
        this.id = id;
        this.expenses = new Expenses();
    }


}
