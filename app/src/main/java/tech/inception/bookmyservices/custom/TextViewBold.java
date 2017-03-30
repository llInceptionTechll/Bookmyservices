package tech.inception.bookmyservices.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ghumman on 12/25/2016.
 */
public class TextViewBold extends TextView {
    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        String fontPath = "fonts/AvenirRoman.otf";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle,Typeface.BOLD);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        String fontPath = "fonts/AvenirRoman.otf";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle, Typeface.BOLD);
    }

    public TextViewBold(Context context) {
        super(context);

        String fontPath = "fonts/AvenirRoman.otf";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle,Typeface.BOLD);
    }
}
