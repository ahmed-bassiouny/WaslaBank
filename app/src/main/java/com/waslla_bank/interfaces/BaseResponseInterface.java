package com.waslla_bank.interfaces;

/**
 * Created by bassiouny on 26/03/18.
 */


public interface BaseResponseInterface <T>{
    void onSuccess(T t);
    void onFailed(String errorMessage);
}
