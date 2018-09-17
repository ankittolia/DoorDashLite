package doordash.com.doordash.service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ankit on 9/15/2018.
 */

public class RestaurantModel {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private String status;
    @SerializedName("cover_img_url")
    private String coverImgUrl;

    public RestaurantModel(String name, String description, String status, String coverImgUrl) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.coverImgUrl = coverImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }
}
