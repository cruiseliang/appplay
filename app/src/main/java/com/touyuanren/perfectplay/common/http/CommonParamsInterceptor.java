package com.touyuanren.perfectplay.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.touyuanren.perfectplay.common.Constant;
import com.touyuanren.perfectplay.common.util.ACache;
import com.touyuanren.perfectplay.common.util.DensityUtil;
import com.touyuanren.perfectplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Liang on 2017/10/11 0011.
 */

public class CommonParamsInterceptor implements Interceptor {

    private Gson gson;
    private Context mContext;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public CommonParamsInterceptor(Context mContext, Gson gson) {
        this.mContext = mContext;
        this.gson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //先拦截原始的request,然后进行请求；
        Request request = chain.request();
        try {
            String method = request.method();
            HashMap<String, Object> commomParamsMap = new HashMap<>();

            commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");

            String token = ACache.get(mContext).getAsString(Constant.TOKEN);
            commomParamsMap.put(Constant.TOKEN, token == null ? "" : token);
            if (method.equals("GET")) {
                //如果不确定请求参数的处理
                HttpUrl httpUrl = request.url();
                HashMap<String, Object> rootMap = new HashMap<>();
                Set<String> setParams = httpUrl.queryParameterNames();
                for (String name : setParams) {
                    if (name.equals(Constant.PARAM)) {
                        String oldParams = httpUrl.queryParameter("p");

                        if (oldParams != null) {
                            HashMap<String, Object> p = gson.fromJson(oldParams, HashMap.class);
                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {

                                    rootMap.put(entry.getKey(), entry.getValue());
                                }
                            }
                        }
                    } else {
                        rootMap.put(name, httpUrl.queryParameter(name));
                    }
                }


                rootMap.put("publicParams", commomParamsMap);
                String newJsonParams = gson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}
                String url = httpUrl.toString();
                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0, index);
                }
                url = url + "?" + Constant.PARAM + "=" + newJsonParams; //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                request = request.newBuilder().url(url).build();

            } else if (method.equals("POST")) {
                RequestBody body = request.body();
                HashMap<String, Object> rootMap = new HashMap<>();
                if (body instanceof FormBody) { // form 表单

                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();
                    rootMap = gson.fromJson(oldJsonParams, HashMap.class); // 原始参数
                    rootMap.put("publicParams", commomParamsMap); // 重新组装
                    String newJsonParams = gson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                }

            } else {


            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return chain.proceed(request);
    }
}
