package com.google.android.wallet.instrumentmanager.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.common.address.AddressUtils;
import com.google.android.wallet.instrumentmanager.common.util.ArrayUtils;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeSelector.OnRegionCodeSelectedListener;

public class RegionCodeSelectorSpinner extends Spinner implements RegionCodeSelector {
    private OnRegionCodeSelectedListener mOnRegionCodeSelectedListener;
    private ContextThemeWrapper mThemedContext;

    static class RegionCodeAdapter extends ArrayAdapter<Integer> {
        private int mDropDownResource;
        private final int mFieldId;
        private final LayoutInflater mInflater;
        private final int mResource;

        public RegionCodeAdapter(ContextThemeWrapper context, int resource, int textViewResourceId, Integer[] objects) {
            super(context, resource, textViewResourceId, objects);
            this.mResource = resource;
            this.mFieldId = textViewResourceId;
            this.mInflater = LayoutInflater.from(context);
        }

        public void setDropDownViewResource(int resource) {
            super.setDropDownViewResource(resource);
            this.mDropDownResource = resource;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(position, convertView, parent, this.mDropDownResource);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(position, convertView, parent, this.mResource);
        }

        private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource) {
            if (convertView == null) {
                convertView = this.mInflater.inflate(resource, parent, false);
            }
            Integer regionCode = (Integer) getItem(position);
            TextView description = (TextView) convertView.findViewById(this.mFieldId);
            if (regionCode.intValue() != 0) {
                description.setText(AddressUtils.getDisplayCountryForDefaultLocale(regionCode.intValue()));
            } else {
                description.setText(null);
            }
            return convertView;
        }
    }

    public RegionCodeSelectorSpinner(Context context) {
        super(context);
        setThemedContext(context);
    }

    public RegionCodeSelectorSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setThemedContext(context);
    }

    public RegionCodeSelectorSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setThemedContext(context);
    }

    private void setThemedContext(Context context) {
        if (context instanceof ContextThemeWrapper) {
            this.mThemedContext = (ContextThemeWrapper) context;
            return;
        }
        throw new IllegalArgumentException("RegionCodeSelectorSpinner must be used with a ContextThemeWrapper");
    }

    public int getSelectedRegionCode() {
        return ((Integer) getSelectedItem()).intValue();
    }

    public void setSelectedRegionCode(int regionCode) throws IllegalStateException {
        if (getAdapter() == null) {
            throw new IllegalStateException("Populate selector with region codes before setting the selected Region Code");
        } else if (regionCode != 0 && regionCode != getSelectedRegionCode()) {
            int index = ((RegionCodeAdapter) getAdapter()).getPosition(Integer.valueOf(regionCode));
            if (index >= 0) {
                setSelection(index);
            }
        }
    }

    public void setRegionCodes(int[] regionCodes) {
        RegionCodeAdapter adapter = new RegionCodeAdapter(this.mThemedContext, R.layout.view_row_spinner, R.id.description, ArrayUtils.toWrapperArray(regionCodes));
        adapter.setDropDownViewResource(R.layout.view_spinner_dropdown);
        setAdapter(adapter);
        setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer regionCode = (Integer) RegionCodeSelectorSpinner.this.getItemAtPosition(position);
                if (RegionCodeSelectorSpinner.this.mOnRegionCodeSelectedListener != null) {
                    RegionCodeSelectorSpinner.this.mOnRegionCodeSelectedListener.onRegionCodeSelected(regionCode.intValue(), RegionCodeSelectorSpinner.this.getId());
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("RegionCodeSelectorSpinn", "Listener fired for onNothingSelected; ignoring");
            }
        });
    }

    public int getRegionCodeCount() {
        return getCount();
    }

    public void setRegionCodeSelectedListener(OnRegionCodeSelectedListener listener) {
        this.mOnRegionCodeSelectedListener = listener;
    }

    public boolean isValid() {
        return getRegionCodeCount() > 0 && getSelectedRegionCode() != 0;
    }

    public boolean validate() {
        return isValid();
    }
}
