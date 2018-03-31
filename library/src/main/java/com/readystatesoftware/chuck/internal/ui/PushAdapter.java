/*
 * Copyright (C) 2017 Jeff Gilfelt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.readystatesoftware.chuck.internal.ui;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.readystatesoftware.chuck.R;
import com.readystatesoftware.chuck.internal.data.LocalCupboard;
import com.readystatesoftware.chuck.internal.data.PushTransaction;

class PushAdapter extends RecyclerView.Adapter<PushAdapter.ViewHolder> {

    private final Context context;
    private final PushListFragment.OnListFragmentInteractionListener listener;
    private final CursorAdapter cursorAdapter;


    PushAdapter(Context context, PushListFragment.OnListFragmentInteractionListener listener) {
        this.listener = listener;
        this.context = context;

        cursorAdapter = new CursorAdapter(PushAdapter.this.context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chuck_list_push_transaction, parent, false);
                ViewHolder holder = new ViewHolder(itemView);
                itemView.setTag(holder);
                return itemView;
            }

            @Override
            public void bindView(View view, final Context context, Cursor cursor) {
                final PushTransaction transaction = LocalCupboard.getInstance().withCursor(cursor).get(PushTransaction.class);
                final ViewHolder holder = (ViewHolder) view.getTag();
                holder.start.setText(transaction.getRequestStartTimeString());

                holder.transaction = transaction;
                holder.code.setText("opre:" + holder.transaction.getCode());
                holder.path.setText("service:" + holder.transaction.getServiceId());
                holder.content.setText(holder.transaction.getContent());
                holder.account.setText(holder.transaction.getAccount());
//                holder.view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (null != PushAdapter.this.listener) {
//                            PushAdapter.this.listener.onListFragmentInteraction(holder.transaction);
//                        }
//                    }
//                });
            }

        };
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return new ViewHolder(v);
    }

    void swapCursor(Cursor newCursor) {
        cursorAdapter.swapCursor(newCursor);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView code;
        public final TextView path;
        public final TextView start;
        public final TextView content;
        public final TextView account;
        PushTransaction transaction;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            code = (TextView) view.findViewById(R.id.code);
            path = (TextView) view.findViewById(R.id.service_id);
            start = (TextView) view.findViewById(R.id.start);
            content = (TextView) view.findViewById(R.id.content);
            account = (TextView) view.findViewById(R.id.account);
        }
    }
}
