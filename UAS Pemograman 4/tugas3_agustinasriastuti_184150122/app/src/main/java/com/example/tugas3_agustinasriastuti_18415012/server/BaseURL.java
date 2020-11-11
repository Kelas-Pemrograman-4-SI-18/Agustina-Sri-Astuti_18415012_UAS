package com.example.tugas3_agustinasriastuti_18415012.server;

public class BaseURL {
    public static String baseurl = "http://192.168.43.243:5050/";
    public static String login = baseurl + "user/login";
    public static String register = baseurl + "user/registrasi";

    //data
    public static String dataDarah = baseurl + "darah/show";
    public static String editDataDarah = baseurl + "darah/update/";
    public static String deleteDataDarah = baseurl + "darah/delete/";
    public static String inputDataDarah = baseurl + "darah/input";
}
