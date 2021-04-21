import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class task2 {

	
	public static BufferedImage writeImage(BufferedImage input){
		int width= input.getWidth();
		int height = input.getHeight();
		BufferedImage img= new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
		int r,g,b;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Color c= new Color(input.getRGB(col,row));
				r=c.getRed();
				g=c.getGreen();
				b=c.getBlue();
				if(r<25){
					r=0;
					b=0;
					g=0;
				}else{
					if(r<50){
						r=25;
						b=25;
						g=25;
					}else{
						if(r<75){
							r=50;
							b=50;
							g=50;
						}else{
							if(r<100){
								r=75;
								b=75;
								g=75;
							}else{ if(r<125){
								r=100;
								b=100;
								g=100;
							}else{
								if(r<150){
									r=125;
									b=125;
									g=125;
								}else{
									if(r<200){
										r=150;
										b=150;
										g=150;
									}else{
										r=255;
										b=255;
										g=255;
									}
								}
							}
							
							}
						}
					}
						
				}
				
				int pixel = r<<16 | g<< 8 | b;
				img.setRGB(col, row, pixel);
			}
		}
		return img;
	}
	public static void main(String[] args) throws IOException {
		BufferedImage image= ImageIO.read(new File("greyimage.png"));
		BufferedImage img= writeImage(image);
		ImageIO.write(img, "jpg", new File("new.jpg"));
	}
}
