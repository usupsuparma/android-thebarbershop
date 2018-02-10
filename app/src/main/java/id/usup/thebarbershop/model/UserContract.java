package id.usup.thebarbershop.model;

import android.provider.BaseColumns;

/**
 * Created by Acer on 12/14/2017.
 */

public class UserContract {
    public static class User implements BaseColumns{
        public static final String TABLE_NAME ="user";
        public static final String COLUM_NAME_USER="name";
        public static final String COLUM_GENDER_USER="gender";
        public static final String COLUM_NOHP_USER="nohp";
        public static final String COLUM_EMAIL_USER="email";
        public static final String COLUM_PASSWORD_USER="password";
    }
}
