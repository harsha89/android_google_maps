package drawingMap;

import android.os.Parcel;
import android.os.Parcelable;

public class Road implements Parcelable {

	public String mName; // Road name
	private Double distance; // route distance
	public String mDescription; // description abt route (Distance: 0.0km(about
								// seconds))
	public int mColor;
	public int mWidth;
	public double[][] mRoute = new double[][] {};
	public Point[] mPoints = new Point[] {};

	public Road(String s) {
		// TODO Auto-generated constructor stub
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(distance.toString());

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Road createFromParcel(Parcel in) {
			return new Road(in);
		}

		public Road[] newArray(int size) {
			return new Road[size];
		}
	};

	public Road(Parcel in) {
		readFromParcel(in);
	}

	public Road() {

	}

	private void readFromParcel(Parcel in) {
		distance = Double.valueOf(in.readString());
	}

}
