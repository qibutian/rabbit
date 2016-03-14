package com.means.rabbit.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.means.rabbit.R;
import com.means.rabbit.activity.merchants.DaiGouActivity;
import com.means.rabbit.activity.merchants.FoodListActivity;
import com.means.rabbit.base.RabbitBaseActivity;

/**
 * 全部分类
 * 
 * @author Administrator
 * 
 */
public class AllItemActivity extends RabbitBaseActivity {

	// RelativeLayout hot_halal,

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_item);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.all_item));

	}

	public void allItemClick(View view) {
		Intent it;
		switch (view.getId()) {
		/** 热门 */
		// 清真美食
		case R.id.hot_halal:
			it = new Intent(self, FoodListActivity.class);
			startActivity(it);
			break;
		// 旅游攻略
		case R.id.hot_travel:
			break;
		// 代购
		case R.id.hot_hotboom:
			it = new Intent(self, DaiGouActivity.class);
			startActivity(it);
			break;
		// 潜水
		case R.id.hot_diving:
			break;
		/** 美食 */
		// 全部
		case R.id.food_all:
			break;
		// 清真美食
		case R.id.food_halal:
			break;
		// 咖啡
		case R.id.food_coffee:
			break;
		// 酒吧
		case R.id.food_bar:
			break;
		// 其他
		case R.id.food_other:
			break;

		/** 酒店 */

		// 全部
		case R.id.grogshop_all:
			break;

		// 星级酒店
		case R.id.grogshop_starred_hotel:
			break;

		// 旅馆
		case R.id.grogshop_hotel:
			break;

		// 度假村
		case R.id.grogshop_vacational_village:
			break;
		default:
			break;
		}

	}

}
