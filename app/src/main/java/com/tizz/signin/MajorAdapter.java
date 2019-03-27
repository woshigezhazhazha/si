package com.tizz.signin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class MajorAdapter<T> extends ArrayAdapter {

    public MajorAdapter(Context context, int resource,@NonNull List<T> objects){
        super(context,resource,objects);
    }
    @Override
    public int getCount(){
        int i=super.getCount();
        return i>0?i-1:i;
    }
}
