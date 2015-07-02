package com.miui.yellowpage.activity;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import java.io.IOException;

/* compiled from: LoginActivity */
class f implements AccountManagerCallback {
    final /* synthetic */ LoginActivity ir;

    f(LoginActivity loginActivity) {
        this.ir = loginActivity;
    }

    public void run(AccountManagerFuture accountManagerFuture) {
        try {
            if (((Bundle) accountManagerFuture.getResult()).getBoolean("booleanResult")) {
                this.ir.sendBroadcast(new Intent("com.miui.yellowpage.action.YELLOWPAGE_LOGIN"));
                this.ir.gR();
            }
        } catch (OperationCanceledException e) {
            e.printStackTrace();
            this.ir.setResult(0);
            this.ir.finish();
        } catch (AuthenticatorException e2) {
            e2.printStackTrace();
            this.ir.setResult(0);
            this.ir.finish();
        } catch (IOException e3) {
            e3.printStackTrace();
            this.ir.setResult(0);
            this.ir.finish();
        }
    }
}
