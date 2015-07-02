package com.google.android.finsky.detailspage;

import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocDetails.BookDetails;
import com.google.android.finsky.utils.FastHtmlParser;

public class AboutAuthorTextModule extends TextModule {
    protected Data getData(Document detailsDoc, boolean hasDetailsLoaded) {
        CharSequence aboutAuthor;
        BookDetails bookDetails = detailsDoc.getBookDetails();
        if (bookDetails != null) {
            aboutAuthor = FastHtmlParser.fromHtml(bookDetails.aboutTheAuthor);
        } else {
            aboutAuthor = null;
        }
        if (TextUtils.isEmpty(aboutAuthor)) {
            return null;
        }
        Data data = new Data();
        data.backend = detailsDoc.getBackend();
        data.docType = detailsDoc.getDocumentType();
        data.callout = null;
        data.calloutGravity = 3;
        data.restrictCalloutMaxLines = false;
        data.bodyHeader = this.mContext.getResources().getString(R.string.details_about_author).toUpperCase();
        data.body = aboutAuthor;
        return data;
    }
}
