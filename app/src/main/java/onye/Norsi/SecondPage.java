package onye.Norsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import onye.Norsi.DataBase.DataBase;
import onye.Norsi.Model.Friends;

public class SecondPage extends AppCompatActivity {

    Button backButton, addUsers, viewUsers;

    EditText usersName, usersAddress, usersPhone, usersEmail;

    ArrayAdapter arrayAdapter;

    ListView listView;

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        configureBackButton();

        addUsers = (Button) findViewById(R.id.addUserID);
        usersName = (EditText) findViewById(R.id.usersNameID);
        usersAddress = (EditText) findViewById(R.id.usersAddressID);
        usersPhone = (EditText) findViewById(R.id.phoneNumID);
        usersEmail = (EditText) findViewById(R.id.email_ID);
        listView = (ListView) findViewById(R.id.usersListID);
        viewUsers = (Button) findViewById(R.id.viewUserId);

        //initiating database
        dataBase = new DataBase(SecondPage.this);

        //calling in the method listview
        showAllUsers(dataBase);

        //too add users when addusers is clicked
        addUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friends friends;
                try {
                    friends = new Friends(-1, usersName.getText().toString(), usersAddress.getText().toString(),usersEmail.getText().toString(),
                            usersPhone.getText().toString());
                    Toast.makeText(SecondPage.this, friends.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    //default should print out if failed
                    Toast.makeText(SecondPage.this, "Error Creating User", Toast.LENGTH_LONG).show();
                    friends = new Friends(-1, "error name", "error address", "error email", "error phone");

                }

                DataBase dataBase = new DataBase(SecondPage.this);
                //insert user into database, using the logic for Friends above
                /*then add it into the method for adding users in db*/
                boolean success = dataBase.addUserIntoDB(friends);
                Toast.makeText(SecondPage.this, "Success= " + success, Toast.LENGTH_LONG).show();


            }
        });

        //will display items in list when saved
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Friends friends = (Friends) adapterView.getItemIdAtPosition(i);
//
//                showAllUsers(dataBase);
//                Toast.makeText(SecondPage.this, )
//
//            }
//        });
        //to show all views saved
        viewUsers.setOnClickListener((v) -> {
            DataBase dataBase = new DataBase(SecondPage.this);
            showAllUsers(dataBase);
        });


    }

    //get all users added in db
    public void showAllUsers(DataBase dataBase) {
        arrayAdapter = new ArrayAdapter <Friends> (SecondPage.this,
                //using dropdownlist
                android.R.layout.simple_dropdown_item_1line, dataBase.getAllUsers());
        listView.setAdapter(arrayAdapter);


    }


    private void configureBackButton() {
        //uses back button to go back to main page
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*stop here
                * would add another page here if I want it to connect to it*/
                finish();
            }
        });

    }

}