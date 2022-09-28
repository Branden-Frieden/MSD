import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        int[] array = {1,2,3,4,5,6,7,8,9,10};

        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }

        System.out.println("please input your name");
        Scanner sc1 = new Scanner(System.in);
        String userName = sc1.nextLine();

        System.out.println("please input your age");
        Scanner sc2 = new Scanner(System.in);
        int userAge = sc2.nextInt();

        if(userAge >= 18){
            System.out.println("Congrats! you are old enough to vote ");
        }
        else{
            System.out.println("You are not old enough to vote ");
        }

        // find the users generation
        if(userAge > 80){
            System.out.println("you are part of the greatest generation");
        }
        else if(userAge > 60){
            System.out.println("you are a baby boomer");
        }
        else if(userAge > 40){
            System.out.println("you are a part of generation X");
        }
        else if(userAge > 20){
            System.out.println("you ara a millennial");
        }
        else{
            System.out.println("you are an ikid");
        }
    }
}