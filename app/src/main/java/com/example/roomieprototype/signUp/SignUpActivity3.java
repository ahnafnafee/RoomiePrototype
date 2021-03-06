//Name: Sign-Up Activity 3
//Purpose: This code asks the users to select answers to prompt, like study habits, in order to eventually match them with other users based on their answers.
//Version 5

package com.example.roomieprototype.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomieprototype.CardStack;
import com.example.roomieprototype.MatchingScreen;
import com.example.roomieprototype.R;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity3 extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseUser user;

    private String sleepSchedule;
    private String cleanSchedule;
    private String eatSchedule;
    private String studySchedule;
    private String socialSchedule;
    private String temperatureSchedule;
    private String apartmentSchedule;
    private String dormSchedule;
    private ChipGroup sleepChips, eatChips, cleanChips, studyChips, dormChips, apartmentChips, socialChips, temperatureChips;
    private TextView chipError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_3);

        // firebase database initiated
        db = FirebaseFirestore.getInstance();

        // firebase auth initiated
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // firebase user initiated
        user = mAuth.getCurrentUser();

        // initializing error textview
        chipError = findViewById(R.id.chips_error);

        // initializing ChipGroups
        sleepChips = findViewById(R.id.sleep_chips);
        eatChips = findViewById(R.id.eat_chips);
        cleanChips = findViewById(R.id.clean_chips);
        studyChips = findViewById(R.id.study_chips);
        dormChips = findViewById(R.id.dorm_chips);
        apartmentChips = findViewById(R.id.apartment_chips);
        socialChips = findViewById(R.id.social_chips);
        temperatureChips = findViewById(R.id.temperature_chips);

        //Button Action
        Button btnSignUp = findViewById(R.id.signup_reg_button);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;

                // checking if all the chipgroups have a selected chip
                if (sleepChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select a sleeping Trait.");
                } else if (eatChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select an eating trait.");
                } else if (cleanChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select a cleaning trait.");
                } else if (studyChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select a studying trait.");
                } else if (dormChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select if you want a dorm or a suite.");
                } else if (apartmentChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select if you have an apartment or not.");
                } else if (socialChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select a social trait");
                } else if (temperatureChips.getCheckedChipId() == View.NO_ID) {
                    cancel = true;
                    chipError.setText("Please select a temperature trait.");
                }

                if (!cancel) {
                    //Switch to MainActivity
                    Intent myIntent = new Intent(getBaseContext(), CardStack.class);
                    startActivity(myIntent);

                    //Add Sleep Schedule to Firebase
                    sleepFireBase();

                    //Add Clean Schedule to Firebase
                    cleanFireBase();

                    //Add Eat Schedule to Firebase
                    eatFireBase();

                    //Add Study Schedule to Firebase
                    studyFireBase();

                    //Add Social to Firebase
                    socialFireBase();

                    //Add Temperature to Firebase
                    temperaureFireBase();

                    //Add apartment to firebase
                    apartmentFireBase();

                    //Add dorm to firebase
                    dormFireBase();

                    //Switch to MainActivity
                    Intent myIntent2 = new Intent(getBaseContext(), MatchingScreen.class);
                    startActivity(myIntent2);
                }


            }
        });
    }

    private void sleepFireBase() {
        ChipGroup sleepGroup = findViewById(R.id.sleep_chips);

        if (sleepGroup.getCheckedChipId() == R.id.night_owl) sleepSchedule = "Night Owl";
        else if (sleepGroup.getCheckedChipId() == R.id.early_bird) sleepSchedule = "Early Bird";
        else if (sleepGroup.getCheckedChipId() == R.id.sleep_depends)
            sleepSchedule = "Depends on the day";

        Map<String, Object> sleep = new HashMap<>();
        sleep.put("sleep", sleepSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void cleanFireBase() {
        ChipGroup cleanGroup = findViewById(R.id.clean_chips);

        if (cleanGroup.getCheckedChipId() == R.id.neat_freak) cleanSchedule = "Neat Freak";
        else if (cleanGroup.getCheckedChipId() == R.id.rel_neat) cleanSchedule = "Relatively Neat";
        else if (cleanGroup.getCheckedChipId() == R.id.rel_messy)
            cleanSchedule = "Relatively Messy";
        else if (cleanGroup.getCheckedChipId() == R.id.messy) cleanSchedule = "Messy";

        Map<String, Object> sleep = new HashMap<>();
        sleep.put("clean", cleanSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void eatFireBase() {
        ChipGroup eatGroup = findViewById(R.id.eat_chips);

        if (eatGroup.getCheckedChipId() == R.id.veg) eatSchedule = "Vegetarian";
        else if (eatGroup.getCheckedChipId() == R.id.vegan) eatSchedule = "Vegan";
        else if (eatGroup.getCheckedChipId() == R.id.kosher) eatSchedule = "Kosher";
        else if (eatGroup.getCheckedChipId() == R.id.halal) eatSchedule = "Halal";
        else if (eatGroup.getCheckedChipId() == R.id.no_pref) eatSchedule = "No Preference";


        Map<String, Object> sleep = new HashMap<>();
        sleep.put("Eat", eatSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void studyFireBase() {
        ChipGroup studyGroup = findViewById(R.id.study_chips);

        if (studyGroup.getCheckedChipId() == R.id.music) studySchedule = "With Music";
        else if (studyGroup.getCheckedChipId() == R.id.quiet) studySchedule = "Quiet";
        else if (studyGroup.getCheckedChipId() == R.id.other_people)
            studySchedule = "With Other People";
        else if (studyGroup.getCheckedChipId() == R.id.myself) studySchedule = "By Myself";


        Map<String, Object> sleep = new HashMap<>();
        sleep.put("Study", studySchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void socialFireBase() {
        ChipGroup socialGroup = findViewById(R.id.social_chips);

        if (socialGroup.getCheckedChipId() == R.id.party_animal) socialSchedule = "Party Animal";
        else if (socialGroup.getCheckedChipId() == R.id.depends_mood)
            socialSchedule = "Depends on my mood";
        else if (socialGroup.getCheckedChipId() == R.id.couch_potato)
            socialSchedule = "Couch Potato";


        Map<String, Object> sleep = new HashMap<>();
        sleep.put("Social", socialSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void temperaureFireBase() {
        ChipGroup temperatureGroup = findViewById(R.id.temperature_chips);

        if (temperatureGroup.getCheckedChipId() == R.id.freezing) temperatureSchedule = "Freezing";
        else if (temperatureGroup.getCheckedChipId() == R.id.cold) temperatureSchedule = "Cold";
        else if (temperatureGroup.getCheckedChipId() == R.id.moderate)
            temperatureSchedule = "Moderate";
        else if (temperatureGroup.getCheckedChipId() == R.id.warm) temperatureSchedule = "Warm";
        else if (temperatureGroup.getCheckedChipId() == R.id.melting)
            temperatureSchedule = "Melting";


        Map<String, Object> temperature = new HashMap<>();
        temperature.put("Temperature", temperatureSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(temperature, SetOptions.merge());
    }

    private void apartmentFireBase() {
        ChipGroup apartmentGroup = findViewById(R.id.apartment_chips);

        if (apartmentGroup.getCheckedChipId() == R.id.apart_no) apartmentSchedule = "No";
        else if (apartmentGroup.getCheckedChipId() == R.id.apart_yes) apartmentSchedule = "Yes";


        Map<String, Object> sleep = new HashMap<>();
        sleep.put("Apartment", apartmentSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }

    private void dormFireBase() {
        ChipGroup dormGroup = findViewById(R.id.dorm_chips);

        if (dormGroup.getCheckedChipId() == R.id.dorm) dormSchedule = "Dorm";
        else if (dormGroup.getCheckedChipId() == R.id.suite) dormSchedule = "Suite";


        Map<String, Object> sleep = new HashMap<>();
        sleep.put("Dorm", dormSchedule);

        //Adding to Firebase
        DocumentReference docRef = db.collection("userData").document(user.getEmail());
        docRef.set(sleep, SetOptions.merge());
    }
}
