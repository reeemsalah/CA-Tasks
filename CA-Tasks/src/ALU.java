
public class ALU {

	private static  boolean zero;
	private static char []output;

	public ALU() {
		zero = true;
		output=new char[32];
		for(int i=0;i<32;i++)
		{
			output[i]='0';
		}
	}

	/**
	 * 
	 * @return if the output is zero or not
	 */
	public static boolean isZero() {
		zero = (output.equals("0000000000000000000000000000000")) ? true : false;
		return output.equals("0000000000000000000000000000000");
	}
	public static void setZero()
	{
		zero = (output.equals("0000000000000000000000000000000")) ? true : false;
	
	}

	/**
	 * 
	 * @return the output of the ALU
	 */
	public String getOutput() {
		String res="";
		for(int i=0;i<output.length;i++)
		{
			res+=output[i]+"";
		}
		return res;
	}

	/**
	 * 
	 * @param Op       the operation code AND 0000 Output = A & B (Bitwise AND); OR
	 *                 0001 Output = A | B (Bitwise OR); add 0010 Output = A + B;
	 *                 sub 0110 Output = A - B; slt 0111 Output = (A <B)? 1 : 0; NOR
	 *                 1100 Output = (A | B) (Bitwise NOR);
	 * 
	 * @param Operand1 integer as operand 1
	 * @param Operand2 integer as operand 2
	 */

	public static void ALUEvaluator(String op, int op1, int op2) {
		switch (op) {
		case "0000":
			and(op1, op2);
			break;
		case "0001":
			or(op1, op2);
			break;
		case "0010":
			add(op1, op2);
			break;
		case "0110":
			sub(op1, op2);
			break;
		case "1110":
			slt(op1, op2);
			break;
		case "1100":
			nor(op1, op2);
			break;
		default:
			System.out.println("Invalid Operation Code");

		}
	}

	/**
	 * 
	 * @param Op       the operation code AND 0000 Output = A & B (Bitwise AND); OR
	 *                 0001 Output = A | B (Bitwise OR); add 0010 Output = A + B;
	 *                 sub 0110 Output = A - B; slt 0111 Output = (A <B)? 1 : 0; NOR
	 *                 1100 Output = (A | B) (Bitwise NOR);
	 * @param Operand1 String as operand 1
	 * @param Operand2 String as operand 2
	 */

	public static void ALUEvaluator(String op, String op1, String op2) {
		if (op1.length() != 32 && op2.length() != 32) {
			System.out.println("Inavlid Inputs");
		} else {
			switch (op) {
			case "0000":
				and(op1, op2);
				break;
			case "0001":
				or(op1, op2);
				break;
			case "0010":
				add(op1, op2);
				break;
			case "0110":
				sub(op1, op2);
				break;
			case "1110":
				slt(op1, op2);
				break;
			case "1100":
				nor(op1, op2);
				break;
			default:
				System.out.println("Invalid Operation Code");

			}
		}
	}

	private static void nor(String op1, String op2) {
		for(int i=0;i<op1.length()&&i<op2.length();i++)
		{
			char c1=op1.charAt(i);
			char c2=op2.charAt(i);
			char c=(char)(1-(c1|c2));
			output[i]=c;
			
		}
	}

	private static void slt(String op1, String op2) {

	}

	private static void sub(String op1, String op2) {

	}

	private static void add(String op1, String op2) {

	}

	private static void or(String op1, String op2) {

	}

	private static void and(String op1, String op2) {

	}

	private static void nor(int op1, int op2) {

	}

	private static void slt(int op1, int op2) {

	}

	private static void sub(int op1, int op2) {

	}

	private static void add(int op1, int op2) {

	}

	private static void or(int op1, int op2) {

	}

	private static void and(int op1, int op2) {

	}

}
