package com.example.testmodifine;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import cn.own.eoeliberay.entity.CategorysEntity;
import cn.own.eoeliberay.view.HttpErrorFragment;

public class BasePageAdapter extends FragmentStatePagerAdapter {

	public ArrayList<Fragment> mFragments = new ArrayList<Fragment>();;
	public List<String> tabs = new ArrayList<String>();

	private Activity mActivity;

	public BasePageAdapter(FragmentActivity activity) {
		super(activity.getSupportFragmentManager());
		this.mActivity = activity;
	}

	/**
	 * 加载listview和tabs
	 * @param listObject
	 */
	public void addFragment(List<String> mList, List<String> listObject) {
		tabs.addAll(mList);
		for (int i = 0; i < listObject.size(); i++) {
			String object = listObject.get(i);
			if (object.equals("1")) {
				addTab(new OneFragme());
			}
			if (object.equals("2")) {
				addTab(new TwoFragme());
			}
			if (object.equals("3")) {
				addTab(new ThreeFragme());
			}
		}
	}
	/**
	 * 只加载listview不加载tabs
	 * @param listObject
	 */
	public void addFragment( List<String> listObject) {

		for (int i = 0; i < listObject.size(); i++) {
			String object = listObject.get(i);
			if (object.equals("1")) {
				addTab(new OneFragme());
			}
			if (object.equals("2")) {
				addTab(new TwoFragme());
			}
			if (object.equals("3")) {
				addTab(new ThreeFragme());
			}
		}
	}

	public void addNullFragment() {
		String cate = "连接错误";
		tabs.add(cate);
		addTab(new HttpErrorFragment());
	}

	public void Clear() {
		mFragments.clear();
		tabs.clear();
	}

	public void addTab(Fragment fragment) {
		mFragments.add(fragment);
		notifyDataSetChanged();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		if (tabs.size() == 0) {
			return "";
		}
		return tabs.get(position);
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	//

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
}
