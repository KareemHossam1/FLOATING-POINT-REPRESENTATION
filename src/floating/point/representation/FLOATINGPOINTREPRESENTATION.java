package floating.point.representation;
// Importing Packages
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class FLOATINGPOINTREPRESENTATION extends Application {
    Label order, result, resultName;  // Create project labels
    TextField Input;  // Create the text field
    Button convert, reset;  // Create buttons 
    VBox vbox;  // Create VBox to order elements
    Scene scene;  // Create scene
    Alert emptyInput;  // Create an alert message for invalid inputs
    // Create HBoxes to order buttons
    HBox h01=new HBox();
    HBox h23=new HBox();
    HBox h45=new HBox();
    HBox h67=new HBox();
    HBox h89=new HBox();
    HBox hDN=new HBox();
    HBox hCR=new HBox();
    @Override
    public void start(Stage primaryStage) {
        // Creating GUI
        order = new Label("Enter any Number : \n ");  // Create new object and set its text
        order.setStyle("-fx-font-weight: bold");    // To set label font bold
        Input = new TextField("");  // Create the text field object
        Input.setEditable(false);  // To make the user enter numbers from buttons not from keyboard
        convert = new Button("Convert");   // Button text
        convert.setPrefSize(160,50);    // Button size
        reset= new Button("Reset"); // Button text
        reset.setPrefSize(160,50);  // Button text
        resultName = new Label("\n Sign   Exponent\t\t   Mantissa");    // Create new object and set its text
        result = new Label ("    0   | 00000000 | 00000000000000000000000");    // Create new object and set its text
        numButton btnNeg = new numButton("-",Input);  // Create negative buttons form the class
        // Create number buttons
        numButton btn1 = new numButton("1",Input,btnNeg.getButton());
        numButton btn2 = new numButton("2",Input,btnNeg.getButton());
        numButton btn3 = new numButton("3",Input,btnNeg.getButton());
        numButton btn4 = new numButton("4",Input,btnNeg.getButton());
        numButton btn5 = new numButton("5",Input,btnNeg.getButton());
        numButton btn6 = new numButton("6",Input,btnNeg.getButton());
        numButton btn7 = new numButton("7",Input,btnNeg.getButton());
        numButton btn8 = new numButton("8",Input,btnNeg.getButton());
        numButton btn9 = new numButton("9",Input,btnNeg.getButton());
        numButton btn0 = new numButton("0",Input,btnNeg.getButton());
        numButton btnDot = new numButton(".",Input,btnNeg.getButton());  // Create dot button
        // Order buttons using HBoxes
        h89.getChildren().addAll(btn8.getButton(),btn9.getButton());
        h67.getChildren().addAll(btn6.getButton(),btn7.getButton());
        h45.getChildren().addAll(btn4.getButton(),btn5.getButton());
        h23.getChildren().addAll(btn2.getButton(),btn3.getButton());
        h01.getChildren().addAll(btn0.getButton(),btn1.getButton());
        hDN.getChildren().addAll(btnDot.getButton(),btnNeg.getButton());
        hCR.getChildren().addAll(convert,reset);
        vbox = new VBox();  // Create a new VBox to order elements
        vbox.getChildren().addAll(order,Input,h89,h67,h45,h23,h01,hDN,hCR,resultName,result);  // Order elements
        vbox.setPadding(new Insets(15,15,15,15));  // Set their position
        scene = new Scene(vbox, 335, 475);   // Create scene, add elements and change its size 
        emptyInput = new Alert(Alert.AlertType.ERROR);   // Create error message
        //------------------------------------------------------------------------------------
        // Calculating floating point representation
        // Convert button listner
        convert.setOnAction(e->{
            // Declare variables we will use in calculating floating point representation
            String input=Input.getText() , binaryFrac = "" , mant , binaryInt , exp ;
            int sign = 0 , add , intInput ;
            double fraction  ;
            // If the user entered wrong input
            // Expected wrong inputs are "" , "-" and "." 
            if (Input.getText().equals("")||Input.getText().equals(".")||Input.getText().equals("-")){
                emptyInput.setTitle("Error");
                emptyInput.setHeaderText("");
                emptyInput.setContentText("You must enter a valid input!");
                emptyInput.show();
            }
            // If the input is correct
            else{
                double input_D=Double.valueOf(input);  // Variable converting String input to a double one
                fraction =Math.abs(input_D - (int) input_D) ; // Take the absolute value of the fraction part
                intInput = Math.abs((int) input_D); // To deal with the integer positive number
                // If statement to calculate +0 and -0
                if (fraction==0 & intInput==0){
                    if(input.indexOf("-") == -1)  // If it is positive zero
                        result.setText("    0   | 00000000 | 00000000000000000000000");
                    else        // If it is negative zero
                        result.setText("    1   | 00000000 | 00000000000000000000000");
                }
                // If the input is correct and not equals +0 or -0
                else{
                    // Calculate sign
                    if (input_D<0) {
                    sign=1;
                    }
                    // Converting number to binary
                    // 1- converting fraction to binary
                    // calculate binary representation by custom logic
                    while(fraction!=0){
                        fraction*=2;   // Multiply fraction by 2
                        if (fraction >= 1){
                            add = 1;  // Number will be added to binary representation
                            fraction -= 1;
                        }
                        else{
                        add = 0;  // Number will be added to binary representation
                        }
                    binaryFrac += add;
                    }
                    // Calculate Mantissa and Exponent
                    if (intInput == 0  ){
                        mant = binaryFrac.substring(binaryFrac.indexOf("1")+1);
                        exp= Integer.toBinaryString(127-(binaryFrac.indexOf("1")+1));
                    }
                    else if (intInput == 1){
                        mant = binaryFrac;
                        exp = Integer.toBinaryString(127);
                    }
                    else{
                        binaryInt = Integer.toBinaryString(intInput);
                        mant = binaryInt.substring(1)+binaryFrac;
                        exp = Integer.toBinaryString(binaryInt.length()-1+127);
                    }
                    // Adding zeroes to exponent and mantissa
                    while (exp.length()<8){
                        exp = 0 +exp;
                    }
                    while ( mant.length()<23){
                       mant = mant + 0;
                    }
                    // Result
                    result.setText("    "+sign+"   | "+exp+" | "+mant.substring(0,23));
                }}});
        // Reset button listner
        reset.setOnAction(e->{
            // Reset the program
            Input.setText("");
            result.setText("    0   | 00000000 | 00000000000000000000000");
            btnNeg.getButton().setDisable(false);
            btnDot.getButton().setDisable(false);
        });
        primaryStage.setResizable(false);
        primaryStage.setTitle("Floating Point");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}