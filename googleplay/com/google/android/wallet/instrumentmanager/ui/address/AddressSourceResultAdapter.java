package com.google.android.wallet.instrumentmanager.ui.address;

import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.wallet.instrumentmanager.common.address.AddressField;
import com.google.android.wallet.instrumentmanager.common.address.AddressFormatter;
import com.google.android.wallet.instrumentmanager.common.address.AddressSource;
import com.google.android.wallet.instrumentmanager.common.address.AddressSourceResult;
import com.google.android.wallet.instrumentmanager.common.util.ArrayUtils;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class AddressSourceResultAdapter extends BaseAdapter implements Filterable {
    private final char mAddressField;
    private final List<AddressSource> mAddressSources;
    private AddressSourceResultFilter mFilter;
    private final LayoutInflater mInflater;
    private final String mLanguageCode;
    CharSequence mLastPublishedConstraint;
    ArrayList<AddressSourceResult> mObjects;
    private final int mRegionCode;
    final char[] mRemainingFields;
    private final char[] mRequiredFields;
    private final int mTextViewResourceId;

    class AddressSourceResultFilter extends Filter {
        AddressSourceResultFilter() {
        }

        ArrayList<AddressSourceResult> performFilteringForValues(CharSequence constraint) {
            if (constraint != null) {
                for (AddressSource source : AddressSourceResultAdapter.this.mAddressSources) {
                    try {
                        ArrayList<AddressSourceResult> processedResults = processAddressSourceResults(source.getAddresses(constraint, AddressSourceResultAdapter.this.mAddressField, AddressSourceResultAdapter.this.mRemainingFields, AddressSourceResultAdapter.this.mRegionCode, AddressSourceResultAdapter.this.mLanguageCode), source.getName());
                        if (!(processedResults == null || processedResults.isEmpty())) {
                            return processedResults;
                        }
                    } catch (Throwable e) {
                        Log.e("AddressSourceResultAdap", "Could not fetch addresses from " + source.getName(), e);
                    }
                }
            }
            return new ArrayList();
        }

        private ArrayList<AddressSourceResult> processAddressSourceResults(List<AddressSourceResult> unprocessedResults, String sourceName) {
            if (unprocessedResults == null) {
                return null;
            }
            ArrayList<AddressSourceResult> processedResults = new ArrayList();
            LinkedHashMap<String, Integer> matchingTermToPosition = new LinkedHashMap();
            for (AddressSourceResult result : unprocessedResults) {
                if (result.address != null) {
                    if (!isBadAddress(result.address)) {
                        if (!containsAllRemainingRequiredFields(result.address)) {
                            String matchingTerm = result.matchingTerm;
                            if (!(TextUtils.isEmpty(matchingTerm) || matchingTermToPosition.containsKey(matchingTerm))) {
                                matchingTermToPosition.put(matchingTerm, Integer.valueOf(processedResults.size()));
                            }
                        }
                    }
                }
                if (!TextUtils.isEmpty(result.description)) {
                    processedResults.add(result);
                    if (!TextUtils.isEmpty(result.matchingTerm)) {
                        matchingTermToPosition.put(result.matchingTerm, Integer.valueOf(-1));
                    }
                }
            }
            if (matchingTermToPosition.isEmpty()) {
                return processedResults;
            }
            int offset = 0;
            for (Entry<String, Integer> entry : matchingTermToPosition.entrySet()) {
                int position = ((Integer) entry.getValue()).intValue();
                if (position != -1) {
                    processedResults.add(position + offset, new AddressSourceResult((String) entry.getKey(), sourceName));
                    offset++;
                }
            }
            return processedResults;
        }

        private boolean isBadAddress(PostalAddress address) {
            if (AddressSourceResultAdapter.this.mRequiredFields == null || address.addressLine.length == 0) {
                return false;
            }
            boolean requireFieldOtherThanAddressLineAndRecipient = false;
            for (char field : AddressSourceResultAdapter.this.mRequiredFields) {
                switch (field) {
                    case R.styleable.Theme_dividerVertical /*49*/:
                    case R.styleable.Theme_dividerHorizontal /*50*/:
                    case R.styleable.Theme_activityChooserViewStyle /*51*/:
                    case R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
                    case R.styleable.Theme_colorAccent /*78*/:
                        break;
                    default:
                        if (AddressField.exists(field)) {
                            requireFieldOtherThanAddressLineAndRecipient = true;
                            if (TextUtils.isEmpty(AddressFormatter.formatAddressValue(address, field))) {
                                break;
                            }
                            return false;
                        }
                        continue;
                }
            }
            return requireFieldOtherThanAddressLineAndRecipient;
        }

        private boolean containsAllRemainingRequiredFields(PostalAddress address) {
            if (AddressSourceResultAdapter.this.mRequiredFields == null) {
                return true;
            }
            for (char field : AddressSourceResultAdapter.this.mRequiredFields) {
                char field2;
                if (field2 == 'A') {
                    field2 = '1';
                }
                if (ArrayUtils.contains(AddressSourceResultAdapter.this.mRemainingFields, field2) && TextUtils.isEmpty(AddressFormatter.formatAddressValue(address, field2))) {
                    return false;
                }
            }
            return true;
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<AddressSourceResult> addresses = performFilteringForValues(constraint);
            FilterResults results = new FilterResults();
            results.values = addresses;
            results.count = addresses.size();
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            publishResults(constraint, (ArrayList) results.values);
        }

        void publishResults(CharSequence constraint, ArrayList<AddressSourceResult> values) {
            AddressSourceResultAdapter.this.mLastPublishedConstraint = constraint;
            AddressSourceResultAdapter.this.mObjects = values;
            if (AddressSourceResultAdapter.this.mObjects == null || AddressSourceResultAdapter.this.mObjects.isEmpty()) {
                AddressSourceResultAdapter.this.notifyDataSetInvalidated();
            } else {
                AddressSourceResultAdapter.this.notifyDataSetChanged();
            }
        }

        public CharSequence convertResultToString(Object resultValue) {
            if (resultValue instanceof AddressSourceResult) {
                return ((AddressSourceResult) resultValue).matchingTerm;
            }
            return super.convertResultToString(resultValue);
        }
    }

    private static char[] checkValidAddressFieldsAndCopy(char[] fields) {
        int i = 0;
        while (i < fields.length && !AddressField.exists(fields[i])) {
            i++;
        }
        if (i < fields.length) {
            return Arrays.copyOfRange(fields, i, fields.length);
        }
        throw new IllegalArgumentException("fields must contain at least one valid field");
    }

    public AddressSourceResultAdapter(ContextThemeWrapper context, int textViewResourceId, int regionCode, String languageCode, char addressField, char[] remainingFields, String requiredFields, List<AddressSource> addressSources) {
        if (remainingFields == null || remainingFields.length == 0) {
            throw new IllegalArgumentException("remainingFields are required");
        } else if (addressSources == null || addressSources.isEmpty()) {
            throw new IllegalArgumentException("addressSources are required");
        } else {
            this.mTextViewResourceId = textViewResourceId;
            this.mRegionCode = regionCode;
            this.mLanguageCode = languageCode;
            this.mAddressField = addressField;
            this.mRemainingFields = checkValidAddressFieldsAndCopy(remainingFields);
            this.mRequiredFields = requiredFields != null ? requiredFields.toCharArray() : null;
            this.mAddressSources = new ArrayList(addressSources);
            this.mInflater = LayoutInflater.from(context);
            this.mObjects = new ArrayList();
        }
    }

    public int getCount() {
        return this.mObjects.size();
    }

    public AddressSourceResult getItem(int position) {
        return (AddressSourceResult) this.mObjects.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new AddressSourceResultFilter();
        }
        return this.mFilter;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mInflater.inflate(this.mTextViewResourceId, parent, false);
        }
        ((TextView) convertView.findViewById(com.google.android.wallet.instrumentmanager.R.id.description)).setText(getItem(position).description);
        return convertView;
    }
}
