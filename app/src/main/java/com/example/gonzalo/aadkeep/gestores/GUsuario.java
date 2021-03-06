package com.example.gonzalo.aadkeep.gestores;

import android.util.Log;

import com.example.gonzalo.aadkeep.pojo.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Gonzalo on 28/02/2016.
 */
public class GUsuario {

    private String urlDestino = "http://192.168.56.2:8080/KeepV2/go";


    public boolean isValidUser(Usuario u) {
        URL url = null;
        BufferedReader in = null;
        String res = "";
        String login;
        String pass;
        try {
            login = URLEncoder.encode(u.getEmail(), "UTF-8");
            pass = URLEncoder.encode(u.getPass(), "UTF-8");
            String destino = urlDestino + "?tabla=usuario&op=login&login=" + login + "&pass=" + pass + "&origen=android&accion=";
            url = new URL(destino);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                res += linea;
            }
            in.close();
            JSONObject obj = new JSONObject(res);
            Log.v("AAAAA1", obj.getBoolean("r") + "");
            return obj.getBoolean("r");
        } catch (MalformedURLException e) {
            Log.v("AAAAA2", e.toString());
        } catch (IOException e) {
            Log.v("AAAAA3", e.toString());
        } catch (JSONException e) {
            Log.v("AAAAA4", e.toString());
        }

        return false;
    }

}
