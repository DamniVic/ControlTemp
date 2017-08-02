package com.hs.damnicomniplusvic.controltemp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hs.damnicomniplusvic.controltemp.R;
import com.hs.damnicomniplusvic.controltemp.adapter.FloatWindowAdapter;
import com.hs.damnicomniplusvic.controltemp.bean.SongBean;
import com.hs.damnicomniplusvic.controltemp.service.FloatWindowService;
import com.hs.damnicomniplusvic.controltemp.service.PlayMusicOffline;
import com.hs.damnicomniplusvic.controltemp.service.PlayMusicOnline;
import com.hs.damnicomniplusvic.controltemp.util.AudioUtil;
import com.hs.damnicomniplusvic.controltemp.util.GetPathFromAlbum;
import com.hs.damnicomniplusvic.controltemp.util.PermissionsUtil;
import com.hs.damnicomniplusvic.controltemp.util.control.CallAction;
import com.hs.damnicomniplusvic.controltemp.util.control.CallView;
import com.hs.damnicomniplusvic.controltemp.util.control.ClockView;
import com.hs.damnicomniplusvic.controltemp.util.control.MessageView;
import com.hs.damnicomniplusvic.controltemp.util.control.NaviAction;
import com.hs.damnicomniplusvic.controltemp.util.control.OpenAppAction;
import com.hs.damnicomniplusvic.controltemp.util.control.OpenWebsite;
import com.hs.damnicomniplusvic.controltemp.util.control.ScheduleView;
import com.hs.damnicomniplusvic.controltemp.util.control.SearchAction;
import com.hs.damnicomniplusvic.controltemp.util.control.SearchApp;
import com.hs.damnicomniplusvic.controltemp.util.control.SearchMusic;
import com.hs.damnicomniplusvic.controltemp.util.control.SearchWeather;
import com.hs.damnicomniplusvic.controltemp.util.control.SendContacts;
import com.hs.damnicomniplusvic.controltemp.util.control.SendMessage;
import com.hs.damnicomniplusvic.controltemp.util.control.Translation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    @BindView(R.id.main_text)
    AppCompatEditText mAppCompatEditText;
    @BindView(R.id.play_pause)
    AppCompatButton mAppCompatButton;
    @BindView(R.id.main_recycle)
    RecyclerView mRecyclerView;
    private FloatWindowAdapter mFloatWindowAdapter;
    private boolean local = false;
    private List<String> mStringList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PermissionsUtil.checkAndRequestPermissions(this);
        mFloatWindowAdapter=new FloatWindowAdapter();
        mRecyclerView.setAdapter(mFloatWindowAdapter);
        init();
    }

    private void init(){
        String[] list=getResources().getStringArray(R.array.function);
        Collections.addAll(mStringList,list);
        mFloatWindowAdapter.setStringList(mStringList);
        mFloatWindowAdapter.setClickCallBack(new FloatWindowAdapter.ClickCallBack() {
            @Override
            public void click(String name) {
                switch (name){
                    case "打电话":
                        callaction();
                        break;
                    case "通话页面":
                        callview();
                        break;
                    case "短信页面":
                        messageview();
                        break;
                    case "导航":
                        navi();
                        break;
                    case "打开APP":
                        openappaction();
                        break;
                    case "打开网页":
                        openwebsite();
                        break;
                    case "创建闹钟":
                        clockcreate();
                        break;
                    case "打开日历":
                        schedulecreate();
                        break;
                    case "网页搜索":
                        searchaction();
                        break;
                    case "搜索APP":
                        searchapp();
                        break;
                    case "在线音乐":
                        searchMusic();
                        break;
                    case "查询天气":
                        searchweather();
                        break;
                    case "发送名片":
                        sendcontact();
                        break;
                    case "发送短信":
                        sendmessage();
                        break;
                    case "翻译":
                        translate();
                        break;
                    case "本地音乐":
                        playmusic();
                        break;
                    case "单首本地":
                        operatemusic();
                        break;
                    case "悬浮框":
                        Intent intent=new Intent(MainActivity.this, FloatWindowService.class);
                        startService(intent);
                        break;
                    default:
                        playmusic();
                        break;
                }
            }
        });
    }

    @OnClick(R.id.play_pause)
    public void click(View view) {
        String text = mAppCompatButton.getText().toString();
        if (!local) {
            if (text.equals("play")) {
                if (PlayMusicOnline.Start())
                    mAppCompatButton.setText("pause");
            } else {
                if (PlayMusicOnline.Pause())
                    mAppCompatButton.setText("play");
            }
        } else {
            if (text.equals("play")) {
                if (PlayMusicOffline.Start())
                    mAppCompatButton.setText("pause");
            } else {
                if (PlayMusicOffline.Pause())
                    mAppCompatButton.setText("play");
            }
        }
    }

    //播放本地媒体文件（大于3M）
    private void playmusic() {
        ArrayList<SongBean> list = AudioUtil.getAllSongBeans(this);
        for (SongBean bean : list
                ) {
            Log.i(TAG, "playmusic: " + bean.toString());
        }
        Intent intent = new Intent(this, PlayMusicOffline.class);
        intent.putParcelableArrayListExtra("song", list);
        startService(intent);
        local = true;
    }

    //选择本地音乐文件播放（单首）
    public void operatemusic() {
        String text = mAppCompatEditText.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, 1234);
    }

    //在线搜索歌曲并播放
    private void searchMusic() {
        String text = mAppCompatEditText.getText().toString();
        SearchMusic searchMusic = new SearchMusic(text, this);
        searchMusic.start();
        local = false;
    }

    //发送短信
    private void sendmessage() {
        String text = mAppCompatEditText.getText().toString();
        SendMessage sendMessage = new SendMessage("", "13251452365", text, this);
        sendMessage.start();
    }

    //发送名片
    private void sendcontact() {
        String text = mAppCompatEditText.getText().toString();
        SendContacts sendContacts = new SendContacts("安防", "暴船长", this);
        sendContacts.start();
    }

    //查询天气(遗留问题)
    private void searchweather() {
        String text = mAppCompatEditText.getText().toString();
        SearchWeather searchWeather = new SearchWeather(text, this);
        searchWeather.start();
    }

    //商场搜索APP
    private void searchapp() {
        String text = mAppCompatEditText.getText().toString();
        SearchApp searchApp = new SearchApp(text, this);
        searchApp.start();
    }

    //百度搜索
    private void searchaction() {
        String text = mAppCompatEditText.getText().toString();
        SearchAction searchAction = new SearchAction(text, this);
        searchAction.Search();
    }

    //添加一个备忘录
    private void schedulecreate() {
        String text = mAppCompatEditText.getText().toString();
        ScheduleView scheduleView = new ScheduleView("李二叔回家了", "李二叔想儿子了，所以回家了", this);
        scheduleView.start();
    }


    //设置一个闹钟
    private void clockcreate() {
        String text = mAppCompatEditText.getText().toString();
        ClockView clockView = new ClockView(8, 23, Calendar.SATURDAY, text, true, null, true, this);
        clockView.start();
    }

    //打开一个网址或者搜索
    private void openwebsite() {
        String text = mAppCompatEditText.getText().toString();
        OpenWebsite openWebsite = new OpenWebsite("www.baidu.com", text, this);
        openWebsite.start();
    }


    //根据APP名字打开APP
    private void openappaction() {
        String text = mAppCompatEditText.getText().toString();
        OpenAppAction openAppAction = new OpenAppAction("TSZTemp", this);
        openAppAction.start();
    }

    //打开短信
    private void messageview() {
        String text = mAppCompatEditText.getText().toString();
        MessageView messageView = new MessageView(this);
        messageView.start();
    }

    //打电话（person和code任意输入一个）
    private void callaction() {
        String text = mAppCompatEditText.getText().toString();
        CallAction callAction = new CallAction("", "13625412536", this);
        callAction.start();
    }

    //打开通话界面
    private void callview(){
        CallView callView=new CallView(this);
        callView.start();
    }

    //翻译(遗留问题)
    private void translate() {
        String text = mAppCompatEditText.getText().toString();
        Translation translation = new Translation("en", text, this);
        translation.start();
    }

    private void navi() {
        String text = mAppCompatEditText.getText().toString();
        NaviAction naviAction = new NaviAction(text, this);
        naviAction.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1234:
                if(null!=data) {
                    Uri uri = data.getData();
                    String path = GetPathFromAlbum.getPath(MainActivity.this, uri);
                    Log.i(TAG, "onActivityResult: " + uri);
//                Intent intent = new Intent(this,PlayMusicOnline.class);
//                intent.putExtra("uri",path);
//                startService(intent);

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(path), "audio/*");
                    startActivity(intent);

//                intent.addCategory(Intent.CATEGORY_APP_MUSIC);
//                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
//                intent.setData(Uri.parse(path));
//                startActivity(intent);
                    local = false;
                }
                break;
            default:
                break;
        }
    }
}
