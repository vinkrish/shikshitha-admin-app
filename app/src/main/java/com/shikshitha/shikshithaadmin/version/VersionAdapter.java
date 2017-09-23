package com.shikshitha.shikshithaadmin.version;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.model.AppVersion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 28-08-2017.
 */

class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.ViewHolder> {
    private List<AppVersion> allItems;
    private List<AppVersion> items;
    private final OnItemClickListener listener;

    interface OnItemClickListener {
        void onItemClick(AppVersion group);
    }

    VersionAdapter(List<AppVersion> allItems, OnItemClickListener listener) {
        this.allItems = allItems;
        this.items = new ArrayList<>();
        this.listener = listener;
    }

    void setDataSet(List<AppVersion> allItems) {
        this.allItems = allItems;
        replaceData("admin");
    }

    void replaceData(String filter) {
        items = new ArrayList<>();
        for(AppVersion appVersion: allItems) {
            if(appVersion.getAppName().equals(filter)) {
                items.add(appVersion);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public VersionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_version_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VersionAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.app_version_id)
        TextView appVersionId;
        @BindView(R.id.app_version_status)
        TextView appVersionStatus;
        @BindView(R.id.app_name)
        TextView appName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final AppVersion appVersion, final OnItemClickListener listener) {
            appVersionId.setText(String.valueOf(appVersion.getVersionId()));
            appVersionStatus.setText(appVersion.getStatus());
            appName.setText(appVersion.getAppName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(appVersion);
                }
            });
        }
    }
}
