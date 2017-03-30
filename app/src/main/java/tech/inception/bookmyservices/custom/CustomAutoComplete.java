package tech.inception.bookmyservices.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by ghumman on 3/4/2017.
 */

public class CustomAutoComplete extends AutoCompleteTextView {

    public CustomAutoComplete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomAutoComplete(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirRoman.otf");
            setTypeface(tf);
        }
    }

}