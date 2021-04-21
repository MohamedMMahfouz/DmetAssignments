import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Task3 {
	
	public static void main(String[] args) {
		float[] matrix = {
		        0.111f, 0.111f, 0.111f, 
		        0.111f, 0.111f, 0.111f, 
		        0.111f, 0.111f, 0.111f, 
		    };

		    BufferedImageOp op = new ConvolveOp( new Kernel(3, 3, matrix) );
		    try {
				BufferedImage image= ImageIO.read(new File("butterfly_22476.png"));
				BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), image.getType()) ;
				//blurredImage =
				op.filter(image, img);
				ImageIO.write(img, "jpg", new File("Gauss3.jpg"));
				
				//7*7
				float[] matrix2 = {
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				        0.0204f, 0.0204f, 0.0204f,0.0204f, 0.0204f, 0.0204f,0.0204f,
				    };
				BufferedImageOp op2 = new ConvolveOp( new Kernel(7, 7, matrix2) );
				BufferedImage img2 = new BufferedImage(image.getWidth(), image.getHeight(), image.getType()) ;
				//blurredImage =
				op2.filter(image, img2);
				ImageIO.write(img2, "jpg", new File("Gauss7.jpg"));
				
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	
}
