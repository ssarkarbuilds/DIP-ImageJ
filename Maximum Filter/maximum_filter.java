
// Author : Riyanka Kundu

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.util.Arrays;

public class maximum_filter implements PlugInFilter {
	ImagePlus imp;
	final int K=9; //filter size
	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor orig) {
		int w = orig.getWidth();
		int h = orig.getHeight();
		ImageProcessor copy = orig.duplicate();
		//ImageProcessor copy=ip.duplicate();
	ImageProcessor ip1=orig.duplicate();
	ImagePlus outputImp = NewImage.createByteImage("Maximum Filter Image", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

		int[ ] P=new int[K];

		for (int v = 1; v <= h-2; v++) {
			for (int u = 1; u <= w-2; u++) {
				//compute filter result for position (u, v)
				int k = 0;
				for (int j = -1; j <= 1; j++) {
					for (int i = -1; i <= 1; i++) {
						P[k] = copy.getPixel(u+i, v+j);
						k++;
					}
				}
				int max=P[0];
				for(int i=1;i<P.length;i++)
				{
					if(P[i]>max)
					{
						max=P[i];
					}
				}
				ip1.putPixel(u, v, max);
			}
		}
		
			byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image	

}

}
