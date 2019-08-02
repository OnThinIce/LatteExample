package com.zlcp.latteec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlcp.latteui.recycler.DataConverter;
import com.zlcp.latteui.recycler.ItemType;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 1轮播图  2商品宫格 3商品分类图 4商品详情图 5 小单图 6广告 7文字
 */
public class IndexDataConverter extends DataConverter {

//    @Override
//    public ArrayList<MultipleItemEntity> convert() {
//        final JSONArray dataArray = JSON.parseObject(getmJsonData()).getJSONArray("data");
//        final int size = dataArray.size();
//        for (int i = 0; i < size; i++) {
//            final JSONObject data = dataArray.getJSONObject(i);
//            final int itemType = data.getInteger("type");
//            final String imageUrl = data.getString("imageUrl");
//            final String text = data.getString("text");
//            final int spanSize = data.getInteger("spanSize");
//            final int id = data.getInteger("goodsId");
//            final JSONArray banners = data.getJSONArray("banners");
//
//            final ArrayList<String> bannerImages = new ArrayList<>();
//            int type = 0;
//            switch (itemType) {
//                case 1:
//                    type = ItemType.BANNER;
//                    //banner初始化
//                    final int bannerSize = banners.size();
//                    for (int j = 0; j < bannerSize; j++) {
//                        final String banner = banners.getString(j);
//                        bannerImages.add(banner);
//                    }
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    type = ItemType.IMAGE;
//                    break;
//                case 4:
//                    type = ItemType.TEXT_IMAGE;
//                    break;
//                case 5:
//                    break;
//                case 6:
//                    break;
//                case 7:
//                    type = ItemType.TEXT;
//                    break;
//                default:
//                    break;
//            }
//
//            final MultipleItemEntity entity = MultipleItemEntity.builder()
//                    .setField(MultipleFields.ITEM_TYPE, type)
//                    .setField(MultipleFields.SPAN_SIZE, spanSize)
//                    .setField(MultipleFields.ID, id)
//                    .setField(MultipleFields.TEXT, text)
//                    .setField(MultipleFields.IMAGE_URL, imageUrl)
//                    .setField(MultipleFields.BANNERS, bannerImages)
//                    .build();
//            ENTITIES.add(entity);
//        }
//        return ENTITIES;
//    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
