
// Author : Subham Sarkar

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class Program_9 implements PlugInFilter {
	String title= null;

	public int setup(String arg, ImagePlus im) {
		title=im.getTitle();
		return DOES_8G;
	}

	public void run(ImageProcessor ip) {
		int w=256;
		int h=140;
		int u=0;
		int v=0;
		int count=2;
		int[] hist= ip.getHistogram(); //finding the histogram values of the image
		
		
		int max=hist[0];
		for(int k=0;k<hist.length;k++){			//finding max value in the histogram array
			if(max<hist[k])
				max=hist[k];
		}
		//IJ.log("heloo"+max);
		int value=max;
		while(value>h){
			
			value=(int)max/count;
			count++;
			

		}
		count--;
		//IJ.log("heloo"+count);

		for(int i=0;i<hist.length;i++){
			hist[i]=(int)hist[i]/count;
			//IJ.log("\nhist["+i+"]=  "+hist[i]);
		}

		
		//creating the histogram image
		ImageProcessor histIp = new ByteProcessor(w,h);
		histIp.setValue(255); //white=255
		histIp.fill(); 
		for(u=0;u<w;u++)
		{
				for(int j=h;j>=(h-hist[u]);j--){
						histIp.putPixel(u,j,0);
					}
				//histIp.setValue(0);
				 //histIp.drawLine(u,u-hist[u],u,h);
				
		}

		


		// display the histogram image:
		String hTitle = "Histogram of " + title;
 		ImagePlus histIm = new ImagePlus(hTitle, histIp);
		histIm.show();
		//histIm.updateAndDraw();
	}

}//end of class
