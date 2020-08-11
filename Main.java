import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math; 
// author: Parmin Patel


class Main {
  public static void main(String[] args) {
    Scanner test_file = null;
    Scanner training_file = null;
    try {
      test_file = new Scanner(new File("test.txt"));
      training_file = new Scanner (new File("training.txt"));
    } catch (Exception e){
      System.out.println("File did not open.");
      System.exit(-1);

    } //This declares the weight 
      double w1 = Math.random() * (1 - 0 + 1) + 0;
      double w2 = Math.random() * (1 - 0 + 1) + 0;
      double w3 = Math.random() * (1 - 0 + 1) + 0;
      double threshold = 200;
      double learning_rate = 0.000001;

      // creates the array for the RGB values in the training data
      int [] training_r = new int [1000];
      int [] training_g = new int [1000];
      int [] training_b = new int [1000];
      int [] training_o = new int [1000];

//allocates all of the training values into arrays based on the value type 
      for(int j = 0; j < 1000; j++){
        training_r[j] = training_file.nextInt();
        training_g[j] = training_file.nextInt();
        training_b[j] = training_file.nextInt();
        training_o[j] = training_file.nextInt();
        training_file.nextLine();
      }

      //trains the perceptron using the training data and calculates error
      int train_score = 0;
      double times=0;
      double percent=0;
      //Runs until the accuracy percent is greater than 99.999
      while(percent<99.999){
        times= (times+1000);
        percent= (train_score / times)*100; 
   
        for( int i = 0; i < 1000; i++){
          int output = 0;
          double error = 0;
          
          double answer = (training_r[i] * w1) + (training_g[i] * w2) + (training_b[i] * w3) ;
          
          if (answer > threshold){
            output = 1;
          } 
          else{
            output = -1;
          }
          error = training_o[i] - output;
          if (error == 0){
            train_score = train_score + 1;
            //System.out.println (" Error: " + error);
            //System.out.println("w1 " + w1);
            //System.out.println("w2 " + w2);
            //System.out.println("w3 " + w3);
          }
          else {
            //System.out.println("error " + error);
            w1 = w1 + (error * learning_rate * training_r[i]);
            w2 = w2 + (error * learning_rate * training_g[i]);
            w3 = w3 + (error * learning_rate * training_b[i]);

            threshold = threshold + error*learning_rate*1;

            
          }
         
        }
        
      }
       training_file.close();
  

  
      int test_score= 0;
      int [] test_r = new int [100];
      int [] test_g = new int [100];
      int [] test_b = new int [100];
      int [] test_o = new int [100];

//allocates all of the training values into arrays based on the value type 
       for(int q = 0; q < 100; q++){
        test_r[q] = test_file.nextInt();
        test_g[q] = test_file.nextInt();
        test_b[q] = test_file.nextInt();
        test_o[q] = test_file.nextInt();
        test_file.nextLine();
       }

       //tests the perceptron
       double black = 0;
       double white=0;
       double actually_black=0;
       double actually_white=0;
       int test_output = 0;
       for (int y = 0; y < 100; y++){
        double test_answer = (test_r[y] * w1) + (test_g[y] * w2 + (test_b[y] * w3)) ;
        if (test_answer < threshold){
          test_output = -1;
          white ++;
          if (test_output!=test_o[y]){
           actually_black++;
          } 

        }
        else {
          
          test_output = 1;
          black ++;
          if(test_output!=test_o[y]){
            actually_white++;
            
          }
        }
        if(test_output == test_o[y]){
          test_score = test_score + 1;
        }
       
       } 
       //checks how many white and black outputs there are 
       int white_counter = 0;
       int black_counter = 0;
       for(int i =0; i< 100; i++){
         if(test_o[i] == -1){
           white_counter++;
         }
         if(test_o[i] == 1){
           black_counter++;
         }
       }
      
      //prints out the Confusion Matrix
      System.out.println("");
      System.out.print("                   ");
      System.out.println("CONFUSION MATRIX");
      System.out.print("  ");
      System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
      System.out.println("Guessed Better on White and is actually White: " + white );
      System.out.println("Guessed Better on Black and is actually Black: " + black);

      System.out.println("Guessed Better on White and is actually Black: " + actually_black);
      System.out.println("Guessed Better on Black and is actually White: " + actually_white);
      double total = white + black;
      System.out.println("Total: " + total);

      
      
//prints out the statistics 

      System.out.println("");
      System.out.print("                           ");
      System.out.println("STATISTICS");
      System.out.print("  ");
      System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
      System.out.println("Percentage of guesses for which the perception was correct (Accuracy): "  + total + "%");
      System.out.println("Precision for white - percentage of the perceptron's white guesses that were actually white: " + (Math.round(white/white_counter*100)) + "%");
      System.out.println("Precision for black - percentage of the perceptron's black guesses that were actually black: " + (Math.round(black/black_counter*100)) + "%");
      System.out.println ("Recall for Black - percentage of the total number of black test cases which the perceptron guessed as black: " + (100-actually_black) + "%");
      System.out.println ("Recall for White - percentage of the total number of white test cases which the perceptron guessed as white: " + (100-actually_white) + "%");
      

  //closes the test file
    test_file.close();
        
  }
}

  


