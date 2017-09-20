

// Author : Subham Sarkar

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;


public class shifting_horizontal implements PlugInFilter {

 
ImagePlus im;
 // new instance variable of this plugin object


 public int setup(String arg, ImagePlus im)
 {
 

 this.im = im; // keep a reference to the image im
 
return DOES_ALL;
 }

 


public void run(ImageProcessor ip) {


int w = ip.getWidth();
 
int h = ip.getHeight();


 int p=255,p1=255;


ImageProcessor copy=ip.duplicate();
ImageProcessor ip1=ip.duplicate();
ImagePlus outputImp = NewImage.createByteImage("Image Inversion", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

int w1=w/2;
 // iterate over all image coordinates



 for (int u = 0; u < w; u++) 
{

 for (int v = 0; v < h; v++) 
{


 
	if(u<=w1){
		p = copy.getPixel(u, v);
 
		ip1.putPixel(u+w1,v,p);
		p1=copy.getPixel(u+w1,v);
		ip1.putPixel(u,v,p1);
		
			}
	if(u>w1) {
		
		// do nothing really ^ _ ^
	}	
 

 }


		 
 }

// end of for loop
	byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image

 } // end of run


} // end of class XY_plugin