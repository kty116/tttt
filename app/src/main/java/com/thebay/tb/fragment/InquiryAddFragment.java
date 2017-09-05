package com.thebay.tb.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thebay.tb.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gun0912.tedbottompicker.TedBottomPicker;

import static android.content.ContentValues.TAG;

public class InquiryAddFragment extends Fragment implements Serializable {

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    @BindView(R.id.object_state_spinner)
    Spinner mObjectStateSpinner;
    @BindView(R.id.input_parent)
    LinearLayout mInputParent;
    @BindView(R.id.content_text)
    EditText mContentText;
    @BindView(R.id.context_edit_scroll_view)
    ScrollView mContentEditScrollView;

    @OnClick({R.id.img_add_button, R.id.cancel_button, R.id.confirm_button})
    void click(View v) {
        switch (v.getId()) {
            case R.id.img_add_button:

//                getFile();
                TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(getActivity())
                        .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                            @Override
                            public void onImagesSelected(ArrayList<Uri> uriList) {
                                // here is selected uri list
                                int position = mUploadFiles.size();
                                for (int i = 0; i < uriList.size(); i++) {
                                    Log.d(TAG, "onImagesSelected: " + uriList.get(i));
                                    File file = new File(uriList.get(i).toString());
//                                }
//                                for (Uri uri : uriList) {
//                                    File file = Utils.getFileForUri(uriList.get(i));
                                    mUploadFiles.put(position, file);
                                    addCustomImageInputView(file.getName(), position);
                                    Log.d(TAG, "onActivityResult: " + mUploadFiles.get(0));
                                    position++;
                                }
                                Log.d(TAG, "onActivityResult: " + mUploadFiles.size());
                                mUploadFileCount = mUploadFiles.size();
                            }
                        })
                        .setPeekHeight(1600)
                        .showTitle(false)
                        .setPreviewMaxCount(1000)
                        .setSelectMaxCount(10)
                        .setSpacing(10)
                        .setCompleteButtonText("확인")
                        .setEmptySelectionText("No Select")
                        .create();

                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager());
                break;

            case R.id.cancel_button:
                getActivity().onBackPressed();
                break;

            case R.id.confirm_button:
                for (int i = 0; i < mUploadFileCount; i++) {
                    if (mUploadFiles.get(i) != null) {
                        Log.d("dddddddd", "onCreate: " + mUploadFiles.get(i));
                        //최종 선택 한 파일 목록!!
                    }
                }
                //데이터 보내기
                break;
        }
    }

    private LinearLayout mFileInputLayout;
    private Map<Integer, File> mUploadFiles;
    private ArrayList<File> mUploadFilesList;
    private int mUploadFileCount = 0;
    private Unbinder unbinder;

    public static InquiryAddFragment newInstance() {
        InquiryAddFragment fragment = new InquiryAddFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("1:1문의 작성");
        View view = inflater.inflate(R.layout.fragment_inquiry_add, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mUploadFiles = new HashMap<>();
        mUploadFilesList = new ArrayList<>();

        mContentEditScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mContentText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
//            // Use the provided utility method to parse the result
//            List<Uri> files = Utils.getSelectedFilesFromResult(data);
//            int position = mUploadFiles.size();
////            for (Uri uri : files) {
//            for (int i = 0; i < files.size(); i++) {
//                Uri uri = Uri.parse(files.get(i).toString());
//                File file = Utils.getFileForUri(uri);
//                mUploadFiles.get(i);
////                Log.d(TAG, "onActivityResult: " + file.getName());
//                addCustomImageInputView(file.getName(), position);
//                Log.d(TAG, "onActivityResult: " + mUploadFiles.get(0));
//                position++;
//                // Do something with the result...
//            }
//            Log.d(TAG, "onActivityResult: " + mUploadFiles.size());
//        }
//    }


//    public void getFile() {
//        // This always works
////        Intent i = new Intent(getActivity(), FilePickerActivity.class);
//        // This works if you defined the intent filter
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//
//        // Set these depending on your use case. These are the defaults.
//        i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, true);
//        i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
//        i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
//
//        // Configure initial directory by specifying a String.
//        // You could specify a String like "/storage/emulated/0/", but that can
//        // dangerous. Always use Android's API calls to get paths to the SD-card or
//        // internal memory.
//        i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
//
//        startActivityForResult(i, FILE_CODE);
//    }

    public void addCustomImageInputView(String imgTitle, final int position) {
        final View view = LayoutInflater.from(mInputParent.getContext()).inflate(R.layout.view_file_input, mInputParent, false);
        mFileInputLayout = (LinearLayout) view.findViewById(R.id.image_input_layout);
        TextView fileText = (TextView) view.findViewById(R.id.file_name_text);
        ImageButton fileRemoveButton = (ImageButton) view.findViewById(R.id.file_remove_button);
        fileText.setText(imgTitle);

        fileRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadFiles.remove(position);
                mInputParent.removeView(view);
            }
        });

        mInputParent.addView(view);

    }

//    public void getHttp(String relativeUrl, RequestParams params) throws JSONException {
//
////        RequestParams params = new RequestParams();
////        params.put("key","value");
//
//        TaobaoRestClient.get(relativeUrl, params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // 받아온 JSONObject 자료 처리
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                // 받아온 JSONArray 자료 처리
//            }
//
//            @Override
//            public void onFinish() {
//                // 끝나면 호출 되는 메소드
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                // 통신 실패시 호출 되는 메소드
//            }
//        });
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
