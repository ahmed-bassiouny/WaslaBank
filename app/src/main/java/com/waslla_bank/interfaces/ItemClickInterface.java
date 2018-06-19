package com.waslla_bank.interfaces;

import android.support.annotation.Nullable;

/**
 * Created by bassiouny on 26/03/18.
 */

public interface ItemClickInterface<T> {
    void getItem(@Nullable T t, int position);
}
