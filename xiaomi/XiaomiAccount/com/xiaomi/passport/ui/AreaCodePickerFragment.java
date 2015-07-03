package com.xiaomi.passport.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.xiaomi.account.R;
import com.xiaomi.passport.utils.PhoneNumUtil.CountryPhoneNumData;
import com.xiaomi.passport.widget.AlphabetFastIndexer;

public class AreaCodePickerFragment extends Fragment {
    public static final String KEY_COUNTRY_ISO = "country_iso";
    public static final String PICK_TARGET = "TARGET_DEFAULT";
    public static final String TAG = "AreaCodePickerFragment";
    protected Activity mActivity;
    protected AreaCodePickerAdapter mAdapter;
    private AlphabetFastIndexer mFastIndexer;
    protected int mIndexTarget;
    protected ListView mListView;
    protected String mPreviousThumb;
    protected View mRoot;

    protected void setupPickerUi(View root, Bundle data) {
        prepareAdapter(root, data);
        prepareListView(root);
        setupListView(root);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mRoot = inflater.inflate(R.layout.passport_area_code_picker_fragment, container, false);
        setupPickerUi(this.mRoot, getArguments());
        return this.mRoot;
    }

    protected void prepareListView(View root) {
        this.mListView = (ListView) root.findViewById(R.id.list);
    }

    protected CountryPhoneNumData getItem(int position) {
        return this.mAdapter.getItem(position);
    }

    protected void prepareAdapter(View root, Bundle data) {
        this.mAdapter = new AreaCodePickerAdapter(getActivity(), data);
    }

    protected void setupListView(View root) {
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(getListViewItemOnClickListener());
        this.mFastIndexer = (AlphabetFastIndexer) root.findViewById(R.id.fast_indexer);
        this.mFastIndexer.attatch(this.mListView);
        this.mFastIndexer.setVisibility(0);
        this.mListView.setOnScrollListener(this.mFastIndexer.decorateScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                String thumb = AreaCodePickerFragment.this.mAdapter.getSectionTitleForPostion(firstVisibleItem + 1);
                if (thumb != null && !TextUtils.equals(thumb, AreaCodePickerFragment.this.mPreviousThumb)) {
                    AreaCodePickerFragment.this.mFastIndexer.drawThumb(thumb);
                    AreaCodePickerFragment.this.mPreviousThumb = thumb;
                }
            }
        }));
    }

    protected OnItemClickListener getListViewItemOnClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CountryPhoneNumData data = AreaCodePickerFragment.this.getItem(position);
                Intent intent = new Intent();
                intent.putExtra(AreaCodePickerFragment.KEY_COUNTRY_ISO, data.countryISO);
                AreaCodePickerFragment.this.mActivity.setResult(-1, intent);
                AreaCodePickerFragment.this.mActivity.finish();
            }
        };
    }
}
