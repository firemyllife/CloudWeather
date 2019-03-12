package com.example.administrator.cloud.util;

import android.text.TextUtils;

import com.example.administrator.cloud.db.City;
import com.example.administrator.cloud.db.County;
import com.example.administrator.cloud.db.Province;
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
                 }

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
                JSONArray allcountys = new JSONArray(response);
                for (int i=0;i<allcountys.length();i++){
                    JSONObject object = allcountys.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getInt("id"));
                    county.setCityId(cityId);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }




}
