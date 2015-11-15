package hearsilent.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	ArrayList<String> arrayList = new ArrayList<>();
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
		for (int i = 0; i < 100; i++) {
			arrayList.add(Integer.toString(i));
		}
		/** Normal **/
		//listView.setAdapter(
		//		new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList));
		/** Custom **/
		listView.setAdapter(new CustomAdapter());

	}

	private class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return arrayList.size();
		}

		@Override
		public Object getItem(int position) {
			return arrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
				convertView = inflater.inflate(R.layout.list_custom, parent, false);

				holder = new ViewHolder();
				holder.textView_title = (TextView) convertView.findViewById(R.id.textView_title);
				holder.textView_content =
						(TextView) convertView.findViewById(R.id.textView_content);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.textView_title.setText(arrayList.get(position));
			holder.textView_content.setText("Test " + arrayList.get(position));

			return convertView;
		}

		class ViewHolder {
			TextView textView_title;
			TextView textView_content;
		}
	}
}
