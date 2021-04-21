import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Task1 {


	public static BufferedImage MeanFilter(BufferedImage input , int k){
		int nrows= input.getWidth();
		int ncols = input.getHeight();
		BufferedImage img= new BufferedImage(nrows, ncols,
                BufferedImage.TYPE_3BYTE_BGR);
				int r;
	        
	        for (int i=0; i<nrows; i++)
	            for (int j=0; j<ncols; j++){
	            	int  m, n, count, t[];
	            	t = new int[k*k];
	                for (m=i-(k/2), count=0; m<=i+(k/2); m++)
	                  for (n=j-(k/2); n<=j+(k/2); n++)
	                    if (m>=0 && m<nrows && n>=0 && n<ncols){
	                    	Color c= new Color(input.getRGB(m,n));
	        				r=c.getRed();
	                    	t[count++]=r;
	                    }
	                 int sum=0;   	
	                for (int z=0; z<t.length; z++)
	                	sum+=t[z];
	                int avg=sum/count;
	                Color col = new Color(avg, avg, avg);
	                img.setRGB(i, j, col.getRGB());
	              
	              }
	        
	        return img;
	        
	}
	     
	public static void main(String[] args) throws IOException {
		BufferedImage image= ImageIO.read(new File("butterfly_22476.png"));
		BufferedImage img= MeanFilter(image,3);
		ImageIO.write(img, "jpg", new File("Mean3.jpg"));
		
		BufferedImage img2= MeanFilter(image,7);
		ImageIO.write(img2, "jpg", new File("Mean7.jpg"));
	}
}
