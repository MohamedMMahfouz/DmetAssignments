
public class btates {

	//50 to 250
	//kol value div by 16
	//quantized value then change every pixel to binary
	String [][] jailan = new String[8][8];
	String[][] p1=new String[8][8];
	String[][] p2=new String[8][8];
	String[][] p3=new String[8][8];
	String[][] p4=new String[8][8];
	String[][] p5=new String[8][8];
	String[][] p6=new String[8][8];
	String[][] p7=new String[8][8];
	String[][] p8=new String[8][8];

	public final static boolean ZIGZAG_FORWARD = true;
	public final static boolean ZIGZAG_BACKWARD = false;
	
	
	
	public void fill(String[][] x){
		
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				int R= 50+(int)(Math.random()*250+1);
				x[i][j]=R+"";
			}
		}
	}
	
	public void quantize(String [][] x){
		
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				int s = Integer.parseInt(x[i][j]);
				int r = s/16;
				x[i][j]=r+"";
			}
		}
	}
	
	public void toBinary(String[][] x){
		
		 int result = 0;
		 for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				
		 int Number =Integer.parseInt(x[i][j]);
		 while(Number > 0){


		        int mod = Number % 2;
		        result = result * 1 + mod;

		        Number /= 2;
		    }
		 String s = ""+Number;
		
		if(s.length()<8){
			int needed= 8-s.length();
			for (int k = 0; k < needed; k++) {
				s="0"+s;
			}
		}
		 x[i][j]=s;
			}
			}  
	}
	
	public void plans(String[][]x){
		int pos=0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p1[i][j]=c+"";
				
			}
		}
		
		pos=1;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p2[i][j]=c+"";
				
			}
		}
		
		pos=2;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p3[i][j]=c+"";
				
			}
		}
		
		pos=3;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p4[i][j]=c+"";
				
			}
		}
	
		pos=4;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p5[i][j]=c+"";
				
			}
		}
		
		
		pos=5;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p6[i][j]=c+"";
				
			}
		}
		
		pos=6;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p7[i][j]=c+"";
				
			}
		}
		
		
		pos=7;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				char c= x[i][j].charAt(pos);
				p8[i][j]=c+"";
				
			}
		}
	}
	
	 public static void main(String[] args) {
		 int a[]=new int[10]; 
		 int i; 
		 for(i=0;i<=10;i++)
		 { a[i]=0; }


	}
}
