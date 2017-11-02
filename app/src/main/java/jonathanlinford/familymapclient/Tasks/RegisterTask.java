package jonathanlinford.familymapclient.Tasks;

import android.os.AsyncTask;

import jonathanlinford.familymapclient.DataTransfer.Requests.RegisterRequestDTO;
import jonathanlinford.familymapclient.DataTransfer.Responses.LoginResponse;
import jonathanlinford.familymapclient.ServerConnection.IServer;

public class RegisterTask extends AsyncTask<RegisterRequestDTO, Integer, LoginResponse> {

    public interface Context {
        void onRegisterComplete(LoginResponse response);
    }

    private Context context;

    public RegisterTask(Context context){
        this.context = context;
    }

    @Override
    protected LoginResponse doInBackground(RegisterRequestDTO... params) {
        RegisterRequestDTO request = params[0];

        return IServer.register(request);
    }


    @Override
    protected void onPostExecute(LoginResponse response){
        context.onRegisterComplete(response);
    }
}

