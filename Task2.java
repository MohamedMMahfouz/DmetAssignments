import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Task2 {
		
	public static BufferedImage MedianFilter(BufferedImage input , int k){
		int nrows= input.getWidth();
		int ncols = input.getHeight();
		BufferedImage img= new BufferedImage(nrows, ncols,
                BufferedImage.TYPE_3BYTE_BGR);
				int r;
	        
	        for (int i=0; i<nrows; i++)
	            for (int j=0; j<ncols; j++){
	            	int  m, n, count, t[], tmp;
	            	t = new int[k*k];
	                for (m=i-(k/2), count=0; m<=i+(k/2); m++)
	                  for (n=j-(k/2); n<=j+(k/2); n++)
	                    if (m>=0 && m<nrows && n>=0 && n<ncols){
	                    	Color c= new Color(input.getRGB(m,n));
	        				r=c.getRed();
	                    	t[count++]=r;
	                    }
	                    	
	                for (m=0; m<count-1; m++)
	                    for (n=m+1; n<count; n++)
	                      if (t[m] < t[n]) {
	                        tmp = t[m];
	                        t[m] = t[n];
	                        t[n] = tmp;
	                      } 
	                Color col = new Color(t[count/2], t[count/2], t[count/2]);
	              
	                img.setRGB(i, j, col.getRGB());
	              
	              }
	        
	        return img;
	        
	}
	            
	        
	        
	            
	        
	
	
	public static void main(String[] args) throws IOException {
		BufferedImage image= ImageIO.read(new File("butterfly_22476.png"));
		BufferedImage img= MedianFilter(image, 3);
		ImageIO.write(img, "jpg", new File("Median3.jpg"));
		
		
		BufferedImage img2= MedianFilter(image, 7);
		ImageIO.write(img2, "jpg", new File("Median7.jpg"));
		
		
	}
	
}
