
// Author : Rituparna Saha

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

 public class image_inversion implements PlugInFilter {
	ImagePlus im;

 public int setup (String arg, ImagePlus im) {

 this.im=im;
 return DOES_8G; // this plugin accepts 8-bit grayscale images
 }

 public void run (ImageProcessor ip) {

 int w = ip.getWidth();
 int h = ip.getHeight();


ImageProcessor copy=ip.duplicate();
ImageProcessor ip1=ip.duplicate();
ImagePlus outputImp = NewImage.createByteImage("Image Inversion", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

 // iterate over all image coordinates
 for (int u = 0; u < w; u++) {
 for (int v = 0; v < h; v++) {
 int p = ip.getPixel(u, v);
 ip1.putPixel(u, v, 255-p); // invert
//im.updateAndDraw();
//im.show();
 }
 }

	byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
 }

 }