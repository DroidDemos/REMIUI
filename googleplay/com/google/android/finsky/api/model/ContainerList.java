package com.google.android.finsky.api.model;

import com.google.android.finsky.protos.DocumentV2.DocV2;
import java.util.List;

public abstract class ContainerList<T> extends PaginatedList<T, Document> {
    private Document mContainerDocument;

    protected ContainerList(String url) {
        super(url);
    }

    protected ContainerList(String url, boolean autoLoadNextPage) {
        super(url, autoLoadNextPage);
    }

    protected ContainerList(List<UrlOffsetPair> urlList, int currentCount, boolean autoLoadNextPage) {
        super(urlList, currentCount, autoLoadNextPage);
    }

    public Document getContainerDocument() {
        return this.mContainerDocument;
    }

    public int getBackendId() {
        return getBackendId(0);
    }

    public int getBackendId(int defaultBackendId) {
        if (this.mContainerDocument != null) {
            return this.mContainerDocument.getBackend();
        }
        return defaultBackendId;
    }

    protected Document[] updateContainerAndGetItems(DocV2 containerDoc) {
        if (containerDoc == null) {
            return new Document[0];
        }
        this.mContainerDocument = new Document(containerDoc);
        int numChildren = containerDoc.child.length;
        Document[] documentArr = new Document[numChildren];
        for (int i = 0; i < numChildren; i++) {
            documentArr[i] = new Document(containerDoc.child[i]);
        }
        return documentArr;
    }
}
