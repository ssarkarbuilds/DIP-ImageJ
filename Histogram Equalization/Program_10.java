
// Author : Riyanka Kundu

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
public class Program_10 implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		int w=ip.getWidth();
		int h=ip.getHeight();
		int M=w*h;	//total number of image pixels
		int K=256;	//number of intensity values

		ImageProcessor copy=ip.duplicate();
		ImageProcessor ip1=ip.duplicate();
		ImagePlus outputImp = NewImage.createByteImage	("Histogram Equalisation", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp =outputImp.getProcessor();
		//compute the cumulative histogram
		int[] H=ip.getHistogram();
		for (int j = 1; j < H.length; j++){
		H[j]=H[j-1]+H[j];
		}

		//equalize the image
		for (int v = 0; v < h; v++) {
 			for (int u = 0; u < w; u++) {
				int a=ip.get(u,v);
				int b=H[a]*(K-1)/M;
				ip1.set(u,v,b);
			}
		}
		byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
		IJ.wait(500);
		outputIp.setPixels(pixels);// update the new image buffer
		outputImp.show ();// Opens a new window to display the output image
	}

}
