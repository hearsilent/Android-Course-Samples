package hearsilent.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	ViewPager mViewPager;
	TabLayout mTabLayout;
	Toolbar mToolbar;

	String[] test = new String[]{"雞肉飯", "滷肉飯", "牛肉麵"};

	MessagesPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAdapter = new MessagesPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setAdapter(mAdapter);

		// TabLayout
		mTabLayout = (TabLayout) findViewById(R.id.tabs_main);
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		// Toolbar
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		// Anim
		mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

			@Override
			public void transformPage(View page, float position) {
				int width = page.getWidth();

				TextView textView2 = (TextView) page.findViewById(R.id.textView2);
				textView2.setTranslationX(position * width);
				textView2.setAlpha(1 - Math.abs(position));
			}
		});

	}

	private class MessagesPagerAdapter extends FragmentPagerAdapter {

		public MessagesPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new DemoFragment().newInstance(position);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return test[position];
		}
	}
}
