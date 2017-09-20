
// Author : Rituparna Saha

import ij.*;
 import ij.plugin.filter.PlugInFilter;
 import ij.process.*;
 import java.util.Arrays;
 import java.lang.*;
 import java.awt.*; 
 import ij.gui.*;

 public class Filter_Median3x3 implements PlugInFilter {
 final int K = 4; // filter size
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
  ImagePlus outputImp = NewImage.createByteImage("Median Filter", w, h, 1,   NewImage.FILL_BLACK);
		ImageProcessor outputIp = outputImp.getProcessor();

 // vector to hold pixels from 3×3 neighborhood
 int[] P = new int[2*K+1];

 for (int v = 1; v <= h-2; v++) {
    for (int u = 1; u <= w-2; u++) {
              // fill the pixel vector P for filter position u, v
            int k = 0;
          for (int j = -1; j <= 1; j++) {
               for (int i = -1; i <= 1; i++) {
                          P[k] = copy.getPixel(u+i, v+j);
                                          k++;
                                                          }
                                                  }
               // sort pixel vector and take the center element
                  Arrays.sort(P);
                    ip1.putPixel(u, v, P[K]);
                                                 }
                                             }
        byte [] pixels = (byte []) ip1.getPixels();// get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
    }//end of run

}
