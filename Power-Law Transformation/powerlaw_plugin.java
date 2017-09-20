
// Author : Rituparna Saha

import ij.process.*;
 import java.util.Arrays;
 import java.lang.*;
 import java.awt.*; 
 import ij.gui.*;
 import ij.*;
import ij.plugin.filter.PlugInFilter;

public class powerlaw_plugin implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
 
		int w=ip.getWidth();
                        int h=ip.getHeight();
                         double r=0f;
                        int s=255;
                          double r1=1f;
                        double g=0.7;
                     ImageProcessor copy = ip.duplicate();
                     ImageProcessor ip1=ip.duplicate();
   ImagePlus outputImp = NewImage.createByteImage("power law transformation gamma<1", w, h, 1,   NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();
                         for(int u=0;u<w;u++)
                              {
                                  for(int v=0;v<h;v++){
                                r=ip.getPixel(u,v);
                               /* for(int i=1;i<=g;r++){
                                       r1=(r1*r);
                                      }*/
                                   r=
                         s=(int)(2*(Math.pow(r,g)));
                         ip1.putPixel(u,v,s);         

	             }//end of loop1

                }//end of loop2
         byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
   }//end of run
}
