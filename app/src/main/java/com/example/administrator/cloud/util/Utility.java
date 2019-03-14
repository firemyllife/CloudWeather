package com.example.administrator.cloud.util;

import android.text.TextUtils;

import com.example.administrator.cloud.db.City;
import com.example.administrator.cloud.db.County;
import com.example.administrator.cloud.db.Province;
import com.example.administrator.cloud.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*解析和返回服务器返回的省级数据*/
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allprovinces = new JSONArray(response);
                for (int i=0;i<allprovinces.length();i++){
                    JSONObject object = allprovinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(object.getString("name"));
                    province.setProvinceCode(object.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    /*
    * 解析和返回服务器返回的市级数据
    * */
    public static boolean handleCityResponse(String response,int provinceId){
         if (!TextUtils.isEmpty(response)){
             try {
                 JSONArray allcitys = new JSONArray(response);
                 for (int i=0;i<allcitys.length();i++){
                     JSONObject object = allcitys.getJSONObject(i);
                     City city = new City();
                     city.setCityName(object.getString("name"));
                     city.setCitycode(object.getInt("id"));
                     city.setProvinceId(provinceId);
                     city.save();
                 }
                 return true;
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
        return false;
    }
    /*
     * 解析和返回服务器返回的县级数据
     * */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject object = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的json数据解析成Weather实体类
     * @return
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
