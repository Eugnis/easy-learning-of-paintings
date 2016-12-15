package com.eugnis.easylearningofpaintings;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArticleSelectActivity extends AppCompatActivity {

    OptionsChooseFragment mOptionsChooseFragment;
    ArticleChooseFragment mArticleChooseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_select);

        Intent intent = getIntent();
        String mode = intent.getStringExtra(MainActivity.MODE);

        Bundle bundle=new Bundle();
        bundle.putString("type", mode);

        mOptionsChooseFragment = new OptionsChooseFragment();
        mOptionsChooseFragment.setArguments(bundle);
        mArticleChooseFragment = new ArticleChooseFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.optionsChoose_fragment, mOptionsChooseFragment);
        transaction.add(R.id.articleChoose_fragment, mArticleChooseFragment);
        transaction.commit();
    }
}
