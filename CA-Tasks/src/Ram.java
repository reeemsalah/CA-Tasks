
public class Ram {
    private String [] dataMemory;
    private boolean memRead;
    private boolean memWrite;
    
    public Ram ()
    {
    	dataMemory=new String[1024];
    	memRead=false;
    	memWrite=false;
    }
    public void writeIntoMemory(int memoryAddress,String data)
    {
    	dataMemory[memoryAddress]=data;
    	memWrite=true;
    	System.out.println("Memory Write:"+memWrite);
    	memWrite=false;
    }
    public String readFromMemory(int memoryAddress)
    {
    	memRead=true;
    	System.out.println("Memory Read:"+memRead);
    	memRead=false;
    	return dataMemory[memoryAddress];
    	
    }
    
    
    public static void main(String[] args) {
		Ram r1=new Ram();
		r1.writeIntoMemory(10, "10001");
		String data=r1.readFromMemory(10);
		System.out.println("Data:"+data);
	}
}
