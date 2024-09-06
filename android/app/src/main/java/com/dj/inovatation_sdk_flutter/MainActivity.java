package com.dj.inovatation_sdk_flutter;

import io.flutter.embedding.android.FlutterActivity;
import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.innovation.animal.breedfunctionsdk.SDK;
import com.innovation.animal.breedfunctionsdk.bean.InsuredEntranceBean;
import com.innovation.animal.breedfunctionsdk.callback.LoadingListener;
import com.innovation.animal.breedfunctionsdk.callback.MessageListener;
import com.innovation.animal.breedfunctionsdk.utils.DialogUtil;
import com.innovation.animal.breedfunctionsdk.utils.ToastUtils;
import com.innovation.animal.breedfunctionsdk.widget.LoadingView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        SDK.setMessageListener(new MessageListener() {
            @Override
            public void showMessage(String msg) {
                super.showMessage(msg);
                ToastUtils.error(msg);
            }

            @Override
            public void showErrorMessage(String msg) {
                super.showErrorMessage(msg);
                ToastUtils.error(msg);
            }
        });
        SDK.setLoadingListener(new LoadingListener() {
            @Override
            public void showLoading() {
                DialogUtil.getInstance().show(MainActivity.this, new LoadingView(MainActivity.this));
            }

            @Override
            public void hideLoading() {
                DialogUtil.getInstance().dismiss();
            }
        });
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "sdk_test_channel").setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                switch (call.method) {
                    case "insureStart":
                        SDK.insuredEntrance(GsonUtils.fromJson(
                                "{" +
                                        "\"deptCode\": \"20221021LNSFGS/COMP20230209HBSFGS\",\n" +
                                        "\"deptName\": \"大家财险SDK/河北省分公司\",\n" +
                                        "\"insuranceId\":\"112233441003\"," +
                                        "\"standardInspectionWay\":13," +
                                        "\"riskCode\":\"202302905\"," +
                                        "\"riskName\":\"辽宁中央政策性奶牛养殖保险\"," +
                                        "\"clauseCode\":\"2023029005\"," +
                                        "\"clauseName\":\"大家财险辽宁中央财政奶牛养殖保险\"," +
                                        "\"animalType\":2," +
                                        "\"livestockBreeds\":204," +//  203 育肥舍 204 能繁舍
                                        "\"insuranceMethod\":\"1\"," +
                                        "\"enterprisesId\":\"2409020001\"," +
                                        "\"enterprisesNames\":\"wayne-personal\"," +
                                        "\"breedMethod\":\"1\"," +
                                        "\"createUserId\":\"20240902000101\"," +
                                        "\"createUserName\":\"大家sdk机构号101\"," +
                                        "\"longitude\": \"116.414777\"," +
                                        "\"latitude\": \"40.03988\"," +
                                        "\"address\": \"中国北京朝阳立水桥office\"," +
                                        "\"province\": \"北京市\"," +
                                        "\"city\": \"北京市\"," +
                                        "\"county\": \"朝阳区\"," +
                                        "\"town\": \"北苑路\"," +
                                        "\"village\": \"立水桥\"" +
                                        "}", InsuredEntranceBean.class), MainActivity.this);
                        result.success("Android " + android.os.Build.VERSION.RELEASE);
                        break;
                }
            }
        });
    }
}
