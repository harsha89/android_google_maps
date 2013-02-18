package userInterfaces;




import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import experiment.android.easy.R;

import places.Result;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import internetAccess.Directions;

public class RouteDetails extends Activity implements Runnable {
	public static List<Result> results;
	private ProgressDialog progDailog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intermediator);
		startSearch();
		
	}
	public void startSearch()
	{
		 progDailog = ProgressDialog.show(RouteDetails.this,"", "Please wait...",true);
                 new Thread() {
                 public void run() {
                	 Directions direction=new Directions();
                	 String xml = direction.getXML(6.92294,79.954,6.92294,79.85245);
                     Document doc = direction.XMLfromString(xml);
                             
                          
             		NodeList nodes = doc.getElementsByTagName("html_instructions");
             		String[] temp=new String[nodes.getLength()];		
             		for (int i = 0; i < nodes.getLength(); i++) {									
             			Element e = (Element)nodes.item(i);
             			temp[i]=e.getTextContent().replaceAll("<b>", " ").replaceAll("</b>", " ").replaceAll("&nbsp;", "").replaceAll("</div>", "").replaceAll("<div style=\"font-size:0.9em\">", " ");
             		}
             		showRoute(temp);
         			progDailog.dismiss(); 
         			}
                 
        }.start();
	}
	public void showRoute(String[] str)
	{
		 Intent intent=new Intent(this,RouteDetailsDisplay.class);
		 intent.putExtra("route", str);
		 startActivity(intent);
		 this.finish();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
