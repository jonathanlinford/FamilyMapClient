package jonathanlinford.familymapclient.Tasks;


import android.os.AsyncTask;

import jonathanlinford.familymapclient.DataTransfer.Requests.FamilyDataDTO;
import jonathanlinford.familymapclient.DataTransfer.Responses.*;
import jonathanlinford.familymapclient.ServerConnection.IServer;

public class RetrieveFamilyDataTask extends AsyncTask<FamilyDataDTO, Integer, FamilyDataResponse> {

    public interface Context {
        void onRetrieveFamilyDataComplete(FamilyDataResponse response);
    }


    private Context context;

    public RetrieveFamilyDataTask(Context context){
        this.context = context;
    }

    @Override
    protected FamilyDataResponse doInBackground(FamilyDataDTO... params) {
        FamilyDataDTO request = params[0];

        EventResponse eventResponse = IServer.event(request.getAuthToken(), null);
        PersonResponse personResponse = IServer.person(request.getAuthToken(), null);

        if(eventResponse.getMessage() == null &&
                personResponse.getMessage() == null){       //  Success
            return new FamilyDataResponse(null, eventResponse.getEvents(), personResponse.getPersons());
        } else{
            return new FamilyDataResponse("Errors: 1) " + eventResponse.getMessage() +
                    " 2) " + personResponse.getMessage(), null, null);
        }
    }

    @Override
    protected void onPostExecute(FamilyDataResponse response){
        context.onRetrieveFamilyDataComplete(response);
    }
}
