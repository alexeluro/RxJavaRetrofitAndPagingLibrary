package com.inspiredcoda.rxjavaretrofitandpaginglibrary.network;

import com.inspiredcoda.rxjavaretrofitandpaginglibrary.model.User;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.util.Resource;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Api {

    @GET("posts")
    Flowable<List<User>> getUsers();

}
