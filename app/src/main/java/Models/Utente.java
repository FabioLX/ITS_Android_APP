package Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FABIO.ROSSI on 20/12/2017.
 */

public class Utente  implements Parcelable {
    private String id;
    private String mFirstname;
    private String mLastname;
    private Integer mAge;

    // region Constructor
    public Utente(String id, String name, String grade, Integer age) {
        this.id = id;
        this.mFirstname = name;
        this.mLastname = grade;
        this.mAge=age;
    }

    protected Utente(Parcel in) {
        id = in.readString();
        mFirstname = in.readString();
        mLastname = in.readString();
        mAge= in.readInt();
    }

    //endregion

    //region getter setter

    public  String getId(){
        return id;
    }
    public  Integer getAge(){
        return mAge;
    }
    public  String getName(){
        return mFirstname;
    }
    public  String getLastname(){
        return mLastname;
    }

    public void setAge(Integer age){
        this.mAge=age;

    }
    public void setId(String id){
        this.id= id;
    }
    public void setName(String name){
        this.mFirstname= name;
    }

    public void setLastname(String surname){
        this.mLastname= surname;
    }

    //region parcelable

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(mFirstname);
        parcel.writeString(mLastname);
        parcel.writeInt(mAge);

    }

    public static final Creator<Utente> CREATOR = new Creator<Utente>() {
        @Override
        public Utente createFromParcel(Parcel in) {
            return new Utente(in);
        }

        @Override
        public Utente[] newArray(int size) {
            return new Utente[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }





}