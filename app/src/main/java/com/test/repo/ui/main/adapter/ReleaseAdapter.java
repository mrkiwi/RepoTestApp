package com.test.repo.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.repo.R;
import com.test.repo.data.network.model.ReleaseModel;

import java.util.List;

public class ReleaseAdapter extends BaseQuickAdapter<ReleaseModel, BaseViewHolder> {

	public ReleaseAdapter(int layoutResId, @Nullable List<ReleaseModel> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, ReleaseModel item) {
		helper.setText(R.id.tv_releaseName, item.getName());
		helper.setText(R.id.tv_author, "By " + item.getAuthor());
		helper.setText(R.id.tv_releaseDescr, item.getDescription());
	}
}
