package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import com.android.vending.R;
import com.google.android.finsky.placesapi.PlaceAutocompletePrediction;
import java.util.List;

public class AddressAutoComplete extends AutoCompleteTextView implements OnItemClickListener {
    private Adapter mAdapter;
    private boolean mBlockNextSuggestion;
    private AddressSuggestionProvider mSuggestionProvider;

    private class Adapter extends ArrayAdapter<String> implements Filterable {
        private List<PlaceAutocompletePrediction> mPredictions;

        public Adapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public int getCount() {
            return this.mPredictions != null ? this.mPredictions.size() : 0;
        }

        public String getItem(int index) {
            return ((PlaceAutocompletePrediction) this.mPredictions.get(index)).getDescription();
        }

        public Filter getFilter() {
            return new PlacesFilter();
        }
    }

    private class PlacesFilter extends Filter {
        private PlacesFilter() {
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            synchronized (AddressAutoComplete.this) {
                boolean block = AddressAutoComplete.this.mBlockNextSuggestion;
                AddressSuggestionProvider provider = AddressAutoComplete.this.mSuggestionProvider;
                AddressAutoComplete.this.mBlockNextSuggestion = false;
            }
            if (provider == null || block) {
                return null;
            }
            List<PlaceAutocompletePrediction> suggestions = provider.getSuggestions(constraint);
            if (suggestions == null) {
                return null;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = suggestions;
            filterResults.count = suggestions.size();
            return filterResults;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results == null || results.count <= 0) {
                AddressAutoComplete.this.mAdapter.notifyDataSetInvalidated();
                return;
            }
            AddressAutoComplete.this.mAdapter.mPredictions = (List) results.values;
            AddressAutoComplete.this.mAdapter.notifyDataSetChanged();
        }
    }

    public AddressAutoComplete(Context context) {
        this(context, null);
    }

    public AddressAutoComplete(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressAutoComplete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAdapter = new Adapter(getContext(), R.layout.address_suggestion_item);
        setAdapter(this.mAdapter);
        setOnItemClickListener(this);
    }

    public synchronized void setSuggestionProvider(AddressSuggestionProvider suggestionProvider) {
        this.mSuggestionProvider = suggestionProvider;
    }

    public synchronized void blockNextSuggestion() {
        this.mBlockNextSuggestion = true;
    }

    protected void replaceText(CharSequence text) {
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (this.mSuggestionProvider != null) {
            this.mSuggestionProvider.onSuggestionAccepted((PlaceAutocompletePrediction) this.mAdapter.mPredictions.get(position));
        }
    }
}
