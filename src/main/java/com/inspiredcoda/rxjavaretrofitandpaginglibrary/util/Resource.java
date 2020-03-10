package com.inspiredcoda.rxjavaretrofitandpaginglibrary.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource <T> {
    @NonNull
    private Status status;
    @Nullable
    private T data;
    @Nullable
    private String message;

    public Resource() {
    }

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> onLoading(@Nullable T data){
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> onSuccess(@Nullable T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> onError(@Nullable T data, @Nullable String message){
        return new Resource<>(Status.ERROR, data, message);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public enum Status {LOADING, SUCCESS, ERROR}
}
