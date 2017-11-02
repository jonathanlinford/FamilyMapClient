package jonathanlinford.familymapclient.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;


import jonathanlinford.familymapclient.Activities.Filter.FilterActivity;
import jonathanlinford.familymapclient.DataTransfer.InternalData;
import jonathanlinford.familymapclient.DataTransfer.Requests.FamilyDataDTO;
import jonathanlinford.familymapclient.Fragments.MapFragment;
import jonathanlinford.familymapclient.DataTransfer.Responses.FamilyDataResponse;
import jonathanlinford.familymapclient.DataTransfer.Responses.LoginResponse;
import jonathanlinford.familymapclient.Fragments.LoginFragment;
import jonathanlinford.familymapclient.R;
import jonathanlinford.familymapclient.Tasks.*;


public class MainActivity extends AppCompatActivity implements LoginTask.Context, RegisterTask.Context, RetrieveFamilyDataTask.Context{

    private LoginFragment loginFragment;
    public static MapFragment mapFragment;

    public static InternalData data = new InternalData();
    public Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new InternalData();
        if(savedInstanceState == null)
            loadLoginFragment();
        else
            loadMapFragment();
    }


    @Override
    public void onLoginComplete(LoginResponse response){
        if(response.getMessage() == null){
            Toast.makeText(getBaseContext(), "Login Succeeded. Retrieving Family Data...", Toast.LENGTH_SHORT).show();
            RetrieveFamilyDataTask newTask = new RetrieveFamilyDataTask(this);
            newTask.execute(new FamilyDataDTO(response.getAuthToken(), response.getPersonID()));
        } else{
            Toast.makeText(getBaseContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_filter_set, menu);
        return true;
    }

    @Override
    public void onRegisterComplete(LoginResponse response){
        if(response.getMessage() == null){
            Toast.makeText(getBaseContext(), "Register Succeeded. Retrieving Family Data...", Toast.LENGTH_SHORT).show();
            RetrieveFamilyDataTask newTask = new RetrieveFamilyDataTask(this);
            newTask.execute(new FamilyDataDTO(response.getAuthToken(), response.getPersonID()));
        } else{
            Toast.makeText(getBaseContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRetrieveFamilyDataComplete(FamilyDataResponse response){
        if(response.getMessage() == null){
            data.setPersons(response.getPersons());
            data.setEvents(response.getEvents());

            String firstName = data.getPersons()[0].getFirstName();
            String lastName = data.getPersons()[0].getLastName();
            Toast.makeText(getBaseContext(), "Thanks " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();

            loadMapFragment();
        } else{
            Toast.makeText(getBaseContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMapFragment() {
        FragmentManager fm = getSupportFragmentManager();

        mapFragment = new MapFragment();

            fm.beginTransaction()
                    .replace(R.id.loginFrameLayout, mapFragment)
                    .commit();

        menu.setGroupVisible(R.id.menuGroup, true);
    }

    private void loadLoginFragment(){
        FragmentManager fm = getSupportFragmentManager();
        loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFrameLayout);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance("Login");
            fm.beginTransaction()
                    .add(R.id.loginFrameLayout, loginFragment)
                    .commit();
        }

        loginFragment.setContext(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        this.menu = menu;
        menu.setGroupVisible(R.id.menuGroup, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.searchMenuItem:
                intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.filterMenuItem:
                intent = new Intent(getBaseContext(), FilterActivity.class);
                startActivity(intent);
                break;
            case R.id.settingsMenuItem:
                intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            default:
        }
        return true;
    }
}

