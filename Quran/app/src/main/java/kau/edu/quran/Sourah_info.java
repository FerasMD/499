package kau.edu.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class Sourah_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sourah_info);

     ExpandableTextView expandableTextView= findViewById(R.id.ex);
        //TabItem tabItem=(TabItem)findViewById(R.id.mon);
        expandableTextView.setText(getIntent().getStringExtra("about"));


        ExpandableTextView expandableTextView1= findViewById(R.id.ex1);
        //TabItem tabItem=(TabItem)findViewById(R.id.mon);
        expandableTextView1.setText("Copyright 2014 Manabu Shimobe\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");


        ExpandableTextView expandableTextView2= findViewById(R.id.ex2);
        //TabItem tabItem=(TabItem)findViewById(R.id.mon);
        expandableTextView2.setText("Copyright 2014 Manabu Shimobe\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");


    }
}