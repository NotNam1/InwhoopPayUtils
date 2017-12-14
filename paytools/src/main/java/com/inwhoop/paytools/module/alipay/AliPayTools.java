package com.inwhoop.paytools.module.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.inwhoop.paytools.interfaces.OnRequestListener;

import java.util.Map;

public class AliPayTools {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private static OnRequestListener sOnRequestListener;
    //    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        //        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                    //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        sOnRequestListener.onSuccess(resultStatus);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        sOnRequestListener.onError(resultStatus);
                    }
                    break;
                }
                default:
                    break;
            }


//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
////                        ToastUtil.TextToast(context,"支付成功");
////                        startActivity(new Intent(context,PaymentOkActivity .class));
////                        finish();
////                        PayActivityToPaySuccessActivity(2);
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
////                        ToastUtil.TextToast(context,"支付失败");
//                    }
//                    break;
//                }
//                case SDK_AUTH_FLAG: {
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
////                        ToastUtil.TextToast(context,"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
//                    } else {
//                        // 其他状态值则为授权失败
////                        ToastUtil.TextToast(context,"授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }



        }
    };

    public static void aliPay(final Activity activity, final String ali_info, OnRequestListener onRxHttp1) {
        sOnRequestListener = onRxHttp1;
//        Map<String, String> params = AliPayOrderInfoUtil.buildOrderParamMap(appid, isRsa2, aliPayModel.getOut_trade_no(), aliPayModel.getName(), aliPayModel.getMoney(), aliPayModel.getDetail());
//        String orderParam = AliPayOrderInfoUtil.buildOrderParam(params);
//
//        String privateKey = alipay_rsa_private;
//
//        String sign = AliPayOrderInfoUtil.getSign(params, privateKey, isRsa2);
//        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(ali_info.trim(), true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
