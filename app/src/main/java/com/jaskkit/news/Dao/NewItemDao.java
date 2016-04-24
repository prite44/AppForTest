package com.jaskkit.news.Dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaskkit on 4/17/16.
 */
public class NewItemDao implements Parcelable{
    @SerializedName("ln_id")
    private String id;
    @SerializedName("ln_update_content_status")
    private String state;
    @SerializedName("ln_title")
    private String title;
    @SerializedName("ln_detail")
    private String detail;
    @SerializedName("ln_image_file_name")
    private String urlImage;
    @SerializedName("ln_update_date")
    private String updateDate;
    @SerializedName("ln_expire_date")
    private String expireDate;

    protected NewItemDao(Parcel in) {
        id = in.readString();
        state = in.readString();
        title = in.readString();
        detail = in.readString();
        urlImage = in.readString();
        updateDate = in.readString();
        expireDate = in.readString();
        orderId = in.readString();
    }

    public static final Creator<NewItemDao> CREATOR = new Creator<NewItemDao>() {
        @Override
        public NewItemDao createFromParcel(Parcel in) {
            return new NewItemDao(in);
        }

        @Override
        public NewItemDao[] newArray(int size) {
            return new NewItemDao[size];
        }
    };

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @SerializedName("ln_order_id")

    private String orderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }


    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(state);
        dest.writeString(title);
        dest.writeString(detail);
        dest.writeString(urlImage);
        dest.writeString(updateDate);
        dest.writeString(expireDate);
        dest.writeString(orderId);
    }
}
