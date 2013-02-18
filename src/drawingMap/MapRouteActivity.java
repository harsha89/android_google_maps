package drawingMap;

import java.io.InputStream;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;

import experiment.android.easy.R;


public class MapRouteActivity extends MapActivity {

	 LinearLayout linearLayout;
     MapView mapView;
     private Road mRoad;
     double fromLat , fromLon,toLat,toLon; 
     Button btnTaxiFareScreen;
     
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent i = getIntent();
	        String from = i.getStringExtra("start");
	        String to = i.getStringExtra("end");
	        
	        String[] start = from.split(",");
	        String[] end = to.split(",");
	        fromLat = Double.valueOf(start[0]);
	        fromLon = Double.valueOf(start[1]);
	        toLat = Double.valueOf(end[0]);
	        toLon = Double.valueOf(end[1]);
	        
	        setContentView(R.layout.map_route);
	        mapView = (MapView) findViewById(R.id.mapview);
            mapView.setBuiltInZoomControls(true);
            new Thread() {
                @Override
                public void run() {
                        String url = RoadProvider
                                        .getUrl(fromLat, fromLon, toLat, toLon); //find the URL
                        InputStream is = getConnection(url);
                        mRoad = RoadProvider.getRoute(is);
                        mHandler.sendEmptyMessage(0);
                      
                }
        }.start();
	        
	 }
	 
	 
		Handler mHandler = new Handler() {
          public void handleMessage(android.os.Message msg) {
                  TextView textView = (TextView) findViewById(R.id.description);
                  textView.setText(mRoad.mName + " " + mRoad.mDescription);
                  MapOverlay mapOverlay = new MapOverlay(mRoad, mapView);
                  List<Overlay> listOfOverlays = mapView.getOverlays();
                  listOfOverlays.clear();
                  listOfOverlays.add(mapOverlay);
                  mapView.invalidate();
          };
  };

  private InputStream getConnection(String url) {
          InputStream is = null;
          try {
                  URLConnection conn = new URL(url).openConnection();
                  is = conn.getInputStream();
          } catch (MalformedURLException e) {
                  e.printStackTrace();
          } catch (IOException e) {
                  e.printStackTrace();
          }
          return is;
  }
	 
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	class MapOverlay extends com.google.android.maps.Overlay {
        Road mRoad;
        ArrayList<GeoPoint> mPoints;

        public MapOverlay(Road road, MapView mv) {
                mRoad = road;
                if (road.mRoute.length > 0) {
                        mPoints = new ArrayList<GeoPoint>();
                        for (int i = 0; i < road.mRoute.length; i++) {
                                mPoints.add(new GeoPoint((int) (road.mRoute[i][1] * 1000000),
                                                (int) (road.mRoute[i][0] * 1000000)));
                        }
                        int moveToLat = (mPoints.get(0).getLatitudeE6() + (mPoints.get(
                                        mPoints.size() - 1).getLatitudeE6() - mPoints.get(0)
                                        .getLatitudeE6()) / 2);
                        int moveToLong = (mPoints.get(0).getLongitudeE6() + (mPoints.get(
                                        mPoints.size() - 1).getLongitudeE6() - mPoints.get(0)
                                        .getLongitudeE6()) / 2);
                        GeoPoint moveTo = new GeoPoint(moveToLat, moveToLong);

                        MapController mapController = mv.getController();
                        mapController.animateTo(moveTo);
                        mapController.setZoom(16);
                }
        }
        
        @Override
        public boolean draw(Canvas canvas, MapView mv, boolean shadow, long when) {
                super.draw(canvas, mv, shadow);
                drawPath(mv, canvas);
                return true;
        }

        public void drawPath(MapView mv, Canvas canvas) {
                int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3);
                for (int i = 0; i < mPoints.size(); i++) {
                        Point point = new Point();
                        mv.getProjection().toPixels(mPoints.get(i), point);
                        x2 = point.x;
                        y2 = point.y;
                        if (i > 0) {
                                canvas.drawLine(x1, y1, x2, y2, paint);
                        }
                        x1 = x2;
                        y1 = y2;
                }
        }
}
}
