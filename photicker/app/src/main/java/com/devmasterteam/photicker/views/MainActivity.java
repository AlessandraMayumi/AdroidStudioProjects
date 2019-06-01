package com.devmasterteam.photicker.views;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.utils.ImageUtil;
import com.devmasterteam.photicker.utils.LongEventType;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    private static final int REQUEST_TAKE_PHOTO = 2;
    private final ViewHolder mViewHolder = new ViewHolder();
    private ImageView mImageSelected;
    private boolean mAutoIncrement;
    private LongEventType mLongEventType;
    private Handler mRepeatUpdateHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        List<Integer> mListImage = ImageUtil.getImagesList();

        this.mViewHolder.mRelativePhotoContent = (RelativeLayout) this.findViewById(R.id.relative_photo_content_draw);
        final LinearLayout content = (LinearLayout) this.findViewById(R.id.linear_horizontal_scroll_content);

        for (Integer imageId : mListImage) {
            ImageView image = new ImageView(this);

            image.setImageBitmap(ImageUtil.decodeSampledBitmapFromResource(getResources(), imageId, 70, 70));
            image.setPadding(20, 10, 20, 10);

            BitmapFactory.Options dimensions = new BitmapFactory.Options();
            dimensions.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), imageId, dimensions);

            final int width = dimensions.outWidth;
            final int height = dimensions.outHeight;

            image.setOnClickListener(onClickImageOption(this.mViewHolder.mRelativePhotoContent, imageId, width, height));

            content.addView(image);

            this.mViewHolder.mLinearSharePanel = (LinearLayout) this.findViewById(R.id.linear_share_panel);
            this.mViewHolder.mLinearControlPanel = (LinearLayout) this.findViewById(R.id.linear_control_panel);

            this.mViewHolder.mButtonZoonIn = (ImageView) this.findViewById(R.id.image_zoom_in);
            this.mViewHolder.mButtonZoonOut = (ImageView) this.findViewById(R.id.image_zoom_out);
            this.mViewHolder.mButtonRotateLeft = (ImageView) this.findViewById(R.id.image_rotate_left);
            this.mViewHolder.mButtonRotateRight = (ImageView) this.findViewById(R.id.image_rotate_right);
            this.mViewHolder.mButtonFinish = (ImageView) this.findViewById(R.id.image_finish);
            this.mViewHolder.mButtonRemove = (ImageView) this.findViewById(R.id.image_remove);

            this.mViewHolder.mDisplayPhoto = (ImageView) this.findViewById(R.id.image_photo);

            this.setListeners();
        }
    }


    private View.OnClickListener onClickImageOption(final RelativeLayout relativeLayout, final Integer imageId, int width, int height) {
        return new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
                final ImageView image = new ImageView(MainActivity.this);
                image.setBackgroundResource(imageId);
                relativeLayout.addView(image);
                mImageSelected = image;

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

                toogleControlPanel(true);

                image.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        float x, y;
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mImageSelected = image;
                                toogleControlPanel(true);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                int coords[] = {0, 0};
                                relativeLayout.getLocationOnScreen(coords);
                                x = event.getRawX() - (float) image.getWidth() / 2;
                                y = event.getRawY() - (coords[1] + (float) image.getHeight() / 2);
                                image.setX(x);
                                image.setY(y);
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                        }
                        return true;
                    }
                });
            }
        };
    }


    private void toogleControlPanel(boolean showControl) {
        if (showControl) {
            this.mViewHolder.mLinearSharePanel.setVisibility(View.GONE);
            this.mViewHolder.mLinearControlPanel.setVisibility(View.VISIBLE);
        } else {
            this.mViewHolder.mLinearSharePanel.setVisibility(View.VISIBLE);
            this.mViewHolder.mLinearControlPanel.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_take_photo:
                if(!PermissionUtil.hasCameraPermission(this)){
                    PermissionUtil.asksCameraPermission(this);
                }dispatchTakePictureIntent();
                break;
            case R.id.image_zoom_in:
                ImageUtil.handleZoomIn(this.mImageSelected);
                break;
            case R.id.image_zoom_out:
                ImageUtil.handleZoomOut(this.mImageSelected);
                break;
            case R.id.image_rotate_left:
                ImageUtil.handleRotateLeft(this.mImageSelected);
                break;
            case R.id.image_rotate_right:
                ImageUtil.handleRotateRight(this.mImageSelected);
                break;
            case R.id.image_finish:
                toogleControlPanel(false);
                break;
            case R.id.image_remove:
                this.mViewHolder.mRelativePhotoContent.removeView(this.mImageSelected);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PermissionUtil.CAMERA_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else{
                new AlertDialog.Builder(this)
                        .setMessage(getString(R.string.without_permission_camera_explanation))
                        .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        }
    }

    private void setListeners() {
        this.findViewById(R.id.image_take_photo).setOnClickListener(this);
        this.findViewById(R.id.image_zoom_in).setOnClickListener(this);
        this.findViewById(R.id.image_zoom_out).setOnClickListener(this);
        this.findViewById(R.id.image_rotate_left).setOnClickListener(this);
        this.findViewById(R.id.image_rotate_right).setOnClickListener(this);
        this.findViewById(R.id.image_finish).setOnClickListener(this);
        this.findViewById(R.id.image_remove).setOnClickListener(this);

        this.findViewById(R.id.image_zoom_in).setOnLongClickListener(this);
        this.findViewById(R.id.image_zoom_out).setOnLongClickListener(this);
        this.findViewById(R.id.image_rotate_left).setOnLongClickListener(this);
        this.findViewById(R.id.image_rotate_right).setOnLongClickListener(this);

        this.findViewById(R.id.image_zoom_in).setOnTouchListener(this);
        this.findViewById(R.id.image_zoom_out).setOnTouchListener(this);
        this.findViewById(R.id.image_rotate_left).setOnTouchListener(this);
        this.findViewById(R.id.image_rotate_right).setOnTouchListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        if(view.getId() == R.id.image_zoom_in) this.mLongEventType = LongEventType.ZoomIn;
        if(view.getId() == R.id.image_zoom_out) this.mLongEventType = LongEventType.ZoomOut;
        if(view.getId() == R.id.image_rotate_left) this.mLongEventType = LongEventType.RotateLeft;
        if(view.getId() == R.id.image_rotate_right) this.mLongEventType = LongEventType.RotateRight;
        new RptUpdater().run();
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();

        if (id == R.id.image_zoom_in || id == R.id.image_zoom_out || id == R.id.image_rotate_left || id == R.id.image_rotate_right) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP && mAutoIncrement) {
                mAutoIncrement = false;
                this.mLongEventType = null;
            } else {
                mAutoIncrement = true;
            }
        }
        view.performClick();
        return false;
    }


    private class RptUpdater implements Runnable{
        @Override
        public void run() {
            if(mAutoIncrement){
                mRepeatUpdateHandler.postDelayed(new RptUpdater(), 50);
            }
            if(mLongEventType != null){
                switch(mLongEventType){
                    case ZoomIn:
                        ImageUtil.handleZoomIn(mImageSelected);
                        break;
                    case ZoomOut:
                        ImageUtil.handleZoomOut(mImageSelected);
                        break;
                    case RotateLeft:
                        ImageUtil.handleRotateLeft(mImageSelected);
                        break;
                    case RotateRight:
                        ImageUtil.handleRotateRight(mImageSelected);
                        break;
                }
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mViewHolder.mDisplayPhoto.setImageBitmap(imageBitmap);
        }
    }

//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
//            File photoFile = null;
//            try{
//                photoFile = ImageUtil.createImageFile(this);
//            }catch (IOException e) {
//                Toast.makeText(this,"Não foi possível iniciar a camera", Toast.LENGTH_SHORT).show();
//            }
//            if(photoFile != null){
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }


    private static class ViewHolder {
        LinearLayout mLinearSharePanel;
        LinearLayout mLinearControlPanel;

        ImageView mButtonZoonIn;
        ImageView mButtonZoonOut;
        ImageView mButtonRotateLeft;
        ImageView mButtonRotateRight;
        ImageView mButtonFinish;
        ImageView mButtonRemove;

        RelativeLayout mRelativePhotoContent;
        ImageView mDisplayPhoto;
    }

}
