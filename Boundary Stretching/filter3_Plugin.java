
// Author : Riyanka Kundu
import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.lang.*;
public class filter3_Plugin implements PlugInFilter {

 
ImagePlus im;
 // new instance variable of this plugin object


 public int setup(String arg, ImagePlus im)
 {
 

 this.im = im; // keep a reference to the image im
 
return DOES_ALL;
 }

 
public void run(ImageProcessor orig) {
	 int M = orig.getWidth();
	 int N = orig.getHeight();
	//ImageProcessor copy=orig.duplicate();
	ImageProcessor ip1=orig.duplicate();
	ImagePlus outputImp = NewImage.createByteImage	("Stretched boundary Image", M, N, 1, NewImage.FILL_BLACK);
	ImageProcessor outputIp =outputImp.getProcessor();
 	// filter matrix of size (2K + 1) × (2L + 1)
	 int[][] filter = {
 			{0,0,1,1,1,0,0},
			{0,1,1,1,1,1,0},
 			{1,1,1,1,1,1,1},
 			{0,1,1,1,1,1,0},
 			{0,0,1,1,1,0,0}
			};
	double s = 1.0/23; // sum of filter coefficients is 23

	 int K = filter[0].length/2;
	 int L = filter.length/2;
	//K=K+10;//L=L+10;

	ImageProcessor copy = orig.duplicate();
	for (int v = 0; v <= N; v++) {
		for (int u = 0; u <= M; u++) {


 	if(( v >= L && v <= (N-L-1))&&( u>=K && u<=(M-K-1))){
		//for (int u = K; u <= M-K-1; u++) 
	 		// compute filter result for position (u, v)
 			int sum = 0;
 			for (int j = -L; j <= L; j++) {
 				for (int i = -K; i <= K; i++) {
					int p = copy.getPixel(u+i, v+j);
 					int c = filter[j+L][i+K];
					sum = sum + c * p;
 				}
 			}
			int q = (int) Math.round(s * sum);
 			if (q < 0) q = 0;
			if (q > 255) q = 255;
 			ip1.putPixel(u, v, q);
			}
	else{
		//K=K+10;//L=L+10;
		//orig.putPixel(u,v,255);
		int p=255;
		
 	if(v >= 0 && v <= L && u>=0 && u<=M)
		{
			 p = copy.getPixel(u,L);
			
			ip1.putPixel(u,v,p);
	         }
	if( ( v >= L && v <= (N-L-1))&& u>=0 && u<=K)
		{
			 p = copy.getPixel(K,v);
			
			ip1.putPixel(u,v,p);
	         }
	if( ( v >= L && v <= (N-L-1))&& u>=M-K-1 && u<=M)
		{
			 p = copy.getPixel(M-K-1,v);
			
			ip1.putPixel(u,v,p);
	         }	
	if( ( v >= N-L-1 && v <= N)&& u>=0 && u<=M)
		{
			 p = copy.getPixel(u,N-L-1);
			
			ip1.putPixel(u,v,p);
	         }
	
		}
	
		 }
	 }
	byte [] pixels = (byte []) ip1.getPixels();//get the image pixel values
	IJ.wait(500);
	outputIp.setPixels(pixels);// update the new image buffer
	outputImp.show ();// Opens a new window to display the output image
 }
}