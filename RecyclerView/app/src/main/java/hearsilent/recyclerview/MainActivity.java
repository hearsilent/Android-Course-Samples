package hearsilent.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	RecyclerView mRecyclerView;

	ArrayList<String> mArrayList = new ArrayList<>();

	RecyclerListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		setUpViews();
	}

	private void findViews() {
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
	}

	private void setUpViews() {
		for (int i = 0; i < 100; i++) {
			mArrayList.add(Integer.toString(i));
		}

		mAdapter = new RecyclerListAdapter();
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(
				new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
		mRecyclerView.setAdapter(mAdapter);
	}

	public class RecyclerListAdapter
			extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder> {

		public class ItemViewHolder extends RecyclerView.ViewHolder {

			TextView titleTextView;
			TextView fromTextView;
			TextView dateTextView;

			public ItemViewHolder(View itemView) {
				super(itemView);
				titleTextView = (TextView) itemView.findViewById(R.id.textView_title);
				fromTextView = (TextView) itemView.findViewById(R.id.textView_from);
				dateTextView = (TextView) itemView.findViewById(R.id.textView_date);

				itemView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						if (getAdapterPosition() == -1) {
							return;
						}

						mArrayList.remove(getAdapterPosition());
						notifyDataSetChanged();
					}
				});
			}
		}

		@Override
		public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new ItemViewHolder(LayoutInflater.from(parent.getContext())
					.inflate(R.layout.list_custom, parent, false));
		}

		@Override
		public void onBindViewHolder(ItemViewHolder holder, int position) {
			holder.titleTextView.setText(mArrayList.get(position));
			holder.fromTextView.setText(
					String.format(Locale.getDefault(), "Test %s", mArrayList.get(position)));
			holder.dateTextView.setText("");
		}

		@Override
		public int getItemCount() {
			return mArrayList.size();
		}

	}
}
