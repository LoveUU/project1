package com.example.testmodifine;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.own.eoeliberay.entity.CategorysEntity;
import cn.own.eoeliberay.indicator.TitlePageIndicator;
import cn.own.eoeliberay.slidingmenu.SlidingMenu;
import cn.own.eoeliberay.ui.base.BaseSlidingFragmentActivity;
import cn.own.eoeliberay.utils.PopupWindowUtil;

public class MainActivity extends BaseSlidingFragmentActivity implements
	OnClickListener, AnimationListener {

	private SlidingMenu sm;
	private ViewPager mViewPager;
	private ImageView imageview_above_more;
	private LinearLayout Linear_above_toHome;
	private TextView tv_above_title;
	private TitlePageIndicator above_indicator;
	private ViewPager above_pager;
	private BasePageAdapter adapter;
	private ImageView imgLeft;
	private ImageView imgRight;
	private LinearLayout view_loading;
	private FrameLayout indicatorFrameLayout;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSlidingMenu();
		setContentView(R.layout.activity_main);
		
		mViewPager = (ViewPager) findViewById(R.id.above_pager);
		Linear_above_toHome = (LinearLayout) findViewById(R.id.Linear_above_toHome);
		Linear_above_toHome.setOnClickListener(this);
		imageview_above_more = (ImageView) findViewById(R.id.imageview_above_more);
		imageview_above_more.setOnClickListener(this);
		above_indicator = (TitlePageIndicator) findViewById(R.id.above_indicator);
		above_pager = (ViewPager) findViewById(R.id.above_pager);
		view_loading = (LinearLayout) findViewById(R.id.view_loading);
		indicatorFrameLayout = (FrameLayout) findViewById(R.id.indicatorFrameLayout);
		adapter = new BasePageAdapter(this);
		List<String> mList = new ArrayList<String>();
		mList.add("第一个");
		mList.add("第二个");
		mList.add("第三个");
		List<String> listObject = new ArrayList<String>();
		listObject.add("1");
		listObject.add("2");
		listObject.add("3");
		//indicatorFrameLayout.setVisibility(View.GONE);   //不显示导航栏
		adapter.addFragment(mList, listObject);
		mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(adapter);
		above_indicator.setViewPager(mViewPager);
		above_indicator.setOnPageChangeListener(new MyPageChangeListener());
		mViewPager.setVisibility(View.VISIBLE);
		view_loading.setVisibility(View.GONE);
		
		tv_above_title = (TextView) findViewById(R.id.tv_above_title);
		tv_above_title.setText("列表");
		
		tabs.add(new CategorysEntity("测试1", "www.baidu.com"));
		tabs.add(new CategorysEntity("测试2", "www.baidu.com"));
		tabs.add(new CategorysEntity("测试3", "www.baidu.com"));

		imgLeft = (ImageView) findViewById(R.id.imageview_above_left);
		imgRight = (ImageView) findViewById(R.id.imageview_above_right);
	}

	private void initSlidingMenu() {
		setBehindContentView(R.layout.behind_slidingmenu);
		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);	//设置菜单与主体之间的阴影宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);   //设置菜单显示后主体显示宽度
		sm.setFadeDegree(0.35f);	//透明度改变
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); //菜单显示触摸模式
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);	//设置菜单与主体之间阴影颜色
		//sm.setShadowWidth(20);	//设置菜单与主体之间的阴影宽度
		sm.setBehindScrollScale(0.5f);	//设置菜单显示开始结束处
	}
	
	@Override
	public void onAnimationStart(Animation animation) {
	}
	@Override
	public void onAnimationEnd(Animation animation) {
	}
	@Override
	public void onAnimationRepeat(Animation animation) {
	}
	boolean isShowPopupWindows = true;

	public List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Linear_above_toHome:
			showMenu();
			break;
		case R.id.imageview_above_more:
			if (isShowPopupWindows) {
				new PopupWindowUtil(mViewPager).showActionWindow(v, this,
						tabs);
			}
			break;
		}
	}
	class MyPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			if (arg0 == 0) {
				getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_FULLSCREEN);
				imgLeft.setVisibility(8);
			} else if (arg0 == adapter.mFragments.size() - 1) {
				imgRight.setVisibility(8);
				getSlidingMenu()
						.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			} else {
				imgRight.setVisibility(0);
				imgLeft.setVisibility(0);
				getSlidingMenu()
						.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			}
		}
	}
}
