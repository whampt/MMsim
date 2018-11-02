import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class DisplayChart extends ApplicationFrame {
	private static String fName;
	private static String[][] results;
   public DisplayChart( String applicationTitle, String chartTitle, String[][] resData) {
	   super(applicationTitle);
	   results=resData;
	   JFreeChart xylineChart = ChartFactory.createXYLineChart(
			   chartTitle ,
			   "Set Size" ,
			   "Page Faults" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( ) {
      final XYSeries random = new XYSeries( "Random Algorithm" );
      
      final XYSeries lrua = new XYSeries( "LRUA" );          
      
      final XYSeries optimal = new XYSeries( "Optimal" ); int x=0;int y=0;int z=0; 
      for(int i = 0; i<results.length;i++)
      {
    	  System.out.println(results[i][0]);
    	  /*
    	   * Memorandum: figure out why it is that == isn't working as a comparison operator here.
    	   */
    	  if(results[i][0].equals("Optimal")){
    		  optimal.add(Double.parseDouble(results[i][2]),
    				  Double.parseDouble(results[i][3]));x++;
    	  }else if(results[i][0].equals("LRUA")){
    		  lrua.add(Double.parseDouble(results[i][2]),
    				  Double.parseDouble(results[i][3]));y++;
    	  }else{
        		  random.add(Double.parseDouble(results[i][2]),
        				  Double.parseDouble(results[i][3]));z++;
    	  }
      }
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(random);          
      dataset.addSeries(lrua);          
      dataset.addSeries(optimal);
      System.out.println(x);
      System.out.println(y);
      System.out.println(z);
      System.out.println(results.length);
      return dataset;
   }

   
   
   public static String[][] procData(List<String> data)
   {
	   String[][] parsedData=new String[data.size()][4];
	   for(int i=0;i<data.size();i++)
	   {
		   String[] temp = data.get(i).split(",|:");
		   temp[0]=temp[0].replaceAll("\\s+","");
		   temp[1]=temp[1].replaceAll("\\s+","");
		   temp[2]=temp[2].replaceAll("\\s+","");
		   temp[2]=temp[2].substring(7);
		   temp[3]=temp[3].replaceAll("\\s+","");
		   parsedData[i]=temp;
	   }
	   return parsedData;
   }
   public static ArrayList<String> readFile() throws IOException
   {
	   List<String> data=new ArrayList<String>();
	   BufferedReader br = new BufferedReader(new FileReader(fName));
	   String line;
	   while ((line = br.readLine()) != null) {
		   if(line.length()!=0)
			   data.add(line);
	   }
	   return (ArrayList<String>) data;
   }
   public static void setFile(String file){
	   fName=file;
   }
   public static void main( String[ ] args ) throws IOException
   {
	   String fName="Tests.txt";
	   setFile(fName);
	   
	   
	   List<String> data=readFile();
	   String[][] parsedData=procData(data);
	   
	   DisplayChart dC=new DisplayChart("AlgorithmRes", "Results", parsedData);
	   dC.pack( );          
	   RefineryUtilities.centerFrameOnScreen(dC);          
	   dC.setVisible( true );
//	   for(int i=0;i<data.size();i++){
//		   System.out.print("*");
//		   for(int j=0;j<4;j++){
//			   System.out.print(parsedData[i][j]);
//			   System.out.print("*");
//		   }
//		   System.out.println();
//	   }
   }
}
