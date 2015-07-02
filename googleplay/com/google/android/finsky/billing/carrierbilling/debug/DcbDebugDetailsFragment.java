package com.google.android.finsky.billing.carrierbilling.debug;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.vending.R;
import java.util.Collection;

public class DcbDebugDetailsFragment extends DialogFragment {
    public static DcbDebugDetailsFragment create(DcbDetailExtractor detailExtractor, String title) {
        Collection<DcbDetail> details = detailExtractor.getDetails();
        String[] detailArray = new String[details.size()];
        int index = 0;
        for (DcbDetail detail : details) {
            int index2 = index + 1;
            detailArray[index] = detail.getTitle() + ": " + detail.getValue();
            index = index2;
        }
        Bundle arguments = new Bundle();
        arguments.putStringArray("formatted_details", detailArray);
        arguments.putString("title", title);
        DcbDebugDetailsFragment fragment = new DcbDebugDetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = new ContextThemeWrapper(getActivity(), R.style.FinskyDialogTheme);
        Bundle arguments = getArguments();
        String[] details = arguments.getStringArray("formatted_details");
        String title = arguments.getString("title");
        Builder builder = new Builder(context);
        builder.setPositiveButton(R.string.ok, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.setTitle(title);
        AlertDialog dialog = builder.create();
        ListView dialogView = new ListView(context);
        dialogView.setAdapter(new ArrayAdapter(context, R.layout.dcb_debug_details_dialog_item, details));
        dialog.setView(dialogView);
        return dialog;
    }
}
