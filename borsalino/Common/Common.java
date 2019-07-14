package com.example.borsalino.Common;

import com.example.borsalino.Model.Category;
import com.example.borsalino.Model.User;
import com.example.borsalino.Remote.IMyAPI;
import com.example.borsalino.Remote.RetrofitClient;

public class Common {
    public static final String BASE_URL = "http://10.0.2.2/BorsalinoORACLE/";
    //public static User currentUser = null;
    public static Category currentCategory = null;
    public static IMyAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }
}

