package tech.inception.bookmyservices.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ghumman on 12/25/2016.
 */
public class EditTextAvira extends EditText {

    public EditTextAvira(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextAvira(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextAvira(Context context) {
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