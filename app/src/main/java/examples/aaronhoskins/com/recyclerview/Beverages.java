package examples.aaronhoskins.com.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Beverages implements Parcelable {
    private String name;
    private String servingSize;
    private int rating;
    private boolean isCaffinated;
    private boolean isAlcoholic;
    private boolean isCarbonated;

    public Beverages() {
    }

    public Beverages(String name, String servingSize, int rating, boolean isCaffinated, boolean isAlcoholic, boolean isCarbonated) {
        this.name = name;
        this.servingSize = servingSize;
        this.rating = rating;
        this.isCaffinated = isCaffinated;
        this.isAlcoholic = isAlcoholic;
        this.isCarbonated = isCarbonated;
    }

    protected Beverages(Parcel in) {
        name = in.readString();
        servingSize = in.readString();
        rating = in.readInt();
        isCaffinated = in.readByte() != 0;
        isAlcoholic = in.readByte() != 0;
        isCarbonated = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(servingSize);
        dest.writeInt(rating);
        dest.writeByte((byte) (isCaffinated ? 1 : 0));
        dest.writeByte((byte) (isAlcoholic ? 1 : 0));
        dest.writeByte((byte) (isCarbonated ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Beverages> CREATOR = new Creator<Beverages>() {
        @Override
        public Beverages createFromParcel(Parcel in) {
            return new Beverages(in);
        }

        @Override
        public Beverages[] newArray(int size) {
            return new Beverages[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isCaffinated() {
        return isCaffinated;
    }

    public void setCaffinated(boolean caffinated) {
        isCaffinated = caffinated;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public boolean isCarbonated() {
        return isCarbonated;
    }

    public void setCarbonated(boolean carbonated) {
        isCarbonated = carbonated;
    }
}
