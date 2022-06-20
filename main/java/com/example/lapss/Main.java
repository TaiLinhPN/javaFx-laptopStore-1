package com.example.lapss;

import com.example.lapss.connect.DBConn;
import com.example.lapss.connect.HandleLaptop;
import com.example.lapss.objects.Laptop;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import jdk.internal.icu.impl.CharacterIteratorWrapper;

//
//public class Main extends Application {
//    @Override
////    public void start(Stage stage) throws IOException {
////        new DBConn();
////        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
////        stage.setTitle("Hello!");
////        stage.setScene(scene);
////        stage.show();
////
////    }
//    public void start(Stage primaryStage) throws Exception {
//
//        DBConn conn = new DBConn();
//        conn.getCompanys();
////        conn.querryDB("DELETE  FROM `companys` WHERE id = "+2);
//
//        HandleLaptop lap  = new HandleLaptop();
//
//        lap.getLaps();
//        lap.updateLaptop("aces","anh2","aces", 22222, 1);

//        lap.createLaps("dell2221", "adasasdasd", "dell", 21111);


//       FlowPane root = new FlowPane();
//       root.setPadding(new Insets(15,15,15,15));
//
//        // Button 1
//        Button button1= new Button("Button1");
//        root.getChildren().add(button1);
//
//
//        // Button 2
//        Button button2 = new Button("Button2");
//        button2.setPrefSize(100, 100);
//        root.getChildren().add(button2);
//
//
//
//
//       Scene scene = new Scene(root, 550, 250);
//
//       primaryStage.setTitle("Laptop Store");
//       primaryStage.setScene(scene);
//       primaryStage.show();
//    }
//
//    void aaaa(){
//      }


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DBConn connection = new DBConn();
        VBox root = new VBox();
        VBox laptopRoot = new VBox();

        HBox headerBox = new HBox();
        Label nameHead = new Label("Name" );
        Label imageHead = new Label("Image" );
        Label priceHead = new Label("Price" );
        Label companyNameHead = new Label("Company" );

        headerBox.getChildren().addAll(nameHead,imageHead,priceHead,companyNameHead);
        headerBox.setSpacing(42);


        HBox inputLaptop = new HBox();
        TextField tfName = new TextField();
        TextField tfImg = new TextField();
        TextField tfPrice = new TextField();
        TextField tfCompany = new TextField();

        Button btnAdd = new Button("Add product");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("aaaaaaa");

                String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ tfName.getText()+"','"+ tfImg.getText()+"',"+ tfPrice.getText()+",'" + tfCompany.getText()+"')";
                connection.querryDB(sql);

                renderLaps(laptopRoot, connection);
            }
        });

        Button btnSearch = new Button("Search product");
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String proviso = tfName.getText();
                System.out.println(proviso);


                renderLaps(laptopRoot, connection, proviso);
            }
        });

        inputLaptop.getChildren().addAll(tfName,tfImg,tfPrice,tfCompany,btnAdd);
        root.getChildren().addAll(btnSearch,headerBox,inputLaptop, laptopRoot);

        renderLaps(laptopRoot,  connection);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Laptop Store");
        stage.setScene(scene);
        stage.show();
    }

    void searchLaps(){

    }
    void displayLaps(DBConn connection, VBox root, List<Laptop> laptopList) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {
            int index = i;
            HBox laptopstBox = new HBox();
            Label lbId = new Label("" + laptopList.get(index).id);
            Label lbName = new Label(laptopList.get(index).name);
            Label lbImg = new Label("" + laptopList.get(index).img);

            Image image = new Image("" + laptopList.get(index).img);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(220);
            imageView.setPreserveRatio(true);
            Label lbCompany= new Label("" + laptopList.get(index).company);

            Label lbPrice = new Label("" + laptopList.get(index).price);
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(actionEvent -> {
                System.out.println("Click delete " + laptopList.get(index).id);

                connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).id);
                renderLaps(root, connection);
            });

            laptopstBox.setSpacing(42);
            laptopstBox.getChildren().addAll(lbId, lbName, lbImg,imageView, lbPrice,lbCompany, btnDelete);
            root.getChildren().add(laptopstBox);
        }
    }

    void displayLaps(DBConn connection, VBox root, List<Laptop> laptopList, String proviso) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {

            int index = i;
            System.out.println(laptopList.get(index).name);
            System.out.println(proviso);
            System.out.println(""+laptopList.get(index).name.equals(proviso));

            String name = ""+laptopList.get(index).name;

            if(name.equals(proviso)){
                HBox laptopstBox = new HBox();
                Label lbId = new Label("" + laptopList.get(index).id);
                Label lbName = new Label(laptopList.get(index).name);
                Label lbImg = new Label("" + laptopList.get(index).img);

                Image image = new Image("" + laptopList.get(index).img);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(220);
                imageView.setPreserveRatio(true);
                Label lbCompany= new Label("" + laptopList.get(index).company);

                Label lbPrice = new Label("" + laptopList.get(index).price);
                Button btnDelete = new Button("Delete");

                btnDelete.setOnAction(actionEvent -> {
                    System.out.println("Click delete " + laptopList.get(index).id);

                    connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).id);
                    renderLaps(root, connection);
                });

                laptopstBox.setSpacing(42);
                laptopstBox.getChildren().addAll(lbId, lbName, lbImg,imageView, lbPrice,lbCompany, btnDelete);
                root.getChildren().add(laptopstBox);
            }

        }
    }


    private void renderLaps(VBox root, DBConn connection){
        List<Laptop> products = connection.getLaps();
        displayLaps(connection, root, products);
    }
    private void renderLaps(VBox root, DBConn connection, String proviso){
        List<Laptop> products = connection.getLaps();
        displayLaps(connection, root, products, proviso);
        System.out.println(proviso);
    }



    public static void main(String[] args) {
        launch();
    }
}