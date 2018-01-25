package app.its.itfabiorossi.its_androidapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import Models.LocationModel;
import app.its.itfabiorossi.its_androidapp.Interfaces.DownloadCallBack;
import app.its.itfabiorossi.its_androidapp.adapters.LocalAdapter;

public class ListActivity extends AppCompatActivity  implements DownloadCallBack.DownloadCallback {

    private  static String APPID= "d1c567ef812691b1c5598e93682b505f";
    private DownloadCallBack.DownloadCallback mCallback;
    private boolean mDownloading = false;
    private DownloadTask mDownloadTask;
    private EditText mEnterData;
    private TextView mProgressStatus;
    private Button mButton;
    private JSONObject jObj ;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mEnterData=(EditText) findViewById(R.id._enter_data);
        mProgressStatus=(TextView) findViewById(R.id._txt_progress);
        mButton=(Button) findViewById(R.id._btn_http);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //lanci ala connection
                startDownload();

            }
        });


//
        ListView mListView = (ListView) findViewById(R.id.list_view);

        //adapter standard
//        String[] citta= new String[]{"forli", "cesena"};
//        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.row,citta);
//        //passaggio adapter alla lista
//        mListView.setAdapter(adapter);


        //custom adapter
        ArrayList<LocationModel> listModel= new ArrayList<>();
        listModel.add( new LocationModel("1", "citta1"));
        listModel.add( new LocationModel("2", "citta2"));

        LocalAdapter adapter= new LocalAdapter(this, listModel);
        mListView.setAdapter(adapter);


    }

    //region HTTP CONNECTION
    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     */
    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true); // SE BODY PRESENTE, (true) LO VOGLIO TENERE
            // Open communications link (network traffic occurs here).
            connection.connect();
//            mProgressDialog=ProgressDialog.show(ListActivity.this, "Progress","Status", true);
//         mProgressStatus.setText("Status: "+ DownloadCallBack.DownloadCallback.Progress.CONNECT_SUCCESS);
            int responseCode = connection.getResponseCode(); //sincrono MA DA ESEGUIRE IN ASYNKTASK
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream(); // se risposta OK prelevo la stringa
//            mProgressStatus.setText("Status: "+ DownloadCallBack.DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 500); // TODO LIMITAZIONE 500 CARATTERI!!!
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    //region Download methods

    /**
     * Start non-blocking execution of DownloadTask.
     */

    public void startDownload() {
        //cancelDownload();
        mDownloadTask = new DownloadTask(this);
        mDownloadTask.execute("https://api.openweathermap.org/data/2.5/forecast");

    }

    /**
     * Cancel (and interrupt if necessary) any ongoing DownloadTask execution.
     */
    public void cancelDownload() {
        if (mDownloadTask != null) {
            mDownloadTask.cancel(true);
        }
    }

    @Override
    public void finishDownloading() {

        mDownloading = false;
        //TODO  STAMPA LISTA

        JSONObject jObj = new JSONObject();//data


    }
    //endregion


    //region convertitore string/Json

    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream, int maxReadSize)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }
    //endregion

    //region dowloadcallback

    @Override
    public void updateFromDownload(Object result) {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {

        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                //TODO
                mProgressStatus.setText(progressCode);

                break;
            case Progress.CONNECT_SUCCESS:
                //TODO
                mProgressStatus.setText(progressCode);
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                //TODO
                mProgressStatus.setText(progressCode);
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                //TODO
                mProgressStatus.setText(progressCode);
                mProgressDialog=ProgressDialog.show(ListActivity.this, "Progress","Status", true);

                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                //TODO
                mProgressStatus.setText(progressCode);
                mProgressDialog=ProgressDialog.show(ListActivity.this, "Progress","Status", true);

                break;
        }
    }


    //endregion

// --------------------------------- CLASS -------------------------------------
    //region ASYNKTASK DownloadTask

    /**
     * Implementation of AsyncTask designed to fetch data from the network.
     */
    private class DownloadTask extends AsyncTask<String, Integer, DownloadTask.Result> {

        private DownloadCallBack.DownloadCallback<String> mCallback;

        public DownloadTask(DownloadCallBack.DownloadCallback<String> callback) {
            setCallback(callback);
        }

//        public DownloadTask() {
//
//        }

        void setCallback(DownloadCallBack.DownloadCallback<String> callback) {
            mCallback = callback;
        }

        /**
         * Wrapper class that serves as a union of a result value and an exception. When the download
         * task has completed, either the result value or exception can be a non-null value.
         * This allows you to pass exceptions to the UI thread that were thrown during doInBackground().
         */
        class Result {
            public String mResultValue;
            public Exception mException;
            public Result(String resultValue) {
                mResultValue = resultValue;
            }
            public Result(Exception exception) {
                mException = exception;
            }
        }

        /**
         * Cancel background network operation if we do not have network connectivity.
         */
        @Override
        protected void onPreExecute() {
            if (mCallback != null) {
                NetworkInfo networkInfo = mCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    // If no connectivity, cancel task and update Callback with null data.
                    mCallback.updateFromDownload(null);
                    cancel(true);
                }
            }
        }

        /**
         * Defines work to perform on the background thread.
         */
        @Override
        protected DownloadTask.Result doInBackground(String... urls) { // da < 0 a n > variabili String !!!

            //connesso

            Result result = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
//                String urlString = urls[0];
                try {
                    //TODO creare stringa
//                    String urlString = new String("api.openweathermap.org/data/2.5/weather?id=2172797"+ URLEncoder.encode(urls[0], "UTF-8")+"&APPID="+APPID);
                    String urlString= new String( urls[0]+"&APPID="+APPID);
                    URL url = new URL(urlString);
                    String resultString = downloadUrl(url);
                    if (resultString != null) {
                        result = new Result(resultString);
                    } else {
                        throw new IOException("No response received.");
                    }
                } catch(Exception e) {
                    result = new Result(e);
                }
            }
            return result;
        }

        /**
         * Updates the DownloadCallback with the result.
         */
        @Override
        protected void onPostExecute(Result result) {
            if (result != null && mCallback != null) {
                if (result.mException != null) {
                    mCallback.updateFromDownload(result.mException.getMessage());
                } else if (result.mResultValue != null) {
                    mCallback.updateFromDownload(result.mResultValue);
                }
                mCallback.finishDownloading();
            }
            else{
                Toast.makeText(getApplicationContext(), "error connection" , Toast.LENGTH_LONG).show();

            }
        }

        /**
         * Override to add special behavior for cancelled AsyncTask.
         */
        @Override
        protected void onCancelled(Result result) {
        }

    }

    //endregion




}
