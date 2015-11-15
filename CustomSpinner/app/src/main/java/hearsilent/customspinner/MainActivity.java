package hearsilent.customspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private String[] dinner = {"雞腿飯", "魯肉飯", "排骨飯", "水餃", "陽春麵"};
	Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinner = (Spinner) findViewById(R.id.spinner);
		/** Normal **/
		//spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
		// dinner));
		/** Custom **/
		spinner.setAdapter(new CustomAdapter());
	}

	private class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return dinner.length;
		}

		@Override
		public Object getItem(int position) {
			return dinner[position];
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

			holder.textView_title.setText(dinner[position]);
			holder.textView_content.setText("Test " + dinner[position]);

			return convertView;
		}

		class ViewHolder {
			TextView textView_title;
			TextView textView_content;
		}
	}
}
