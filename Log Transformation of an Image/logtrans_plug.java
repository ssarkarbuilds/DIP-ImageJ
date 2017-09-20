
// Author : Rituparna Saha

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;

 public class logtrans_plug implements PlugInFilter {
	ImagePlus im;

 public int setup (String arg, ImagePlus im) {

 this.im=im;
 return DOES_ALL; // this plugin accepts all images
 }

 public void run (ImageProcessor ip) {

	 int w = ip.getWidth();
 	int h = ip.getHeight();
	
	double r=0f;
	int s=255;

ImageProcessor copy=ip.duplicate();
ImageProcessor ip1=ip.duplicate();
ImagePlus outputImp = NewImage.createByteImage("Log Transformed Image", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

 // iterate over all image coordinates
 	for (int u = 0; u < w; u++) {
		 for (int v = 0; v < h; v++) {
				r=ip.getPixel(u,v);
				 s= (int)(50 * (Math.log(r+1)));
				 ip1.putPixel(u,v,s); // log transform
	
	 }
 	}

byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
 }// end of run()

 }