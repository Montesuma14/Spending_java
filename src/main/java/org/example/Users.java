package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    private ArrayList<User> users;
    private File usersFile;


    public Users(){
        this.users = new ArrayList<User>();
        usersFile = new File("users.txt");
        if(!usersFile.exists()) {
            try {
                System.out.println("Создан файл с пользователями!");
                usersFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public  ArrayList<User> get_users() {
        return this.users;
    }

    //Регистрация нового пользователя
    public User create_User(){
        System.out.println("Введите имя");
        String name = SupportC.inLine();
        while (name.length() > 8 || find_by_name(name)){
            if (name.length() > 8){
                System.out.println("Максимальная длинна имени 8 символов.");
                name = SupportC.inLine();
            }
            else {
                System.out.println("Введенное имя уже используется. Введите другое имя");
                name = SupportC.inLine();
            }

        }

        System.out.println("Введите пароль");
        String password = SupportC.inLine();
        while (password.length() > 8){
            System.out.println("Максимальная длинна пароля 8 символов.");
            password = SupportC.inLine();
        }

        System.out.println(this.users);
        return add_User(new User(name, password, this.users.size()));
    }


    //Поиск пользователя по имени (возвращает true, если есть)
    private boolean find_by_name(String name){

        for (User user1: this.users) {
            if (name.equals(user1.getName()))
                return true;
        }
        return false;
    }

    private   User add_User(User user) {
        this.users.add(user);
        return user;
    }


    //Метод проверки пользователя и подтверждения входа
    public User check_entering() {

        Scanner in = new Scanner(System.in);
        User user = null;
        //Проверка имени пользователя
        System.out.println("Введите имя");
        String name = in.nextLine();
        for (User user1: this.users) {
            //System.out.println("USERS: check_entering: "+ this.users.get(i).name +"____" + name);
            if (user1.getName().equals(name)) {
                user = user1;
                break;
            }
        }
        while (user == null) {
            System.out.println("Такого имени не найдено, попробуйте снова.");
            name = in.nextLine();
            for (User user1: this.users) {
                if (user1.getName().equals(name)) {
                    user = user1;
                    break;
                }
            }
        }
        //Проверка пароля, найденного пользователя.
        System.out.println("Введите пароль");
        while (true){
            String password = in.nextLine();
            if (!password.equals(user.getPassword())){
                System.out.println("Пароль не верный. Попробуйте снова.");
            }
            else
                break;
        }
        //Возвращается найденный пользователь
        return user;

    }
}
