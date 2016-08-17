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

	public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

		private final static int TYPE_1 = 0, TYPE_2 = 1;

		public class ItemViewHolder2 extends RecyclerView.ViewHolder {

			TextView titleTextView;
			TextView fromTextView;
			TextView dateTextView;

			public ItemViewHolder2(View itemView) {
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

						mArrayList
								.add(getAdapterPosition(), Integer.toString(getAdapterPosition()));
						notifyItemInserted(getAdapterPosition());
					}
				});
			}
		}

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
						notifyItemRemoved(getAdapterPosition());
					}
				});
			}
		}

		@Override
		public int getItemViewType(int position) {
			return position % 3 == 0 ? TYPE_2 : TYPE_1;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			if (viewType == TYPE_1) {
				return new ItemViewHolder(LayoutInflater.from(parent.getContext())
						.inflate(R.layout.list_custom, parent, false));
			} else {
				return new ItemViewHolder2(LayoutInflater.from(parent.getContext())
						.inflate(R.layout.list_custom2, parent, false));
			}
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			int viewType = getItemViewType(position);

			if (viewType == TYPE_1) {
				ItemViewHolder viewHolder = (ItemViewHolder) holder;
				viewHolder.titleTextView.setText(mArrayList.get(position));
				viewHolder.fromTextView.setText(
						String.format(Locale.getDefault(), "Test %s", mArrayList.get(position)));
				viewHolder.dateTextView.setText("");
			} else {
				ItemViewHolder2 viewHolder = (ItemViewHolder2) holder;
				viewHolder.titleTextView.setText(mArrayList.get(position));
				viewHolder.fromTextView.setText(
						String.format(Locale.getDefault(), "Test %s", mArrayList.get(position)));
				viewHolder.dateTextView.setText("");
			}
		}

		@Override
		public int getItemCount() {
			return mArrayList.size();
		}

	}
}
