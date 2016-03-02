package oracle;

import java.util.Scanner;

public class stars {
	public void printStars(int rows){
		int columns = 2*rows-1;
		int spaces = (columns-1)/2;
		for(int i=1; i<=rows;i++,spaces--){
			for(int j=1; j<=spaces; j++){
				System.err.print(" ");
			}
			for(int j=1; j<=2*i-1;j++){
				if(j%2 !=0){
					System.err.print("*");
				}else{
					System.err.print(" ");
				}
			}
			if(i<rows)
			System.err.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.err.println("Enter no of rows");
		int rows = sc.nextInt();
		new stars().printStars(rows);
	}
}
