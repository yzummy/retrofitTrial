package com.example.yzgv7.retrofittutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText e_id;
    private TextView t_name;
    private Retrofit retrofit;
    private APIInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btnSearch);
        e_id = findViewById(R.id.bookID);
        t_name = findViewById(R.id.bookName);



        retrofit= new Retrofit.Builder()
                .baseUrl("https://my.api.mockaroo.com/testjason.json?key=9ec96170")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIInterface.class);








    }

    public void getBookName(View view) {
        Call<Book> model = service.repo(e_id.getText().toString());
        model.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                //Log.e("Test", response.body().gettitle());
                System.out.print("Test: "+response.body().gettitle());
                System.out.print("Test: "+response.body().gettitle());

                t_name.setText(response.body().getPrice());
            }



            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }


    public interface APIInterface {
        @GET("book/{id}")
        Call<Book> repo(@Path("id") String id);

    }


    public class Book {
        private String title;
        private String price;
        private String adsfaf;
        public String getAdsfaf(){
            return adsfaf;
        }
        public void setAdsfaf(String adsfaf){
            this.adsfaf = adsfaf;
        }
        public String getPrice(){
            return price;
        }

        public void setPrice(String price){
            this.price=price;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String title) {
            this.title = title;
        }
    }


}
