package jonathanlinford.familymapclient.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import jonathanlinford.familymapclient.DataTransfer.Requests.LoginRequestDTO;
import jonathanlinford.familymapclient.DataTransfer.Requests.RegisterRequestDTO;
import jonathanlinford.familymapclient.R;
import jonathanlinford.familymapclient.Tasks.LoginTask;
import jonathanlinford.familymapclient.Tasks.RegisterTask;


public class LoginFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private String title;
    private Context context;


    private TextView titleTextView;
    private EditText serverHostEditText;
    private EditText serverPortEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton genderRadioMale;
    private RadioButton genderRadioFemale;
    private Button loginButton;
    private Button registerButton;

    private char gender;


    public LoginFragment(){
        //Empty public constructor
    }

    public static LoginFragment newInstance(String title){
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            title = getArguments().getString(ARG_TITLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflated this fragment's layout
        View v = inflater.inflate((R.layout.fragment_login), container, false);

        serverHostEditText = (EditText) v.findViewById(R.id.serverHostEditText);
        serverPortEditText = (EditText) v.findViewById(R.id.serverPortEditText);
        usernameEditText = (EditText) v.findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) v.findViewById(R.id.passwordEditText);
        firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) v.findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);
        genderRadioGroup = (RadioGroup) v.findViewById(R.id.genderRadioGroup);
        genderRadioMale = (RadioButton) v.findViewById(R.id.genderRadioMale);
        genderRadioFemale = (RadioButton) v.findViewById(R.id.genderRadioFemale);
        loginButton = (Button) v.findViewById(R.id.loginButton);
        registerButton = (Button) v.findViewById(R.id.registerButton);

        genderRadioMale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((RadioButton) v).isChecked())
                {
                    gender = 'm';
                }
            }
        });

        genderRadioFemale.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((RadioButton) v).isChecked())
                {
                    gender = 'f';
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LoginTask task = new LoginTask((LoginTask.Context) context);
                task.execute(getLoginRequest());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RegisterTask task = new RegisterTask((RegisterTask.Context) context);
                task.execute(getRegisterRequest());
            }
        });

        return v;
    }

    public LoginRequestDTO getLoginRequest(){
        LoginRequestDTO request = new LoginRequestDTO();

        request.setServerHost(serverHostEditText.getText().toString());
        request.setServerPort(serverPortEditText.getText().toString());
        request.setUserName(usernameEditText.getText().toString());
        request.setPassword(passwordEditText.getText().toString());

        return request;
    }

    public RegisterRequestDTO getRegisterRequest(){
        RegisterRequestDTO request = new RegisterRequestDTO();

        request.setServerHost(serverHostEditText.getText().toString());
        request.setServerPort(serverPortEditText.getText().toString());
        request.setUserName(usernameEditText.getText().toString());
        request.setPassword(passwordEditText.getText().toString());
        request.setFirstName(firstNameEditText.getText().toString());
        request.setLastName(lastNameEditText.getText().toString());
        request.setEmail(emailEditText.getText().toString());
        request.setGender(gender);

        return request;
    }

    public void setContext(Context context){
        this.context = context;
    }
}
