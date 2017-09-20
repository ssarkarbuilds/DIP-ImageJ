

// Author : Riyanka Kundu

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
public class Unsharp_mask implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		
		unsharpMask(ip, 10, 1.0); 
	}
	

	float[] makeGaussKernel1d(double sigma) {

		// create the kernel
		int center = (int) (3.0*sigma);
		float[] kernel = new float[2*center+1]; // odd size

		// fill the kernel
		double sigma2 = sigma * sigma; // ?2
		for (int i=0; i<kernel.length; i++) {
			double r = center - i;
			kernel[i] = (float) Math.exp(-0.5 * (r*r) / sigma2);
		}
	
		return kernel;
	}
	public void unsharpMask(ImageProcessor ip, double sigma, double a) {
			
			int w = ip.getWidth();
			int h = ip.getHeight();
			
			ImageProcessor ip1=ip.duplicate();
			ImagePlus outputImp = NewImage.createByteImage	("Unsharp Mask(Sigma=10)", w, h, 1, NewImage.FILL_BLACK);
			ImageProcessor outputIp =outputImp.getProcessor();
			ImageProcessor I = ip.convertToFloat(); // I
	
			// create a blurred version of the image
			ImageProcessor J = I.duplicate(); // ˜I
			float[] H = makeGaussKernel1d(sigma);
			Convolver cv = new Convolver();
			cv.setNormalize(true);
			cv.convolve(J, H, 1, H.length);
			cv.convolve(J, H, H.length, 1);
		
			I.multiply(1+a); // I ? (1 + a) · I
			J.multiply(a); // ˜I ? a · ˜I
			I.copyBits(J,0,0,Blitter.SUBTRACT); // ˜I ? (1 + a) · I ? a · ˜I
	
			//copy result back into original byte image
			ip1.insert(I.convertToByte(false), 0, 0);
			byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
			IJ.wait(500);
			outputIp.setPixels(pixels);// update the new image buffer
			outputImp.show ();// Opens a new window to display the output image
		}

}
