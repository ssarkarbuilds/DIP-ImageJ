
// Author : Subham Sarkar

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
public class Program_5 implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
	int w = ip.getWidth();
	int h = ip.getHeight();
	ImageProcessor copy=ip.duplicate();
	ImageProcessor ip1=ip.duplicate();
	ImagePlus outputImp = NewImage.createByteImage	("Binary Image", w, h, 1, NewImage.FILL_BLACK);
	ImageProcessor outputIp =outputImp.getProcessor();
	int aT=123;
	//iterate over allimage coordinates
	for (int v = 0; v < h; v++) {
		for (int u = 0; u < w; u++) {
			int a = ip.get(u, v);
			if (a <=aT)
				a = 0; // clamp to minimum value
			else
				a=255;
			ip1.set(u, v, a);
		}
	}
	byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
	}

}
