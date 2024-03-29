package com.enigma.enigmaStore.controller;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import com.enigma.enigmaStore.model.Product;
import com.enigma.enigmaStore.util.Validator;
import com.enigma.enigmaStore.view.UI;

public class Service {
    Validator validator = new Validator();
    UI ui = new UI();
    public void serviceController(int input){
        switch (input) {
            case 1:
                ui.addProductUI();
                break;
            case 2:
                ui.updateProductUI();
                break;
            case 3:
                ui.removeProductUI();
                break;
            case 4:
                ui.viewProductUI();
                break;
            case 5:
                ui.findProductUI();
                break;
            case 6:
                System.out.println(ui.getQuit());
                break;
            default:
                System.out.println(ui.getInvalidService());
                break;
        }
    }

    public void addProduct(String name, String brand, double price){
        if(name == "" || brand == "" || price == 0){
            System.out.println(ui.getInvalidInput());
            ui.menu();
        } else{
           ProductController controller = new ProductController();
           controller.addProduct(new Product(UUID.randomUUID().toString(), name, brand, price, new Date()));
           controller.getProduct(0);
           System.out.println("\nInputted Product: ");
           System.out.println(controller.getProduct(0));
        }
    }

    public void displayProducts(){
        ProductController products = ProductController.getInstance();
        int index = 1;
        for(int i = 0; i<products.getTotalProduct(); i++){
            Product product = products.getProduct(i);
            index += i;
            System.out.printf("%d. %s | %s | %s | %f | %tF %n", index, product.getId(), product.getName(), product.getBrand(), product.getPrice(), product.getDate());
        }
    }

    public void removeProducts(Scanner input){
        ProductController products = ProductController.getInstance();
        if(input.hasNextInt()){
            int index = input.nextInt();
            index--;
            for(int i = 0; i<products.getTotalProduct(); i++){
                Product product = products.getProduct(i);
                if(index == i){
                    products.removeProduct(product);
                }
            }
        } else{
            System.out.println(ui.getInvalidInput());
        }
        ui.menu();     
    }
}
