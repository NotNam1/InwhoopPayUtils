package com.inwhoop.paytool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.inwhoop.paytools.interfaces.OnRequestListener;
import com.inwhoop.paytools.module.alipay.AliPayTools;

public class MainActivity extends AppCompatActivity {

    String pays = "app_id=2017120100291353&biz_content=%7B%22out_trade_no%22%3A%2220171214145551582005%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22MAIDOU%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F47.96.10.126%3A8080%2Fmaidou%2Fpay%2Fno_auth_request_ali_notify&passback_params=%7B%22appId%22%3A%222017120100291353%22%2C%22orderId%22%3A%22183%22%2C%22orderNum%22%3A%2220171214145551582005%22%2C%22orderWay%22%3A%222%22%2C%22payMoney%22%3A%220.01%22%7D&sign_type=RSA2&timeout_express=15m×tamp=2017-12-14+14%3A57%3A05&version=1.0&sign=MPGAvJqhSItkRprKfuX0qZn7zuHaj5LonX5sfr0xWM3%2BIE%2FpNEzxczMIWx2Q2t03RtGq1r%2BOfW8R64YgHXVmzJNBW3DdEGP8gb5FTXsKdJtS0LqI9aU5wxlaLyqRdyMQkV2y7MqKqoYPIvVQ%2FpkFuPyCcL8H4mhRJEnXkjti%2FC%2BbaNyWm0E1%2FkS9dm1aR0iWjUBvvpPIXfMShKrIIFLlthgwl%2FvYvA9N37zqRjS2eFVOoJ8nn7yCNr1UXMx49sUq0vNUpBVosFQWOQGnHhu%2FTRgyw4TA0FIC58zoRMOBRuWhCWZqkqDZQ5jNb%2FEIs6p8gi%2FNuTpLy7e%2FMULjH7f9Lg%3D%3D"
;
    private Button zfb_btn;
    private Button wx_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zfb_btn = (Button) findViewById(R.id.zfb_btn);
        wx_btn = (Button) findViewById(R.id.wx_btn);
        zfb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AliPayTools.aliPay(MainActivity.this, pays, new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e("testAlipay", s);
//                JSONObject jsonObject;
//                try {
//                    jsonObject = new JSONObject(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                    }

                    @Override
                    public void onError(String s) {
                        Log.e("testAlipay", s);
//                JSONObject jsonObject;
//                try {
//                    jsonObject = new JSONObject(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                    }
                });
            }
        });
        wx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WechatPayTools.wechatPayApp(MainActivity.this,"wx3f2ff015f3bcfbaf",null,);
            }
        });

    }
}
