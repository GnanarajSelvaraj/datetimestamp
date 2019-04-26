package org.mahiti.time_premission.rest;



import org.mahiti.time_premission.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/json/est/now")
    Call<DataResponse> getCurrentDateTime();


}