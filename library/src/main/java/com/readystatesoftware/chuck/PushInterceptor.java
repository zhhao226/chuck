package com.readystatesoftware.chuck;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.readystatesoftware.chuck.internal.data.LocalCupboard;
import com.readystatesoftware.chuck.internal.data.PushContentProvider;
import com.readystatesoftware.chuck.internal.data.PushTransaction;

/**
 * Created by zhanghao.
 * 2018/3/29-19:38
 */

public class PushInterceptor {

    public static Uri create(Context context, PushTransaction transaction) {
        ContentValues values = LocalCupboard.getInstance().withEntity(PushTransaction.class).toContentValues(transaction);
        Uri uri = context.getContentResolver().insert(PushContentProvider.TRANSACTION_URI, values);
//        transaction.setId(Long.valueOf(uri.getLastPathSegment()));
        return uri;
    }

    public static int update(Context context, PushTransaction transaction, Uri uri) {
        ContentValues values = LocalCupboard.getInstance().withEntity(PushTransaction.class).toContentValues(transaction);
        int updated = context.getContentResolver().update(uri, values, null, null);
        return updated;
    }

}
