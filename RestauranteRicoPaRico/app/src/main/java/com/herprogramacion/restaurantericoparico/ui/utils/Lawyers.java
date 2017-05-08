package com.herprogramacion.restaurantericoparico.ui.utils;

import android.provider.BaseColumns;

/**
 * Created by Sergio on 08/05/2017.
 */

public class Lawyers {
    public static abstract class LawyerEntry implements BaseColumns {
        public static final String TABLE_NAME ="lawyer";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String RATING = "rating";

    }
}
