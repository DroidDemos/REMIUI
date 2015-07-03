package com.xiaomi.passport.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import com.xiaomi.account.R;
import com.xiaomi.passport.utils.PhoneNumUtil;
import com.xiaomi.passport.utils.PhoneNumUtil.CountryPhoneNumData;
import com.xiaomi.passport.widget.AlphabetFastIndexer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class AreaCodePickerAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private List<CountryPhoneNumData> mList;
    private Map<Integer, String> mSectionText;
    private int[] mSectionToPosition;
    private String[] mSections;

    public AreaCodePickerAdapter(Context context, Bundle data) {
        this.mContext = context;
        buildPickerSectionList();
    }

    private void buildPickerSectionList() {
        int i;
        List<CountryPhoneNumData> recommendList = PhoneNumUtil.getRecommendCountryPhoneNumDataList();
        List<CountryPhoneNumData> totalList = PhoneNumUtil.getCountryPhoneNumDataList();
        this.mList = new ArrayList(recommendList);
        this.mList.addAll(totalList);
        SortedSet<String> set = new TreeSet();
        List<String> firstChars = new ArrayList();
        for (i = 0; i < recommendList.size(); i++) {
            firstChars.add(AlphabetFastIndexer.STARRED_TITLE);
            set.add(AlphabetFastIndexer.STARRED_TITLE);
        }
        for (CountryPhoneNumData item : totalList) {
            String first = item.countryName.substring(0, 1).toUpperCase();
            firstChars.add(first);
            set.add(first);
        }
        this.mSections = (String[]) set.toArray(new String[0]);
        this.mSectionToPosition = new int[this.mSections.length];
        this.mSectionText = new HashMap();
        this.mSectionToPosition[0] = 0;
        this.mSectionText.put(Integer.valueOf(0), this.mContext.getString(R.string.passport_area_code_hot));
        for (i = 1; i < this.mSections.length; i++) {
            this.mSectionToPosition[i] = firstChars.indexOf(this.mSections[i]);
            this.mSectionText.put(Integer.valueOf(this.mSectionToPosition[i]), this.mSections[i]);
        }
    }

    public String getSectionTitleForPostion(int position) {
        return ((CountryPhoneNumData) this.mList.get(position)).countryName.substring(0, 1).toUpperCase();
    }

    public int getCount() {
        return this.mList.size();
    }

    public CountryPhoneNumData getItem(int position) {
        return (CountryPhoneNumData) this.mList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        AreaCodePickerListItem resultView = (AreaCodePickerListItem) convertView;
        if (resultView == null) {
            resultView = (AreaCodePickerListItem) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.passport_area_code_list_item, null);
        }
        resultView.bind(getItem(position), (String) this.mSectionText.get(Integer.valueOf(position)));
        return resultView;
    }

    public Object[] getSections() {
        return this.mSections;
    }

    public int getPositionForSection(int section) {
        return this.mSectionToPosition[section];
    }

    public int getSectionForPosition(int position) {
        int p = 0;
        int i = 1;
        while (i < this.mSectionToPosition.length && this.mSectionToPosition[i] <= position) {
            p++;
            i++;
        }
        return p;
    }
}
