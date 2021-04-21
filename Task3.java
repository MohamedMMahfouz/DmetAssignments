import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class task3 {

	public static int[] Histogram(BufferedImage input){
		int width= input.getWidth();
		int height = input.getHeight();
		BufferedImage img= new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
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
	public static void main(String[] args) throws IOException {
		BufferedImage image= ImageIO.read(new File("greyimage.png"));
		int[] histogram= Histogram(image);
		for (int i = 0; i < histogram.length; i++) {
			System.out.println(histogram[i]);
		}
		
	}
}


