package com.socialnetwork.Database;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.socialnetwork.Fragments.GroupFragment;
import com.socialnetwork.Objects.GroupObj;
import com.socialnetwork.Utils.Constants;
import com.socialnetwork.Utils.PreferencesManager;

import java.util.ArrayList;

import static com.socialnetwork.Utils.Constants.listGroup;

public class FirebaseManager {

    private static final String TAG = "FirebaseManager";
    private static FirebaseManager instance;
    private static DatabaseReference reference;
    private static Context context;
    private static PreferencesManager preferencesManager;
    static GroupObj group = new GroupObj();
    static ArrayList<GroupObj> arrayGroup = new ArrayList<>();

   // private static PreferenceMovilixaManager pmm;

    public FirebaseManager(){
        Context context;
    }

    public static synchronized void initializeInstance(Context c) {
        if (instance == null) {
            instance = new FirebaseManager();
            context = c;
            preferencesManager = new PreferencesManager(c);
        }
    }

    public static synchronized FirebaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(FirebaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    public static void saveUser(){

        String userId = "";
        final String firebaseUserId = "dfgdfg";
        if (reference == null) {
            reference = FirebaseDatabase.getInstance().getReference();
        }

        if(!preferencesManager.getUserId().isEmpty()){
            userId = preferencesManager.getUserId();
        }

        DatabaseReference.goOnline();
        reference
                .child(Constants.NODE_USERS)
                .child(userId)
                .child(Constants.NODE_USERS_INFORMATION)
                .child(Constants.NODE_USERS_EMAIL)
                .setValue(preferencesManager.getUserEmail(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Log.e(TAG, "Sync GROUP con FIREBASE success!");

                        } else {
                            Log.e(TAG, "Sync GROUP FIREBASE failed: " + databaseError.getMessage());
                        }

                        if(reference != null) {
                            DatabaseReference.goOffline();
                            reference = null;
                        }
                    }
                });
        DatabaseReference.goOnline();
        reference
                .child(Constants.NODE_USERS)
                .child(userId)
                .child(Constants.NODE_USERS_INFORMATION)
                .child(Constants.NODE_USERS_NAME)
                .setValue(preferencesManager.getUserName(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Log.e(TAG, "Sync GROUP con FIREBASE success!");

                        } else {
                            Log.e(TAG, "Sync GROUP FIREBASE failed: " + databaseError.getMessage());
                        }

                        if(reference != null) {
                            DatabaseReference.goOffline();
                            reference = null;
                        }
                    }
                });
    }

    public static void getGroups() {

        String userID = preferencesManager.getUserId();


     //   listGroup = new ArrayList<>();

        if(reference == null){
            reference = FirebaseDatabase.getInstance().getReference();
        }

        if (!userID.isEmpty()) {
            reference
                    .child(Constants.NODE_GROUP)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.e(TAG, "Get USER (FIREBASE) success!");

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                group.setCode(snapshot.getKey());
                                group.setName(snapshot.getValue().toString());
                                arrayGroup.add(group);
                                listGroup.add(group);
                                Log.e("s",snapshot.getKey()+ snapshot.getValue().toString());
                                group = new GroupObj();
//                                GroupFragment.
                            }
                            reference.goOffline();
                            reference = null;
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            reference.goOffline();
                            reference = null;
                        }
                    });
        }
    }
}