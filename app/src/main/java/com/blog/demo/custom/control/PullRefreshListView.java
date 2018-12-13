package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PullRefreshListView extends ListView {
	private final static String LOGTAG = "PullRefreshListView";

	private final static int DONE 				= -1;
	private final static int PULL_TO_REFRESH	= 1; // 下拉刷新
	private final static int RELEASE_TO_REFRESH	= 2; // 放手刷新
	private final static int REFRESHING			= 3; // 刷新
	
	private final static int RATIO				= 3;
	
	private int mHeadState = DONE;
	
	private View mViewHead;
	private ProgressBar mPbHead;
	private TextView mTvHead;
	
	private int mHeadContentHeight;
	
	private float mDownY;
	private boolean mRecord;
	private int mFirstVisibleItem;
	
	private boolean mRefreshable;
	private OnPullToRefreshListener mPullToRefreshListener;
	
	public PullRefreshListView(Context context) {
		this(context, null);
	}

	public PullRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setFadingEdgeLength(0);
		initHeadView(context);
		
		setOnScrollListener(new OnPullToRefreshScrollListener());
	}
	
	private void initHeadView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		mViewHead = inflater.inflate(R.layout.listview_head, this, false);
		measureView(mViewHead);
		
		mHeadContentHeight = mViewHead.getMeasuredHeight();
		mViewHead.setPadding(0, -1 * mHeadContentHeight, 0, 0);
		mViewHead.invalidate();
		addHeaderView(mViewHead, null, false);
		
		mPbHead = (ProgressBar) mViewHead.findViewById(R.id.pb_head);
		mTvHead = (TextView) mViewHead.findViewById(R.id.tv_head);
	}
	
	private void measureView(View child) {
		ViewGroup.LayoutParams lp = child.getLayoutParams();
		if (lp == null) {
			lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
					ViewGroup.LayoutParams.WRAP_CONTENT); 
		}
		
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
		int lpHeight = lp.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!mRecord) {
				mDownY = ev.getY();
				mRecord = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (!mRecord) {
				mDownY = ev.getY();
				mRecord = true;
			}
			
			if (mFirstVisibleItem == 0 && mRefreshable) {
				refreshHeadView(ev);
			} else {
				mDownY = ev.getY();
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mFirstVisibleItem == 0 && mRefreshable) {
				refreshHeadView(ev);
			}
			mRecord = false;
		}
		
		return super.onTouchEvent(ev);
	}
	
	private void refreshHeadView(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:
			float distance = ev.getY() - mDownY;
			LogUtil.log(LOGTAG, "distance = " + distance);
			
			if (mHeadState != REFRESHING && mRecord) {
				if (mHeadState == DONE) {
					if (distance > 0) {
						mHeadState = PULL_TO_REFRESH;
						changeHeadState();
					}
				} 
				if (mHeadState == PULL_TO_REFRESH) {
					if (distance <= 0) {
						mHeadState = DONE;
						changeHeadState();
					} else if (distance >= RATIO * mHeadContentHeight) {
						distance = RATIO * mHeadContentHeight;
						mHeadState = RELEASE_TO_REFRESH;
						changeHeadState();
					}
				}
				
				if (mHeadState == RELEASE_TO_REFRESH) {
					if (distance < RATIO * mHeadContentHeight) {
						mHeadState = PULL_TO_REFRESH;
						changeHeadState();
					}
				}
				
				if (mHeadState == PULL_TO_REFRESH || mHeadState == RELEASE_TO_REFRESH) {
					mViewHead.setPadding(0, (int)(distance / RATIO - mHeadContentHeight), 0, 0);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mHeadState == RELEASE_TO_REFRESH) {
				mHeadState = REFRESHING;
				changeHeadState();
				
				if (mPullToRefreshListener != null)
					mPullToRefreshListener.onPullToRefresh();
			} else if (mHeadState == PULL_TO_REFRESH){
				mHeadState = DONE;
				changeHeadState();
			}
		}
	}
	
	private void changeHeadState() {
		switch(mHeadState) {
			case PULL_TO_REFRESH:
				mPbHead.setVisibility(View.GONE);
				mTvHead.setText("下拉刷新");
				break;
			case RELEASE_TO_REFRESH:
				mPbHead.setVisibility(View.GONE);
				mTvHead.setText("释放刷新");
				break;
			case REFRESHING:
				mPbHead.setVisibility(View.VISIBLE);
				mTvHead.setText("正在刷新");
				mViewHead.setPadding(0, 0, 0, 0);
				break;
			case DONE:
				mViewHead.setPadding(0, -1 * mHeadContentHeight, 0, 0);
				break;

		}
	}
	
	public void done() {
		mHeadState = DONE;
		changeHeadState();
	}
	
	public void setOnPullToRefreshListener(OnPullToRefreshListener listener) {
		this.mPullToRefreshListener = listener;
		this.mRefreshable = (listener != null);
	}
	
	public static interface OnPullToRefreshListener {
		public void onPullToRefresh();
	}
	
	public class OnPullToRefreshScrollListener implements OnScrollListener {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			mFirstVisibleItem = firstVisibleItem;
			LogUtil.log(LOGTAG, "FirstVisibleItem = " + mFirstVisibleItem);
		}
		
	}

}
