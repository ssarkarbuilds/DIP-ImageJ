
// Author : Riyanka Kundu

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
import ij.io.Opener;
public class Program_11 implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp1) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		
		Opener opener = new Opener();  
		ImagePlus imp = opener.openImage("C:/Users/Rinu/Desktop/Input2.jpg");  
		imp.show(); 
		ImageProcessor ipA = imp.getProcessor(); // target image IA (to be modified)
		ImageProcessor ip1=ipA.duplicate();
		int w = ipA.getWidth();
		int h = ipA.getHeight();
		ImagePlus outputImp = NewImage.createByteImage	("Histogram Specification Image", w, h, 1, NewImage.FILL_BLACK);
		ImageProcessor outputIp =outputImp.getProcessor();
		Opener opener1 = new Opener();  
		ImagePlus imp1 = opener1.openImage("C:/Users/Rinu/Desktop/Input.bmp");  
		imp1.show(); 
		ImageProcessor ipR = imp1.getProcessor(); // reference image IR
		int[] hA = ipA.getHistogram(); // get the histogram for IA
		int[] hR = ipR.getHistogram(); // get the histogram for IR
		int[] F = matchHistograms(hA, hR); // mapping function fhs(a)
		ip1.applyTable(F); // apply fhs() to the target image IA
		byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
		IJ.wait(500);
		outputIp.setPixels(pixels);// update the new image buffer
		outputImp.show ();// Opens a new window to display the output image
	}

	int[] matchHistograms (int[] hA, int[] hR) {
		 // hA . . . histogram hA of target image IA
		// hR . . . reference histogram hR
		// returns the mapping function fhs() to be applied to image IA

		int K = hA.length; // hA, hR must be of length K
		double[] PA = Cdf(hA); // get CDF of histogram hA
		double[] PR = Cdf(hR); // get CDF of histogram hR
		int[] F = new int[K]; // pixel mapping function fhs()

		// compute mapping function fhs()
		for (int a = 0; a < K; a++) {
			int j = K-1;
			do {
				F[a] = j;
				j--;
			} while (j>=0 && PA[a]<=PR[j]);
		}
		return F;
	}


	double[] Cdf (int[] h) {
		// returns the cumulative distribution function for histogram h
		int K = h.length;
		int n = 0; // sum all histogram values
		for (int i=0; i<K; i++) {
			n += h[i];
		}

		double[] P = new double[K]; // create cdf table P
		int c = h[0]; // cumulate histogram values
		P[0] = (double) c / n;
		for (int i=1; i<K; i++) {
			c += h[i];
			P[i] = (double) c / n;
		}
		return P;
	}
}
