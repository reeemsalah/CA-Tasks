import java.util.Scanner;

public class ALU {

	private static int zero;
	private static String output;

	public ALU() {
		zero =0;
		output = "";
		for (int i = 0; i < 32; i++) {
			output += "0";
		}
		output+="/0";
	}

	/**
	 * 
	 * @return if the output is zero or not
	 */
	public static int isZero() {
		zero = (output.substring(0, 32).equals("0000000000000000000000000000000")) ? 1 : 0;
		return zero;
	}

	public static void setZero() {
		zero = (output.substring(0, 32).equals("0000000000000000000000000000000")) ? 1 : 0;

	}

	/**
	 * 
	 * @return the output of the ALU
	 */
	public String getOutput() {
		return output;
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
		case "0111":
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
			int n1 = binToDec(op1);
			int n2 = binToDec(op2);
			switch (op) {
			case "0000":
				System.out.println("Operation Name: AND");
				and(n1, n2);
				break;
			case "0001":
				System.out.println("Operation Name: OR");
				or(n1, n2);
				break;
			case "0010":
				System.out.println("Operation Name: ADD");
				add(n1, n2);
				break;
			case "0110":
				System.out.println("Operation Name: SUB");
				sub(n1, n2);
				break;
			case "0111":
				System.out.println("Operation Name: SLT");
				slt(n1, n2);
				break;
			case "1100":
				System.out.println("Operation Name: NOR");
				nor(n1, n2);
				break;
			default:
				System.out.println("Invalid Operation Code");

			}
		}
	}

	private static void nor(int op1, int op2) {
		int res1 = ~(op1 | op2);
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	private static void slt(int op1, int op2) {
		int res1 = (op1 < op2) ? 1 : 0;
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	private static void sub(int op1, int op2) {
		int res1 = op1 - op2;
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	private static void add(int op1, int op2) {
		int res1 = op1 + op2;
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	private static void or(int op1, int op2) {
		int res1 = (op1 | op2);
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	private static void and(int op1, int op2) {
		int res1 = (op1 & op2);
		String res2 = decToBin(res1);
		output = res2 + "/" + res1;
		isZero();

	}

	public static int binToDec(String s) {
		int res = 0;
		char sign = s.charAt(1);
		int power = s.length() - 1;
		for (int i = 1; i < s.length(); i++) {

			res += Integer.parseInt(s.substring(i, i + 1)) * (int) (Math.pow(2, power));
			power--;
		}

		return sign == '0' ? res : (res * -1);
	}

	public static String decToBin(int n) {
		String sign = "";
		if (n < 0) {
			sign = "1";
			n=n*-1;
		}
		else
			sign = "0";

		String res = "";
		while (n != 0) {
			int d = n % 2;
			n = n / 2;
			res = d + res;

		}
		
		while(res.length()!=32)
			res="0"+res;
		if(sign=="1") {
			String temp="";
			int c=0;
			int i;
			for(i=31;i>=0;i--)
			{
				temp=res.charAt(31)+""+temp;
				if(res.charAt(i)=='1') 
					c++;
				
				if(c==1) {
					i--;
					break;
				}
			}
			if(c==1)
			{
				while(i>=0)
				{
					if(res.charAt(i)=='1')
						temp="0"+temp;
					else
						temp="1"+temp;
					i--;
				}
			}
			res=temp;
		}
		
		return res ;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("1st Operand: ");
		String tmp1=sc.nextLine();
		System.out.print("2nd Operand: ");
		String tmp2=sc.nextLine();
		System.out.print("Operation: ");
		String op=sc.nextLine();
		String [] op1=tmp1.split("/");
		String [] op2=tmp2.split("/");
		String s1=op1[0];
		String s2=op2[0];
		int n1=Integer.parseInt(op1[1]);
		int n2=Integer.parseInt(op2[1]);
		ALUEvaluator(op, n1, n2);
		System.out.println("1st Operand: "+tmp1);
		System.out.println("2nd Operand: "+tmp2);
		System.out.println("Output: "+output);
		System.out.println("Z-flag Value: "+isZero());
		
		
		
	}

}
