
public class Cache {
 private Block [] cache;
 private int hits;
 private int miss;
 public String [] memory;
 public Cache()
 {
	 cache=new Block[8];
	 for (int i = 0; i < cache.length; i++) {
		 cache[i]=new Block();
		
	}
	 hits=0;
	 miss=0;
	 memory =new String[] {"0","100011000","sss","this one","101","11","1","0","new 0","11","10","101"};
 }
  public void findData(int address)
 {
	  int index=address%8;
	  int tag=address/8;
	  Block b=cache[index];
	  boolean found=false;
	  if(b.isValid())
	  {
		  if(b.getTag()==tag)
		  {
			  hits++;
			  System.out.println("Hit, Data="+b.getData());
			  found=true;
		  }
		  else 
			  System.out.println("Miss");
		  
	  }
	  else 
		  System.out.println("Miss");
	  if(!found)
	  {
		  miss++;
		  b.setValid(true);
		  b.setTag(tag);
		  b.setData(memory[address]);
	  }
  
 
}
  public static void main(String[] args) {
	Cache c1=new Cache();
	c1.findData(3);
	c1.findData(3);
	c1.findData(0);
	c1.findData(0);
	c1.findData(8);
	c1.findData(8);
	c1.findData(0);
	
}
}
