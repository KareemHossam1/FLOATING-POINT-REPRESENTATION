package floating.point.representation;
// Importing Packages
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class numButton {
    Button btn = new Button();  // Create a new button
// Create a constructor for numbers and dot    
// The constructor has 3 arguments; Button name, Text field to write on and a button to disable
public numButton(String num , TextField Input, Button negative){
    btn.setText(num);  // Set button text
    btn.setPrefSize(160,50);    // Set button size
    btn.setStyle("-fx-font-weight: bold");  // Make the text Bold
    // Button listner
    btn.setOnAction(e->{
        negative.setDisable(true);  // Disable negative button
        Input.setText(Input.getText()+num);  // write the number on the text field
        if(num.equals("."))  // If it is dot button
            btn.setDisable(true);   // Disable it
    });}
// Create a constructor for negative
// The constructor has 2 arguments; Button name and Text field to write on
public numButton(String num , TextField Input){
    btn.setText(num);   // Set button text
    btn.setPrefSize(160,50);    // Set button size
    btn.setStyle("-fx-font-weight: bold");   // Make the text Bold
    // Button listner
    btn.setOnAction(e->{
        Input.setText(Input.getText()+num); // write negative on the text field
        btn.setDisable(true);   // Disable it
});}
// Create a method to return the button in order to change its properties
public Button getButton(){
    return btn;
}}