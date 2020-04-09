
public class ProgramExecution {
	private int pc;
	private RegisterFile registerFile;
	private String[] instMem;
	private String[] dataMem;

	public ProgramExecution() {
		registerFile = new RegisterFile();
		instMem = new String[6];
		//add $1,$2,$3
		instMem[0]="00000000010000110000100000100000";
		//sub $4,$5,$6
		instMem[1]="00000000101001100010000000100010";
		//lw $7,(0)$8
		instMem[2]="10001100111010000000000000000000";
		//sw $9,(0)$10
		instMem[3]="10101101001010100000000000000000";
		//beq $5,$4,16
		instMem[4]="00010000100001010000000000010000";
		//J 18
		instMem[5]="00001000000000000000000000010010";
	

		dataMem = new String[] {};
	}

	public void startExec() {
		System.out.println("Loading Program....");
		pc = 0;
		while (pc < instMem.length) {
			fetch(instMem[pc]);
			System.out.println("PC:  "+getPC());
			
		}
		System.out.println("Program Finished Execution...........");
	}

	public void fetch(String s) {
		decode(s);
	}

	public void decode(String s) {
		String opCode = s.substring(0, 6);
		int opCodeInt = binToDec(opCode);
		if (opCodeInt == 0)
		// R Type instruction
		{
			System.out.println("Executing R type.....");
			registerFile.setWrite(true);
			decodeR(s);

			pc++;
		}
		if (opCodeInt == 35) {
			// LW instruction
			System.out.println("Executing LW .....");
			registerFile.setWrite(true);
			decodeLWSW(s);
			pc++;
		}
		if (opCodeInt == 43) {
			// SW instruction
			System.out.println("Executing SW.....");
			registerFile.setWrite(false);
			decodeLWSW(s);
			pc++;
		}
		if (opCodeInt == 4)
		// BEQ instruction
		{
			System.out.println("Executing BEQ.....");
			registerFile.setWrite(false);
			decodeBEQ(s);

		}
		if (opCodeInt == 2)
		// J instruction
		{
			System.out.println("Executing J.....");
			registerFile.setWrite(false);
			decodeJ(s);

		}
	}

	private void decodeJ(String s) {

		String imm = s.substring(6);
		pc  ++;
	}

	private void decodeBEQ(String s) {

		int rs = binToDec(s.substring(6, 11));
		int rt = binToDec(s.substring(11, 16));
		String[] req = registerFile.readTwo(rs, rt);
		String imm = s.substring(16);
		if (req[0] == req[1]) {
			System.out.println("Branching.....");
			//pc = binToDec(signExtend(imm));
			pc++;
		} else {
			System.out.println("Not Branching.....");
			pc++;
		}
	}

	public String signExtend(String s) {
		String sign = s.substring(0, 1);

		while (s.length() != 32)
			s = sign + s;
		return s;
	}

	private void decodeLWSW(String s) {
		int rs = binToDec(s.substring(6, 11));
		int rt = binToDec(s.substring(11, 16));
		int imm = binToDec(s.substring(16));
		registerFile.readTwo(rs, rt);
	}

	private void decodeR(String s) {
		int funct = binToDec(s.substring(26));
		System.out.println("funct code:" + funct);
		int rs = binToDec(s.substring(6, 11));
		int rt = binToDec(s.substring(11, 16));
		int rd = binToDec(s.substring(16, 21));
		String[] req = registerFile.readTwo(rs, rt);
		String res = "";
		if (funct == 34) {
			System.out.println("Executing SUB.....");
			int tmp = binToDec(req[0]) - binToDec(req[1]);
			res = decToBin(tmp);

		}
		if (funct == 32) {
			System.out.println("Executing ADD.....");
			int tmp = binToDec(req[0]) + binToDec(req[1]);
			res = decToBin(tmp);
		}
		registerFile.write(rd, res);

	}

	public static int binToDec(String s) {
		int res = 0;

		int power = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {

			res += Integer.parseInt(s.substring(i, i + 1)) * (int) (Math.pow(2, power));
			power--;
		}
		return res;
	}

	public static String decToBin(int n) {
		String sign="";
		if(n<0)
			sign="1";
		else
			sign="0";
			
		String res = "";
		while (n != 0) {
			int d = n % 2;
			n = n / 2;
			res = d + res;

		}
		return sign+ res;
	}
	public String getPC()
	{
		String res=decToBin(pc);
		String sign=""+res.charAt(0);
		while(res.length()!=32)
			res=sign+res;
		return res;
	}

	public static void main(String[] args) {

		ProgramExecution test = new ProgramExecution();
		test.startExec();
	}

}
