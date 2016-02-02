package com.epsilon.coders.phms.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    // database name and version
    private static final String DATABASE_NAME = "iCare.db";
    private static final int DATABASE_VERSION = 16;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ICare Profile Table
	public static final String I_CARE_PROFILE = "icareprofiles";
	public static final String COL_ICARE_PROFILE_ID = "id";
    public static final String COL_ICARE_PROFILE_IMAGE = "image";
    public static final String COL_ICARE_PROFILE_NAME = "name";
	public static final String COL_ICARE_PROFILE_AGE = "age";
	public static final String COL_ICARE_PROFILE_BLOOD_GROUP = "blood_group";
	public static final String COL_ICARE_PROFILE_WEIGHT = "weight";
	public static final String COL_ICARE_PROFILE_HEIGHT = "height";
	public static final String COL_ICARE_PROFILE_DATE_OF_BIRTH = "dateofbirth";
	public static final String COL_ICARE_PROFILE_SPECIAL_NOTES = "special_notes";

	// ICare Activity Chart Table
	public static final String ICARE_DIET_CHART = "icaredietchart";
	public static final String COL_ICARE_DIET_ID = "diet_id";
	public static final String COL_ICARE_DIET_DATE = "date";
	public static final String COL_ICARE_DIET_TIME = "time";
	public static final String COL_ICARE_DIET_FOOD_MENU = "food_menu";
	public static final String COL_ICARE_DIET_EVENT_NAME = "event_name";
	public static final String COL_ICARE_DIET_ALARM = "set_alarm";

    // ICare VACCINATION Chart Table
    public static final String ICARE_VACCINATION_CHART = "icarevaccinationchart";
    public static final String COL_ICARE_VACCINATION_ID = "vaccination_id";
    public static final String COL_ICARE_VACCINATION_DATE = "date";
    public static final String COL_ICARE_VACCINATION_TIME = "time";
    public static final String COL_ICARE_VACCINATION_DETAILS = "vaccination_details";
    public static final String COL_ICARE_VACCINATION_NAME = "vaccination_name";
    public static final String COL_ICARE_VACCINATION_ALARM = "set_alarm";


    // ICare Doctor Profile Table
    public static final String I_CARE_DOCTOR_PROFILE = "icaredoctorprofiles";
    public static final String COL_ICARE_DOCTOR_PROFILE_ID = "_id";
    public static final String COL_ICARE_DOCTOR_PROFILE_NAME = "dname";
    public static final String COL_ICARE_DOCTOR_PROFILE_QUALIFICATION = "qualification";
    public static final String COL_ICARE_DOCTOR_PROFILE_DESIGNATION = "designation";
    public static final String COL_ICARE_DOCTOR_PROFILE_EXPERTISE  = "expertise";
    public static final String COL_ICARE_DOCTOR_PROFILE_ORGANIZATION = "organization";
    public static final String COL_ICARE_DOCTOR_PROFILE_CHAMBER = "chamber";
    public static final String COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS = "visitinghours";
    public static final String COL_ICARE_DOCTOR_PROFILE_LOCATION = "location";
    public static final String COL_ICARE_DOCTOR_PROFILE_PHONE = "phone";
    public static final String COL_ICARE_DOCTOR_PROFILE_EMAIL = "email";

	// ICare careinfo  Table
	public static final String I_CARE_CAREINFO_TBL = "careinfotbl";
	public static final String COL_CAREINFO_TBL_ID = "_id";
	public static final String COL_CAREINFO_TBL_CARE_NAME = "carename";
	public static final String COL_CAREINFO_TBL_CARE_ADDRESS = "careaddress";
	public static final String COL_CAREINFO_TBL_CONTACT_NUMBER = "contactnumber";
	public static final String COL_CAREINFO_TBL_EMAIL  = "email";

    //ICare note Table
    public static  final String I_CARE_NOTE_TBL ="notetbl";
    public static final String COL_NOTE_TBL_ID = "_id";
    public static final String COL_NOTE_TBL_NAME ="notename";
    public static final String COL_NOTE_TBL_DETAIL ="notedetail";
    public static final String COL_NOTE_TBL_DATE ="notedate";
    public static final String COL_NOTE_TBL_TIME ="notetime";
    public static final String COL_NOTE_TBL_USER_ID ="noteuserid";




	// Database creation sql statement
	private static final String DATABASE_CREATE_PROFILE = "create table "
			+ I_CARE_PROFILE + "( " + COL_ICARE_PROFILE_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_ICARE_PROFILE_NAME + " text not null," + " "
			+ COL_ICARE_PROFILE_AGE + " text not null," + " "
			+ COL_ICARE_PROFILE_BLOOD_GROUP + " text not null," + " "
			+ COL_ICARE_PROFILE_WEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_HEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_DATE_OF_BIRTH + " text not null," + " "
			+ COL_ICARE_PROFILE_SPECIAL_NOTES + " text not null," + " "
            +COL_ICARE_PROFILE_IMAGE + " BLOB);";

    // Database creation sql statement
    private static final String DATABASE_CREATE_DOCTOR_PROFILE = "create table "
            + I_CARE_DOCTOR_PROFILE + "( " + COL_ICARE_DOCTOR_PROFILE_ID
            + " integer primary key autoincrement, " + " "
            + COL_ICARE_DOCTOR_PROFILE_NAME + " text not null," + " "
            + COL_ICARE_DOCTOR_PROFILE_QUALIFICATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_DESIGNATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_EXPERTISE + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_ORGANIZATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_CHAMBER + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_VISITING_HOURS + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_LOCATION + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_PHONE + " text," + " "
            + COL_ICARE_DOCTOR_PROFILE_EMAIL + " text);";

	private static final String DATABASE_CREATE_DACTIVITY = "create table "
			+ ICARE_DIET_CHART + "(" + COL_ICARE_DIET_ID
			+ " integer primary key autoincrement, " + COL_ICARE_DIET_DATE
			+ " text not null," + COL_ICARE_DIET_TIME + " text not null,"
			+ COL_ICARE_DIET_FOOD_MENU + " text not null,"
			+ COL_ICARE_DIET_EVENT_NAME + " text not null,"
			+ COL_ICARE_DIET_ALARM + " text not null);";


    private static final String DATABASE_CREATE_VACTIVITY = "create table "
            + ICARE_VACCINATION_CHART + "(" + COL_ICARE_VACCINATION_ID
            + " integer primary key autoincrement, " + COL_ICARE_VACCINATION_DATE
            + " text not null," + COL_ICARE_VACCINATION_TIME + " text not null,"
            + COL_ICARE_VACCINATION_DETAILS + " text not null,"
            + COL_ICARE_VACCINATION_NAME + " text not null,"
            + COL_ICARE_VACCINATION_ALARM + " text not null);";


    // CAREINFO table create quiry
    private static final String DATABASE_CREATE_CAREINFO = "create table "
            + I_CARE_CAREINFO_TBL + "(" + COL_CAREINFO_TBL_ID
            + " integer primary key autoincrement, "
            + COL_CAREINFO_TBL_CARE_NAME + " text not null,"
            + COL_CAREINFO_TBL_CARE_ADDRESS + " text not null,"
            + COL_CAREINFO_TBL_CONTACT_NUMBER + " text ,"
            + COL_CAREINFO_TBL_EMAIL + " text );";


    //NOTE table create quary
    private static final String DATABASE_CREATE_NOTE = "create table "
            + I_CARE_NOTE_TBL + "(" + COL_NOTE_TBL_ID
            + " integer primary key autoincrement, "
            +COL_NOTE_TBL_DATE+"text,"
            +COL_NOTE_TBL_TIME+"text,"
            + COL_NOTE_TBL_NAME + " text not null,"
            + COL_NOTE_TBL_DETAIL + " text not null,"
            +COL_NOTE_TBL_USER_ID+ " text);";

    //Important Note
//ICare note Table
    public static  final String TBL_NOTE ="important_note_tb2";
    public static final String KEY_NOTE_TITLE = "title";
    public static final String KEY_TITLE_DATE="date";
    public static final String KEY_TITLE_DESCRIPTION ="notedetail";

    private static final String DATABASE_CREATE_NOTE_QUIRE = "create table "
            + TBL_NOTE + "(" + "_id"
            + " integer primary key autoincrement, "
            + KEY_NOTE_TITLE + " text not null,"
            + KEY_TITLE_DATE + " text not null,"
            + KEY_TITLE_DESCRIPTION + " text );";

// Create family profile

    public static final String TBL_I_CARE_FAMILY_PROFILE = "icare_family_profiles";
    public static final String COL_ICARE_FAMILY_PROFILE_ID = "f_id";
    public static final String COL_ICARE_FAMILY_PROFILE_NAME = "f_name";
    public static final String COL_ICARE_FAMILY_PROFILE_NUMBER = "f_number";
    public static final String COL_ICARE_FAMILY_PROFILE_RELATIONSHIP = "f_relationship";
    public static final String COL_ICARE_FAMILY_PROFILE_AGE = "fage";
    public static final String COL_ICARE_FAMILY_PROFILE_BLOOD_GROUP = "f_blood_group";
    public static final String COL_ICARE_FAMILY_PROFILE_WEIGHT = "f_weight";
    public static final String COL_ICARE_FAMILY_PROFILE_HEIGHT = "f_height";
    public static final String COL_ICARE_FAMILY_PROFILE_DATE_OF_BIRTH = "f_dateofbirth";
    public static final String COL_ICARE_FAMILY_PROFILE_SPECIAL_NOTES = "f_special_notes";

    private static final String DATABASE_CREATE_FAMILY_PROFILE = "create table "
            + TBL_I_CARE_FAMILY_PROFILE + "(" +COL_ICARE_FAMILY_PROFILE_ID
            + " integer primary key autoincrement, "
            + COL_ICARE_FAMILY_PROFILE_NAME + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_NUMBER + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_RELATIONSHIP + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_AGE + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_BLOOD_GROUP + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_WEIGHT + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_HEIGHT + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_DATE_OF_BIRTH + " text not null,"
            + COL_ICARE_FAMILY_PROFILE_SPECIAL_NOTES + " text );";


    // family diet list details

    // ICare Family Diet Chart Table
    public static final String TBL_FAMILY_DIET_CHART = "icarefamilydietchart";
    public static final String COL_ICARE_FAMILY_DIET_ID = "family_diet_id";
    public static final String COL_ICARE_FAMILY_DIET_DATE = "date";
    public static final String COL_ICARE_FAMILY_DIET_TIME = "time";
    public static final String COL_ICARE_FAMILY_DIET_DETAILS = "family_diet_details";
    public static final String COL_ICARE_FAMILY_DIET_NAME = "family_diet_name";
    public static final String COL_ICARE_FAMILY_DIET_ALARM = "set_alarm";


    private static final String DATABASE_CREATE_FAMILY_DIET = "create table "
            + TBL_FAMILY_DIET_CHART + "(" + COL_ICARE_FAMILY_DIET_ID
            + " integer primary key autoincrement, " + COL_ICARE_FAMILY_DIET_DATE
            + " text not null," + COL_ICARE_FAMILY_DIET_TIME + " text not null,"
            + COL_ICARE_FAMILY_DIET_DETAILS + " text not null,"
            + COL_ICARE_FAMILY_DIET_NAME + " text not null,"
            + COL_ICARE_FAMILY_DIET_ALARM + " text not null);";

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_PROFILE);
		database.execSQL(DATABASE_CREATE_DACTIVITY);
        database.execSQL(DATABASE_CREATE_DOCTOR_PROFILE);
        database.execSQL(DATABASE_CREATE_CAREINFO);
        database.execSQL(DATABASE_CREATE_NOTE);
        database.execSQL(DATABASE_CREATE_NOTE_QUIRE);
        database.execSQL(DATABASE_CREATE_FAMILY_PROFILE);
        database.execSQL(DATABASE_CREATE_VACTIVITY);
        database.execSQL(DATABASE_CREATE_FAMILY_DIET);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + I_CARE_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_DIET_CHART);
        db.execSQL("DROP TABLE IF EXISTS " + I_CARE_DOCTOR_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + I_CARE_CAREINFO_TBL);
        db.execSQL("DROP TABLE IF EXISTS " + I_CARE_NOTE_TBL);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_I_CARE_FAMILY_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + ICARE_VACCINATION_CHART);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_FAMILY_DIET_CHART);
		onCreate(db);
	}

}
