
// Author : Subham Sarkar

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.Blitter;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.awt.Color;

public class first_derivative implements PlugInFilter {
    static float[] HX = {
    		-1, 0, 1,
            -1, 0, 1,
            -1, 0, 1 };
    static float[] HY = {
			-1, -1, -1,
			 0,  0,  0,
			 1,  1,  1 };

    public int setup(String arg, ImagePlus I) {
        return DOES_8G + PlugInFilter.NO_CHANGES;
    }

    public void run(ImageProcessor ip) {
        FloatProcessor Ix = (FloatProcessor) ip.convertToFloat();
        FloatProcessor Iy = (FloatProcessor) Ix.duplicate();
        Ix.convolve(HX, 3, 3);
        Iy.convolve(HY, 3, 3);

        
        (new ImagePlus("First Derivative Along X", Ix)).show();
        (new ImagePlus("First Derivative Along Y", Iy)).show();
}

}
