package com.yellowpineapple.wakup.sdk.controllers;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yellowpineapple.wakup.sdk.R;
import com.yellowpineapple.wakup.sdk.models.Offer;
import com.yellowpineapple.wakup.sdk.views.OfferListView;

import java.util.List;

/***
 * ADAPTER
 */

public class OffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnLongClickListener, View.OnClickListener {

    List<Offer> offers;
    boolean loading;
    Context context;
    Location currentLocation;
    Listener listener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public OffersAdapter(final Context context) {
        super();
        this.context = context;
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {

        public OfferListView offerView;
        public OfferViewHolder(OfferListView v) {
            super((View) v);
            offerView = v;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_HEADER: {
                // TODO include header
                return null;
            }
            default: {
                OfferListView offerView = new OfferListView(getContext());
                offerView.setClickable(true);
                offerView.setOnClickListener(this);
                offerView.setLongClickable(true);
                offerView.setOnLongClickListener(this);
                // TODO Include listeners
                OfferViewHolder viewHolder = new OfferViewHolder(offerView);
                return viewHolder;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_HEADER: {
                // TODO Implement
            }
            default: {
                OfferViewHolder offerViewHolder = (OfferViewHolder) holder;
                // TODO if header is present, increase position
                Offer offer = offers.get(position);
                offerViewHolder.offerView.setOffer(offer, currentLocation);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        // TODO Increase in 1 if header is present
        if (offers != null) {
            count = offers.size();
        }
        if (loading) {
            count++;
        }
        return count;
    }

    boolean isLoadingView(int position) {
        int size = offers != null ? offers.size() : 0;
        return position == size;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof OfferListView) {
            OfferListView offerView = (OfferListView) v;
            if (listener != null) listener.onOfferClick(offerView.getOffer(), offerView);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v instanceof OfferListView) {
            OfferListView offerView = (OfferListView) v;
            if (listener != null) listener.onOfferLongClick(offerView.getOffer(), offerView);
            return true;
        }
        return false;
    }

    public interface Listener {
        void onOfferClick(Offer offer, View view);
        void onOfferLongClick(Offer offer, View view);
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public Context getContext() {
        return context;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}