package com.example.kaylee.activityoptionsapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 参考：https://mp.weixin.qq.com/s/D4q-_EaqeNLPEEyJ0q0YEA
 * 步骤：
 * 1.指定theme
 * <style name="AppTheme" parent="Theme.AppCompat">
 * <item name="android:windowContentTransitions">true</item>
 * </style>
 * 2.定义动画ActivityOptionsCompat compat=ActivityOptionsCompat.makeCustomAnimation(MainActivity.this,R.anim.anim_in,R.anim.anim_out);
 * 3.使用ActivityCompat的startActivity()方法启动另一个界面，并传入动画
 * ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, Main2Activity.class), compat.toBundle());
 * 4.在退出的时候调用ActivityCompat.finishAfterTransition(this)进行退出动画。
 *
 * @Override public void onBackPressed() {
 * super.onBackPressed();
 * ActivityCompat.finishAfterTransition(this);
 * }
 * 5.共享动画：在两个界面的xml中设置android:transitionName，指定要共享元素。
 * ?6.android给我们提供了几种预设的动画方式，它们分别是
 * change_bounds
 * change_clip_bounds
 * change_transition
 * change_image_transition
 * change_scroll
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text);
        final TextView textView2 = (TextView) findViewById(R.id.text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己传入动画
//                ActivityOptionsCompat compat=ActivityOptionsCompat.makeCustomAnimation(MainActivity.this,R.anim.anim_in,R.anim.anim_out);
                //不断放大一个View，以达到切换界面
//                ActivityOptionsCompat compat=ActivityOptionsCompat.makeScaleUpAnimation(textView,textView.getWidth()/2,textView.getHeight()/2,0,0);
                //放大一个图片，以达到切换界面
//                ActivityOptionsCompat compat =ActivityOptionsCompat.makeThumbnailScaleUpAnimation(textView, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),textView.getWidth()/2,textView.getHeight()/2);
                //共享一个元素
//                ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,textView,"text");
                //共享多个元素
                Pair pair1 = Pair.create(textView, "text");
                Pair pair2 = Pair.create(textView2, "text2");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair1, pair2);

                ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, Main2Activity.class), compat.toBundle());
            }
        });
    }
}
