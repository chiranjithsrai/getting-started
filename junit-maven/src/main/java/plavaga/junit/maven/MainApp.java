/**
 * 
 */
package plavaga.junit.maven;

/**
 * @author chiranjithsrai
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations op = new Operations();
		int num1 = 10;
		int num2 = 5;
		int sum = op.add(num1, num2);
		System.out.println("Sum :: "+sum);
		int substracted = op.substract(num1, num2);
		System.out.println("substracted Result :: "+substracted);
	}

}
