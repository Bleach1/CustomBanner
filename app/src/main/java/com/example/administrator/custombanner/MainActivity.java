package com.example.administrator.custombanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.custombanner.banner.BannerHolder;
import com.example.administrator.custombanner.banner.LJNCBViewHolderCreator;
import com.example.administrator.custombanner.banner.LJNConvenientBanner;
import com.example.administrator.custombanner.banner.LJNOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LJNConvenientBanner banner;
    private List<String> strings = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strings.add("http://uploads.5068.com/allimg/140212/28_140212141737_1.jpg");
        strings.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/16/c2/20980524_1368673584062.jpg");
        strings.add("http://www.deskier.com/uploads/allimg/160821/1-160R1064H0.jpg");
        strings.add("http://img3.duitang.com/uploads/blog/201306/29/20130629034050_imJQT.jpeg");
        strings.add("http://2t.5068.com/uploads/allimg/151023/48-151023160136.jpg");
        banner = findViewById(R.id.banner);
        banner.setPages(new LJNCBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerHolder();
            }
        }, strings).setPageIndicator(new int[]{R.drawable.point_sel, R.drawable.point_nos})
                .setPageIndicatorAlign(LJNConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new LJNOnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this, "item" + position, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.startTurning(3000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.stopTurning();
        }
    }

}
