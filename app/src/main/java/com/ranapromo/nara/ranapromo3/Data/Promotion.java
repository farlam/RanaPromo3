package com.ranapromo.nara.ranapromo3.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by NOO on 05/09/2015.
 */
public class Promotion implements Parcelable{

    public long promoID; // promo's ID
    public String oldPrice; // original before %
    public String newPrice; // price after %
    public String marqueName; // name of product's brand
    public String promotionName; // name of the product
    public String reduction; // % of reduction
    public String timeLeft; // time left until the end of the promo
    public String description; // description of the promo
    public int image_ID;  //should be a string type
    public int favorite; // not favorite = 0    favorite =1


    public Promotion(Parcel in){

        String[] mData = new String[10];

        in.readStringArray(mData);

        this.promoID = Long.valueOf(mData[0]);
        this.oldPrice = mData[1];
        this.newPrice = mData[2];
        this.marqueName = mData[3];
        this.promotionName = mData[4];
        this.reduction = mData[5];
        this.timeLeft = mData[6];
        this.description = mData[7];
        this.image_ID = Integer.parseInt(mData[8]);
        this.favorite = Integer.parseInt(mData[9]);

    }

    public Promotion(){

    }
    public Promotion(long promoID, String oldPrice, String newPrice, String marqueName, String promotionName, String reduction, String timeLeft, String description, int image_id, int favorite) {
        this.promoID = promoID;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.marqueName = marqueName;
        this.promotionName = promotionName;
        this.reduction = reduction;
        this.timeLeft = timeLeft;
        this.description = description;
        this.image_ID = image_id;
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(new String[]{String.valueOf(this.promoID),
                                            this.oldPrice,
                                            this.newPrice,
                                            this.marqueName,
                                            this.promotionName,
                                            this.reduction,
                                            this.timeLeft,
                                            this.description,
                                            String.valueOf(image_ID),
                                            String.valueOf(favorite) });
    }

    public static final Parcelable.Creator<Promotion> CREATOR= new Parcelable.Creator<Promotion>() {

        @Override
        public Promotion createFromParcel(Parcel in) {
// TODO Auto-generated method stub
            return new Promotion(in);  //using parcelable constructor
        }

        @Override
        public Promotion[] newArray(int size) {
// TODO Auto-generated method stub
            return new Promotion[size];
        }
    };
}
