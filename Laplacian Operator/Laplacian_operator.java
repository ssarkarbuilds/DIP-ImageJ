
// Author : Riyanka Kundu

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.Blitter;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;

public class Laplacian_operator implements PlugInFilter {
    static float[] lap_pos = {
    		0, 1, 0,
            1, -4, 1,
            0, 1, 0 };
    static float[] lap_neg = {
		0, -1, 0,
            -1, 4, -1,
            0, -1, 0  };

    public int setup(String arg, ImagePlus I) {
        return DOES_ALL;
    }

    public void run(ImageProcessor ip) {
        FloatProcessor Ix = (FloatProcessor) ip.convertToFloat();
        FloatProcessor Iy = (FloatProcessor) Ix.duplicate();
        Ix.convolve(lap_pos, 3, 3);
        Iy.convolve(lap_neg, 3, 3);
        
        (new ImagePlus("Positive Laplacian", Ix)).show();
        (new ImagePlus("Negative Laplacian", Iy)).show();
    }
    

   
   

}