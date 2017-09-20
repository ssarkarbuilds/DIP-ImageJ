

// Author : Subham Sarkar

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.Blitter;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;

public class Sobel_Plugin implements PlugInFilter {
    static float[] sobelX = {
    		-1, 0, 1,
            -2, 0, 2,
            -1, 0, 1 };
    static float[] sobelY = {
			-1, -2, -1,
			 0,  0,  0,
			 1,  2,  1 };

    public int setup(String arg, ImagePlus I) {
        return DOES_ALL;
    }

    public void run(ImageProcessor ip) {
        FloatProcessor Ix = (FloatProcessor) ip.convertToFloat();
        FloatProcessor Iy = (FloatProcessor) Ix.duplicate();
        Ix.convolve(sobelX, 3, 3);
        Iy.convolve(sobelY, 3, 3);
        
        (new ImagePlus("Ix", Ix)).show();
        (new ImagePlus("Iy", Iy)).show();
 
        
        FloatProcessor Ix2 = (FloatProcessor) Ix.duplicate(); Ix2.sqr();
        FloatProcessor Iy2 = (FloatProcessor) Iy.duplicate(); Iy2.sqr();
        
        FloatProcessor E = (FloatProcessor) Ix2.duplicate();
        E.copyBits(Iy2, 0, 0, Blitter.ADD);
        E.sqrt();
        
      
       
        (new ImagePlus("Sobel Edge Detection", E)).show();
      
    }
    

   
   

}