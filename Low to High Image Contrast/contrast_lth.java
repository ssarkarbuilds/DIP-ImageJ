
// Author : Rituparana Saha

import ij.*;
 import ij.plugin.filter.PlugInFilter;
 import ij.process.*;
 import java.util.Arrays;
 import java.lang.*;
 import java.awt.*; 
 import ij.gui.*;
public class contrast_lth implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		int w=ip.getWidth();
                        int h=ip.getHeight();
                       int alow=0;int ahigh=255;
              double r=0f;int s=255;
                     ImageProcessor copy = ip.duplicate();
                   ImageProcessor ip1=ip.duplicate();
        ImagePlus outputImp = NewImage.createByteImage("high contrast", w, h, 1,              NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();
                         for(int u=0;u<w;u++)
                              {
                                  for(int v=0;v<h;v++){
                                r=ip.getPixel(u,v);
                                if(r<125){
                                r=alow;
                                       }
                                else{
                                   r=ahigh;
                                   }
                            
                                  s=(int)((r-alow)*((255)/(ahigh-alow)));
                                 ip1.putPixel(u,v,s); 
	                      }//end of loop

                     }//end of loop
               byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
    }//end of run
}
