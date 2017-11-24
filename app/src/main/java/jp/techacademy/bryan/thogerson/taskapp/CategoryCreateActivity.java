package jp.techacademy.bryan.thogerson.taskapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoryCreateActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mNameEdit;
    private Category mCategory;

    private View.OnClickListener mOnDoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCategory();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);

        // ActionBarを設定する
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // UI部品の設定
        findViewById(R.id.done_button).setOnClickListener(mOnDoneClickListener);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra(InputActivity.EXTRA_CATEGORY, -1);
        Realm realm = Realm.getDefaultInstance();
        mCategory = realm.where(Category.class).equalTo("id", categoryId).findFirst();
        realm.close();

        mNameEdit = (EditText) findViewById(R.id.name_edit_text);
    }

    private void addCategory() {
        String name = mNameEdit.getText().toString();

        if(name != null){
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            if (mCategory == null) {
                // 新規作成の場合
                mCategory = new Category();

                RealmResults<Category> categoryRealmResults = realm.where(Category.class).findAll();

                int identifier;
                if (categoryRealmResults.max("id") != null) {
                    identifier = categoryRealmResults.max("id").intValue() + 1;
                } else {
                    identifier = 0;
                }
                mCategory.setId(identifier);
            }

            mCategory.setName(name);

            realm.copyToRealmOrUpdate(mCategory);
            realm.commitTransaction();

            realm.close();
        }
    }
}