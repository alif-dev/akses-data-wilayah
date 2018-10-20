package com.example.alifm.dokumentasiwilayah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alifm.dokumentasiwilayah.api.ApiClient;
import com.example.alifm.dokumentasiwilayah.api.ApiInterface;
import com.example.alifm.dokumentasiwilayah.model.Data;
import com.example.alifm.dokumentasiwilayah.model.Region;
import com.example.alifm.dokumentasiwilayah.model.UniqueCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnGetApiData;

    private Spinner spinnerProv;
    private Spinner spinnerKab;
    private Spinner spinnerKec;
    private Spinner spinnerKel;

    private TextView uniqueCodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetApiData = findViewById(R.id.btn_get_api_data);
        spinnerProv = findViewById(R.id.spinner_prov);
        spinnerKab = findViewById(R.id.spinner_kab);
        spinnerKec = findViewById(R.id.spinner_kec);
        spinnerKel = findViewById(R.id.spinner_kel);
        uniqueCodeTextView = findViewById(R.id.uniqueCode_textView);

        loadUniqueCode();
    }

    public void loadUniqueCode() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UniqueCode> call = apiService.getUniqueCode();

        call.enqueue(new Callback<UniqueCode>() {
            @Override
            public void onResponse(Call<UniqueCode> call, Response<UniqueCode> response) {
                uniqueCodeTextView.setText(response.body().getUniqueCode());
            }

            @Override
            public void onFailure(Call<UniqueCode> call, Throwable t) {

            }
        });
    }

    public void loadProvinceList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final String code = "MeP7c5ne" + uniqueCodeTextView.getText().toString();

        Call<Data> call = apiService.getProvinceList(code);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                final List<Region> daftarProvinsi = response.body().getData();

                // masukkan daftar provinsi ke array string
                String[] provs = new String[daftarProvinsi.size()];
                for (int i = 0; i < daftarProvinsi.size(); i++) {
                    provs[i] = daftarProvinsi.get(i).getName();
                }

                // masukkan daftar provinsi ke spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_item, provs);
                spinnerProv.setAdapter(adapter);

                spinnerProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //idProvTextView.setText(daftarProvinsi.get(position).getId() + "");
                        int idProv = daftarProvinsi.get(position).getId();
                        loadKabupatenList(code, idProv);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void loadKabupatenList(final String code, final int idProv) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getKabupatenList(code, idProv);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                final List<Region> daftarKabupaten = response.body().getData();

                // masukkan daftar kabupaten ke array string
                String[] kabs = new String[daftarKabupaten.size()];
                for (int i = 0; i < daftarKabupaten.size(); i++) {
                    kabs[i] = daftarKabupaten.get(i).getName();
                }

                // masukkan daftar kabupaten ke spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_item, kabs);
                spinnerKab.setAdapter(adapter);

                spinnerKab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int idKab = daftarKabupaten.get(position).getId();
                        loadKecamatanList(code, idKab);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void loadKecamatanList(final String code, int idKab) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getKecamatanList(code, idKab);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                final List<Region> daftarKecamatan = response.body().getData();

                // masukkan daftar kecamatan ke array string
                String[] kecs = new String[daftarKecamatan.size()];
                for (int i = 0; i < daftarKecamatan.size(); i++) {
                    kecs[i] = daftarKecamatan.get(i).getName();
                }

                // masukkan daftar kecamatan ke spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_item, kecs);
                spinnerKec.setAdapter(adapter);

                spinnerKec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int idKec = daftarKecamatan.get(position).getId();
                        loadKelurahanList(code, idKec);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void loadKelurahanList(final String code, final int idKec) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getKelurahanList(code, idKec);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                final List<Region> daftarKelurahan = response.body().getData();

                // masukkan daftar kelurahan ke array string
                String[] kels = new String[daftarKelurahan.size()];
                for (int i = 0; i < daftarKelurahan.size(); i++) {
                    kels[i] = daftarKelurahan.get(i).getName();
                }

                // masukkan daftar kelurahan ke spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_item, kels);
                spinnerKel.setAdapter(adapter);

                spinnerKel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void getApiData(View view) {
        loadProvinceList();
    }
}