package com.xiaoxiukj.jhan.androidtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private BoomMenuButton bmb;
    FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initFruits();
//        adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//        super.registerForContextMenu(listView);

        ImageView img = (ImageView) findViewById(R.id.image_view);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.drawable.app)).getBitmap();
        int w = bitmap.getWidth(), h = bitmap.getHeight();
        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int [] resultPixes=OpenCVHelper.gray(pix,w,h);
        Log.d("my error", "here");
        Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        result.setPixels(resultPixes, 0, w, 0, 0,w, h);
//        img.setImageBitmap(result);
        Glide.with(this).load(R.drawable.app).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_1);
//        bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
        bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.bat));


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.menuheadtitle);
        menu.add(Menu.NONE, Menu.FIRST+1, 0, R.string.deleteoperation);
        menu.add(Menu.NONE, Menu.FIRST+2, 0, R.string.canceloperation);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {// 选择监听
        AdapterView.AdapterContextMenuInfo acmiRef = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();// 用来获取item信息哎，重要
        int removeIndex = acmiRef.position;
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                fruitList.remove(removeIndex);
                adapter.notifyDataSetChanged();// 删除后刷新ListView

                break;
            case Menu.FIRST + 2:

                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.exitDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitDialog() {
        Dialog dialog=new AlertDialog.Builder(this).setTitle("程序退出？").setMessage("确定退出吗？").setPositiveButton("退出", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        }).create();//创建Dialog
        dialog.show();//显示对话框

    }

    private void initFruits() {
        Fruit appleFruit = new Fruit("Apple", R.drawable.apple);
        fruitList.add(appleFruit);
        Fruit bananaFruit = new Fruit("Banana", R.drawable.apple);
        fruitList.add(bananaFruit);
        Fruit orangeFruit = new Fruit("Orange", R.drawable.apple);
        fruitList.add(orangeFruit);
        Fruit waterFruit = new Fruit("pear", R.drawable.apple);
        fruitList.add(waterFruit);
        Fruit kkFruit = new Fruit("pear", R.drawable.apple);
        fruitList.add(kkFruit);
        Fruit yyFruit = new Fruit("pear", R.drawable.apple);
        fruitList.add(yyFruit);
    }

    public void gotoDialogActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DialogActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        menu.add(Menu.NONE, Menu.FIRST+1, 0, "test set");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())//得到被点击的item的itemId
        {
            case Menu.FIRST+1: //对应的ID就是在add方法中所设定的Id
                System.out.println("test");
                break;
            case Menu.FIRST+2:
                System.out.println("error");
                break;
            case R.id.menu_settings:
                System.out.println("settings");
                break;
        }
        return true;
    }
}
