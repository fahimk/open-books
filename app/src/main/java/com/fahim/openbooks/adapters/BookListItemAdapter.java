package com.fahim.openbooks.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.fahim.openbooks.R;
import com.fahim.openbooks.retrofit.Feed;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Fahim on 8/10/2014.
 */
public class BookListItemAdapter extends ArrayAdapter<Feed.Entry> {
    private Activity mActivity;

    public BookListItemAdapter(Activity activity, List<Feed.Entry> items) {
        super(activity, R.layout.row, R.id.title, items);
        mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO: use viewholder
        View row = super.getView(position, convertView, parent);
        Feed.Entry entry = getItem(position);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        Picasso.with(mActivity).load(entry.map.get("http://opds-spec.org/image/thumbnail")).into(icon);
//.transform(new CircleTransform())
        return (row);
    }

    private class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            float radius = size / 2f;

            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawCircle(radius, radius, radius, paint);

            if (source != output) {
                source.recycle();
            }

            return output;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
