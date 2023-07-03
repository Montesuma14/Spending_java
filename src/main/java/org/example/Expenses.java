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

    //Добавить трату за сегодня
     public  void addExpenseToday(){
         LocalDate date;
         date = LocalDate.now();
         System.out.println("Трату какой категории хотите добавить?");
         SearchTypeExp();
         byte command = SupportC.take_command_and_check(this.TypesOfExpens.size());
         String type = this.TypesOfExpens.get(command-1);
         System.out.println("Сколько вы потратили?");
         double cost = SupportC.isSumm();
         setNewExpens(new Expense(date, type, cost ));
         System.out.println("Отлично! Ваш расход добавлен!");
         System.out.println(date + " " + type + " " + cost);

     }


    //Добавить трату за любой день этого месяца
    public  void addExpenseAnyDay(){
        LocalDate date = LocalDate.now();
        System.out.println("За какой день этого месяца вы хотите ввести расходы?");
        date = dateReturn(SupportC.inLine());
        System.out.println("Трату какой категории хотите добавить?");
        SearchTypeExp();
        byte command = SupportC.take_command_and_check(this.TypesOfExpens.size());
        String type = this.TypesOfExpens.get(command-1);
        System.out.println("Сколько вы потратили?");
        double cost = SupportC.isSumm();
        setNewExpens(new Expense(date, type, cost ));
        System.out.println("Отлично! Ваш расход добавлен!");
        System.out.println(date + " " + type + " " + cost);

    }


//Ввод дня текущего месяца с клавиатуры с проверками
    private LocalDate dateReturn(String dateS){
        LocalDate date = LocalDate.now();
        while (true) {
            if (!SupportC.isDigit(dateS)) {
                System.out.println("Значение не является числом. Введите подходящее значение.");
                dateS = SupportC.inLine();
            }
            else {
                int day = Integer.parseInt(dateS);
                //System.out.println(date.getDayOfMonth() + "   " + date.lengthOfMonth());
                if (day <= 0 || date.getDayOfMonth() < day ){
                    System.out.println("Значение выходит за диапазон месяца");
                    dateS = SupportC.inLine();
                }
                else return date.withDayOfMonth(day);
            }

        }
    }


    //Вывести все расходы пользователя
    public void printAllExpenses(){
        for (Expense expense: getExpenses()){
            System.out.println(expense.getDate() +"___"+ expense.getType() +"___" + expense.getCoast());
        }
    }

    //Добавление нового расхода в порядке возрастания даты
    private void setNewExpens(Expense expense){

        for (int i = 0; i < getExpenses().size(); i++){
            LocalDate aftDate = getExpenses().get(i).getDate();
            if (aftDate.isAfter(expense.getDate())){
                getExpenses().add( i,expense);
                return;
            }
        }
        getExpenses().add(expense);
    }



    public String printExpense(int i){
        return i + "___" + getExpenses().get(i).getDate() + "___" + getExpenses().get(i).getType() + "___" + getExpenses().get(i).getCoast();
    }


    //Отобразить текущие категории трат
     public void SearchTypeExp(){
        for (int i = 0; i < this.TypesOfExpens.size(); i++){
            System.out.println(i + ". " + this.TypesOfExpens.get(i));
        }
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
