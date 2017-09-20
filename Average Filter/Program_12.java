
// Author: Riyanka Kundu

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
public class Program_12 implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor orig) {
		int w = orig.getWidth();
		int h = orig.getHeight();
		ImageProcessor copy = orig.duplicate();

		ImageProcessor ip1=orig.duplicate();
		ImagePlus outputImp = NewImage.createByteImage	("Average Filtered Image", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp =outputImp.getProcessor();
		for (int v = 1; v <= h-2; v++) {
			for (int u = 1; u <= w-2; u++) {
				//compute filter result for position (u, v)
				int sum = 0;
				for (int j = -1; j <= 1; j++) {
					for (int i = -1; i <= 1; i++) {
						int p = copy.getPixel(u+i, v+j);
						sum = sum + p;
					}
				}
				int q = (int) Math.round(sum/9.0);
				ip1.putPixel(u, v, q);
			}
		}
		byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
		IJ.wait(500);
		outputIp.setPixels(pixels);// update the new image buffer
		outputImp.show ();// Opens a new window to display the output image
	}

}
