package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.YellowPageImage;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.model.ExpressOrder;
import java.io.Serializable;

/* compiled from: SendExpressConfirmFragment */
public class U extends dF {
    private static int jj;
    private ExpressOrder cP;
    private TextView gK;
    private Button hO;
    private TextView jk;
    private TextView jl;
    private TextView jm;
    private ImageView jn;
    private dc jo;

    public void a(dc dcVar) {
        this.jo = dcVar;
    }

    public void onResume() {
        super.onResume();
        this.JP.k(false);
        this.JP.setTitle((int) R.string.express_order_confirmation);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (jj == 0) {
            jj = this.JP.getResources().getDimensionPixelSize(R.dimen.express_confirm_company_logo_size);
        }
        View inflate = layoutInflater.inflate(R.layout.send_express_confirm_fragment, viewGroup, false);
        this.hO = (Button) inflate.findViewById(R.id.submit);
        this.hO.setOnClickListener(new cx());
        this.gK = (TextView) inflate.findViewById(R.id.number);
        this.jk = (TextView) inflate.findViewById(R.id.src_city);
        this.jm = (TextView) inflate.findViewById(R.id.company);
        this.jl = (TextView) inflate.findViewById(R.id.dest_city);
        this.jn = (ImageView) inflate.findViewById(R.id.company_logo);
        Bundle arguments = getArguments();
        Serializable serializable = arguments.getSerializable("extra_express_order");
        a(arguments.getString("extra_express_order_company_logo_url"), this.jn);
        if (serializable != null) {
            this.cP = (ExpressOrder) serializable;
            b(this.cP);
        }
        return inflate;
    }

    private void b(ExpressOrder expressOrder) {
        this.gK.setText(expressOrder.au().getPhone());
        this.jk.setText(expressOrder.au().eV() + " " + expressOrder.au().eQ());
        this.jm.setText(expressOrder.as().ee());
        this.jl.setText(expressOrder.at().eV());
    }

    private void a(String str, ImageView imageView) {
        YellowPageImage yellowPageImage = new YellowPageImage(this.JP, str, jj, jj, ImageFormat.PNG);
        yellowPageImage.setImageProcessor(new V(this));
        YellowPageImgLoader.loadImage(this.JP, imageView, yellowPageImage, R.drawable.express_company_default_logo);
    }
}
