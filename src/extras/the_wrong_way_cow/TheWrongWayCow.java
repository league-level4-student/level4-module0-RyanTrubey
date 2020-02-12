//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int left = 0;
    	int right = 0;
    	int up = 0;
    	int down = 0;
    	String direction = "";
        
    	for(int i = 0; i < field.length; i++) {
        	for(int j = 0; j < field[0].length-2; j++) {
        		if(field[i][j] == 'c' && field[i][j+1] =='o' && field[i][j+2] == 'w') {
        			left++;
        			if(left >= 2) {
        				direction = "left";
        				break;
        			}
        		}
        		
        		if(field[i][j] == 'w' && field[i][j+1] =='o' && field[i][j+2] == 'c') {
        			right++;
        			if(right >= 2) {
        				direction = "right";
        				break;
        			}
        		}
        	}
        }
    	
    	for(int i = 0; i < field.length-2; i++) {
    		for(int j = 0; j < field[0].length; j++) {
    			if(field[i][j] == 'c' && field[i+1][j] =='o' && field[i+2][j] == 'w') {
        			down++;
        			if(down >= 2) {
        				direction = "down";
        				break;
        			}
        		}
        		
        		if(field[i][j] == 'w' && field[i+1][j] =='o' && field[i+2][j] == 'c') {
        			up++;
        			if(left >= 2) {
        				direction = "up";
        				break;
        			}
        		}
    		}
    	}
        
        for(int i = 0; i < field.length; i++) {
        	for(int j = 0; j < field[0].length-2; j++) {
        		if(field[i][j] == 'c' && field[i][j+1] =='o' && field[i][j+2] == 'w') {
        			if(!direction.equals("left")) {
        				int[] coordinate = {i, j};
        				return coordinate;
        			}
        		}
        		
        		if(field[i][j] == 'w' && field[i][j+1] =='o' && field[i][j+2] == 'c') {
        			if(!direction.equals("right")) {
        				int[] coordinate = {i, j+2};
        				return coordinate;
        			}
        		}
        	}
        }
        
        for(int i = 0; i < field.length-2; i++) {
        	for(int j = 0; j < field[0].length; j++) {
        		if(field[i][j] == 'c' && field[i+1][j] =='o' && field[i+2][j] == 'w') {
        			if(!direction.equals("left")) {
        				int[] coordinate = {i, j};
        				return coordinate;
        			}
        		}
        		
        		if(field[i][j] == 'w' && field[i+1][j] =='o' && field[i+2][j] == 'c') {
        			if(!direction.equals("left")) {
        				int[] coordinate = {i+2, j};
        				return coordinate;
        			}
        		}
        	}
        }
        return null;
    }
}
