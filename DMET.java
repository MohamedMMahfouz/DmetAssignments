import java.awt.Color;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class DMET {

	public static int[] Histogram(BufferedImage input){
		int width= input.getWidth();
		int height = input.getHeight();
		
		int r;
		int[] h= new int[256];
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Color c= new Color(input.getRGB(col,row));
				r=c.getRed();
				h[r]+=1;
				
			}
		}
				return h;
	}
	
	public static int otsu(int[] histogram , int total) {
	    int sum = 0;
	    for (int i = 0; i < histogram.length; ++i) 
	        sum += i * histogram[i];
	    int sumB = 0;
	    int wB = 0;
	    int wF = 0;
	    int mB;
	    int mF;
	    double max = 0.0;
	    double between = 0.0;
	    double threshold1 = 0.0;
	    double threshold2 = 0.0;
	    for (int i = 0; i < histogram.length; ++i) {
	        wB += histogram[i];
	        if (wB == 0)
	            continue;
	        wF = total - wB;
	        if (wF == 0)
	            break;
	        sumB += i * histogram[i];
	        mB = sumB / wB;
	        mF = (sum - sumB) / wF;
	        between = wB * wF * (mB - mF) * (mB - mF);
	        if ( between >= max ) {
	            threshold1 = i;
	            if ( between > max ) {
	                threshold2 = i;
	            }
	            max = between;            
	        }
	    }
	    return (int)(( threshold1 + threshold2 ) / 2);
	}
	
	public static int GetTotal(BufferedImage input){
		int width= input.getWidth();
		int height = input.getHeight();
		int count=0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				count++;
			}
			}
	return count;
	}
	public static BufferedImage Threshold(BufferedImage input,int T ){
		int width= input.getWidth();
		int height = input.getHeight();
		BufferedImage img= new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
		int r;
		
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) { 
				Color c= new Color(input.getRGB(col,row));
				r=c.getRed();
				if(r<=T){
					r=0;
				}else{
					r=255;
				}
				int pixel = r<<16 | r<< 8 | r;
				img.setRGB(col, row, pixel);
			}
		}
		return img;
		
	}
	
	public static BufferedImage BackGround(BufferedImage input ,BufferedImage I ){
		int width= input.getWidth();
		int height = input.getHeight();
		BufferedImage img= new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
		int r;
		int I_pixel;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) { 
				Color c= new Color(input.getRGB(col,row));
				Color I_c= new Color(I.getRGB(col,row));
				r=c.getRed();
				I_pixel=I_c.getBlue();
				if(r==255){// value of background
					I_pixel=0;
				}
				
				int pixel = I_pixel<<16 | I_pixel<< 8 | I_pixel;
				img.setRGB(col, row, pixel);
			}
		}
		return img;
	}
	public static BufferedImage ForeGround(BufferedImage input ,BufferedImage I ){
		int width= input.getWidth();
		int height = input.getHeight();
		BufferedImage img= new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
		int r;
		int I_pixel;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) { 
				Color c= new Color(input.getRGB(col,row));
				Color I_c= new Color(I.getRGB(col,row));
				r=c.getRed();
				I_pixel=I_c.getBlue();
				if(r==0){// value of background
					I_pixel=0;
				}
				
				int pixel = I_pixel<<16 | I_pixel<< 8 | I_pixel;
				img.setRGB(col, row, pixel);
			}
		}
		return img;
	}
	
	 public static double[] GetEntropy(BufferedImage actualImage){
		 ArrayList<String> values= new ArrayList<String>();
		 double [] r= new double[2];
		             int n = 0;
		             HashMap<Integer, Integer> occ = new HashMap<Integer,Integer>();
		             for(int i=0;i<actualImage.getHeight();i++){
		                 for(int j=0;j<actualImage.getWidth();j++){
		                   int pixel = actualImage.getRGB(j, i);
		                   int d= (int)Math.round(0.2989 * pixel + 0.5870 * pixel + 0.1140 * pixel);
		                  if(!values.contains(String.valueOf(d)))
		                      values.add(String.valueOf(d));
		                  if (occ.containsKey(d)) {
		                      occ.put(d, occ.get(d) + 1);
		                  } else {
		                      occ.put(d, 1);
		                  }
		                  ++n;
		           }
		        }
		        double c=0;
		        double e = 0.0;
		        for (HashMap.Entry<Integer, Integer> entry : occ.entrySet()) {
		        	
		        	double p = (double) entry.getValue() / n;
		             int x=(int) (Math.log(p) / Math.log(2));
		             e += p * x;
		             c++;
		        }
		        r[0]=-e;
		        r[1]=c;
		 return r;
		}
	
	public static void main(String[] args) throws IOException {
		BufferedImage I= ImageIO.read(new File("sat_noisy.gif"));
		int[] histogram= Histogram(I);
		int total = GetTotal(I);
		int Threshold = otsu(histogram, total);
		System.out.println("Threshold value: "+Threshold);
		BufferedImage img= Threshold(I, Threshold);
		ImageIO.write(img, "jpg", new File("Binary_I.jpg"));
		
		BufferedImage img1= ForeGround(img, I);
		ImageIO.write(img1, "jpg", new File("FG_I.jpg"));
		
		BufferedImage img2= BackGround(img, I);
		ImageIO.write(img2, "jpg", new File("BG_I.jpg"));
		double[] e_I_array=GetEntropy(I);
		double e_I= e_I_array[0];
		System.out.println("Entropy of original image: " +e_I);
		
		double[] e_FG_array=GetEntropy(img1);
		double e_FG_I= e_FG_array[0];
		int c_FG = (int)e_FG_array[1]; //number of instances in Foreground image
		System.out.println("Entropy of Foreground image: " +e_FG_I );
		
		double[] e_BG_array=GetEntropy(img2);
		double e_BG_I= e_BG_array[0];
		int c_BG = (int)e_BG_array[1]; //number of instances in Background image
		System.out.println("Entropy of Background image: " +e_BG_I );
		double n=c_FG+c_BG;  //total number of instances
		
		double entropy_before= (c_FG/n)*e_FG_I + (c_BG/n)*e_BG_I ; 
		
		double info_gain= e_I - (entropy_before);
		System.out.println("information gain: "+info_gain);
		
	}
}
