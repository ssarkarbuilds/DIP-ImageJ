
// Author : Subham Sarkar

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.Blitter;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;

public class Prewitt_Plugin implements PlugInFilter {
    static float[] prewittX = {
    		-1, 0, 1,
            -1, 0, 1,
            -1, 0, 1 };
    static float[] prewittY = {
			-1, -1, -1,
			 0,  0,  0,
			 1,  1,  1 };

    public int setup(String arg, ImagePlus I) {
        return DOES_ALL;
    }

    public void run(ImageProcessor ip) {
        FloatProcessor Ix = (FloatProcessor) ip.convertToFloat();
        FloatProcessor Iy = (FloatProcessor) Ix.duplicate();
        Ix.convolve(prewittX, 3, 3);
        Iy.convolve(prewittY, 3, 3);
        
        (new ImagePlus("Ix", Ix)).show();
        (new ImagePlus("Iy", Iy)).show();
 
        
        FloatProcessor Ix2 = (FloatProcessor) Ix.duplicate(); Ix2.sqr();
        FloatProcessor Iy2 = (FloatProcessor) Iy.duplicate(); Iy2.sqr();
        
        FloatProcessor E = (FloatProcessor) Ix2.duplicate();
        E.copyBits(Iy2, 0, 0, Blitter.ADD);
        E.sqrt();
        
      
       
        (new ImagePlus("Prewitt Edge Detection", E)).show();
      
    }
    

   
   

}