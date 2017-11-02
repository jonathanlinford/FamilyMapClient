package jonathanlinford.familymapclient.Tasks;

import android.os.AsyncTask;

import jonathanlinford.familymapclient.DataTransfer.Requests.LoginRequestDTO;
import jonathanlinford.familymapclient.DataTransfer.Responses.LoginResponse;
import jonathanlinford.familymapclient.ServerConnection.IServer;

public class LoginTask extends AsyncTask<LoginRequestDTO, Integer, LoginResponse> {

    public interface Context {
        void onLoginComplete(LoginResponse response);
    }

    private Context context;

    public LoginTask(Context context){
        this.context = context;
    }

    @Override
    protected LoginResponse doInBackground(LoginRequestDTO... params) {
        LoginRequestDTO request = params[0];

        return IServer.login(request);
    }

    @Override
    protected void onPostExecute(LoginResponse response){
        context.onLoginComplete(response);
    }
}
