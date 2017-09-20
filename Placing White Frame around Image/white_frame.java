
// Author : Subham Sarkar

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

 public class white_frame implements PlugInFilter {

 public int setup (String arg, ImagePlus im) {
 return DOES_ALL; // this plugin accepts 8-bit grayscale images
 }

 public void run (ImageProcessor ip) {
 int w = ip.getWidth(); 
 int h = ip.getHeight();

ImageProcessor copy=ip.duplicate();
ImageProcessor ip1=ip.duplicate();
ImagePlus outputImp = NewImage.createByteImage("Image with white frame", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

for(int u=0;u<=w;u++){
	for(int v=0;v<=h;v++){

	if(v<=10 || v>=h-10)
		ip1.putPixel(u,v,255);
	if(u<=10 || u>=w-10)
		ip1.putPixel(u,v,255);
	
}
}

 	byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
 }
 }
 

 