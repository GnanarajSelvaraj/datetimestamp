package org.mahiti.time_premission.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("currentDateTime")
    @Expose
    private String currentDateTime;
    @SerializedName("utcOffset")
    @Expose
    private String utcOffset;
    @SerializedName("isDayLightSavingsTime")
    @Expose
    private boolean isDayLightSavingsTime;
    @SerializedName("dayOfTheWeek")
    @Expose
    private String dayOfTheWeek;

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public String get$id() {
        return $id;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public boolean isDayLightSavingsTime() {
        return isDayLightSavingsTime;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }



    public String getOrdinalDate() {
        return ordinalDate;
    }

    public Object getServiceResponse() {
        return serviceResponse;
    }

    @SerializedName("timeZoneName")
    @Expose
    private String timeZoneName;

    @SerializedName("ordinalDate")
    @Expose
    private String ordinalDate;
    @SerializedName("serviceResponse")
    @Expose
    private Object serviceResponse;


}