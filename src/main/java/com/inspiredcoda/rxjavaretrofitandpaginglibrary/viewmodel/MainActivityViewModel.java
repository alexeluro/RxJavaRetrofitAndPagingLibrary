package com.inspiredcoda.rxjavaretrofitandpaginglibrary.viewmodel;

import com.inspiredcoda.rxjavaretrofitandpaginglibrary.model.User;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.network.Client;
import com.inspiredcoda.rxjavaretrofitandpaginglibrary.util.Resource;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    Client client;
    MediatorLiveData<Resource<List<User>>> mediator = new MediatorLiveData<>();

    public void init(){
        client = new Client();
        getSources();
    }

    private void getSources(){
        final LiveData<Resource<List<User>>> source = LiveDataReactiveStreams.fromPublisher(
                client.getApi().getUsers()
                .onErrorReturn(new Function<Throwable, List<User>>() {
                    @Override
                    public List<User> apply(Throwable throwable) throws Exception {
                        return new ArrayList<>();
                    }
                })
                .map(new Function<List<User>, Resource<List<User>>>() {
                    @Override
                    public Resource<List<User>> apply(List<User> users) throws Exception {
                        if(users.isEmpty()){
                            return new Resource<>().onError(null, new IOException().getMessage());
                        }else{
                            return new Resource<>().onSuccess(users);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
        );


        mediator.addSource(source, new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> users) {
                mediator.setValue(users);
                mediator.removeSource(source);
            }
        });
    }

    public LiveData<Resource<List<User>>> getData(){
        return mediator;
    }


}
