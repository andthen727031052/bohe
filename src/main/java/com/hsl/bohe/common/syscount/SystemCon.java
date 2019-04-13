package com.hsl.bohe.common.syscount;


public class SystemCon {
    public static final int OKFLAG=1;//有效
    public static final int DJFLAG=2;//临时无效
    public static final int QXFLAG=3;//无效

    //编码
    public static final int OK=1;
    public static final int ERROR=100;

    //redis服务器相关设置
    //public static final String REDISHOST="39.105.189.141";
    public static final int REDISPORT=6379;
    public static final String REDISHOST="10.8.156.67";

    public static final String REDISPASS="qfjava";
    //Redis常用Key
    public static final String LOGINTOKEN="usertoken";
    public static final String PHONETOKEN="phonetoken";

    //Cookie的常用键
    public static final String COOKIETOKEN="sctoken";

}
