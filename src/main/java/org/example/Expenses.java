package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Expenses {

    private  ArrayList<Expense> expenses;
    private ArrayList<String> TypesOfExpens;




    public Expenses (){

        this.expenses = new ArrayList<Expense>();
        this.TypesOfExpens = new ArrayList<String>();
        this.TypesOfExpens.add("Продукты");
        this.TypesOfExpens.add("Кафе и рестараны");
        this.TypesOfExpens.add("Медицина");
        this.TypesOfExpens.add("Питомец");
        this.TypesOfExpens.add("Транспорт");


    }

    public void setExpenses(ArrayList<Expense> Expenses){
        this.expenses = Expenses;
    }
    public ArrayList<Expense> getExpenses(){
        return this.expenses;
    }


    //Добавление нового расхода в порядке возрастания даты
    public void setNewExpens(Expense expense){

        for (int i = 0; i < getExpenses().size(); i++){
            LocalDate aftDate = getExpenses().get(i).getDate();
            if (aftDate.isAfter(expense.getDate())){
                getExpenses().add( i,expense);
                return;
            }
        }
        getExpenses().add(expense);
    }

    public void setTypesOfExpens(ArrayList<String> typesOfExpens) {
        TypesOfExpens = typesOfExpens;
    }

    public ArrayList<String> getTypesOfExpens() {
        return TypesOfExpens;
    }


    public class Expense {
        private LocalDate date;

        private String comment;
        private String type;
        private double coast;




        public Expense (LocalDate date, String type, double coast ){
            this.date = date;
            this.type = type;
            this.coast = coast;
            this.comment = null;
        }
        public Expense (LocalDate date, String type, double coast, String comment ){
            this.date = date;
            this.type = type;
            this.coast = coast;
            this.comment = comment;
        }


        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getCoast() {
            return coast;
        }

        public void setCoast(double coast) {
            this.coast = coast;
        }
    }

}
