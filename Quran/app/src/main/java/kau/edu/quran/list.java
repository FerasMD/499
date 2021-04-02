package kau.edu.quran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class list extends ArrayAdapter<String> {
    private int layout;


    public list(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        layout = resource;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewholder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
            viewHolder.button = (ImageButton) convertView.findViewById(R.id.list_item_btn);
            convertView.setTag(viewHolder);
        }
        mainViewholder = (ViewHolder) convertView.getTag();
        ViewHolder finalMainViewholder = mainViewholder;

        mainViewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputStream is = null;
                try {
                    is = getContext().getAssets().open("sourah_Info.xml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;
                try {
                    db = dbf.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                Document doc = null;
                try {
                    doc = db.parse(is);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                NodeList nodeList = doc.getElementsByTagName("ROW");

                String sname = "";

                Sourah s = new Sourah();
                sname = (String) finalMainViewholder.title.getText();


                ArrayList<String> arrayList = s.displayDetails(nodeList, sname);
                String about = arrayList.get(0);
                String res = arrayList.get(1);


                getContext().startActivity(new Intent(getContext().getApplicationContext(), Sourah_info.class).putExtra("about", about).putExtra("res", res));
            }


        });
        mainViewholder.title.setText(getItem(position));


        return convertView;
    }
}

class ViewHolder {

    TextView title;

    ImageButton button;
}


