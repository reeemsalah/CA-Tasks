
public class Test {

	public static void main(String[] args) {
		char c1='0';
		char c2='1';
		char c=(char)(48-(c1|c2));
		System.out.println((char)(c1|c2));
		System.out.println(c2);

		
		System.out.println(c);
	}

}
