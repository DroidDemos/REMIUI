package com.google.android.play.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.play.R;
import com.google.android.play.image.AvatarCropTransformation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

class PlayDrawerAccountRow extends RelativeLayout {
    private TextView mAccountName;
    private FifeImageView mAvatar;

    public PlayDrawerAccountRow(Context context) {
        super(context);
    }

    public PlayDrawerAccountRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAvatar = (FifeImageView) findViewById(R.id.avatar);
        this.mAccountName = (TextView) findViewById(R.id.account_name);
    }

    public void bind(DocV2 accountDoc, String accountName, BitmapLoader bitmapLoader) {
        this.mAccountName.setText(accountName);
        setContentDescription(getResources().getString(R.string.play_drawer_content_description_switch_account, new Object[]{accountName}));
        if (accountDoc == null) {
            Bitmap avatarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_profile_none);
            this.mAvatar.setImageBitmap(AvatarCropTransformation.getNoRingAvatarCrop(getResources()).transform(avatarBitmap, avatarBitmap.getWidth(), avatarBitmap.getHeight()));
            return;
        }
        Image avatarImage = DocV2Utils.getFirstImageOfType(accountDoc, 4);
        this.mAvatar.setImage(avatarImage.imageUrl, avatarImage.supportsFifeUrlOptions, bitmapLoader);
    }
}
