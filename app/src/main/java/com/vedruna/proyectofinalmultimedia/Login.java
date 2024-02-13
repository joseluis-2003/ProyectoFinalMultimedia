package com.vedruna.proyectofinalmultimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
/**
 * Actividad para iniciar sesión.
 */
public class Login extends AppCompatActivity {
    private TextView user;
    private TextView pass;
    private TextView message;
    private Button login;

    //ATRIBUTOS LOGIN GOOGLE
    private static final int RC_SIGN_IN = 1;

    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth mAuth;

    SignInButton signInButton;
    /**
     * Método llamado al crear la actividad.
     * @param savedInstanceState El estado previamente guardado de la actividad, si existe.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Inicializar las variables
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        message = findViewById(R.id.message);
        signInButton= findViewById(R.id.btnGoogle);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("831024423089-ieieei2feep3keo28haeu9tit28bj4fs.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    /**
     * Método llamado cuando la actividad se vuelve visible para el usuario.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    /**
     * Método para iniciar el proceso de inicio de sesión con Google.
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    /**
     * Método llamado después de que la actividad obtiene un resultado de otra actividad.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(Login.this, "Inicio de Google fallido", Toast.LENGTH_SHORT).show();

            }
        }
    }
    /**
     * Método para autenticar con Firebase usando el token de ID de Google.
     */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goHome();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Login.this, "Autenticacion Firebase fallida", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    /**
     * Método para actualizar la interfaz de usuario después de la autenticación.
     */
    private void updateUI(FirebaseUser user) {
        user= mAuth.getCurrentUser();
        if(user!=null){
            goHome();
        }
    }
    /**
     * Método para ir a la página principal de la aplicación después de iniciar sesión correctamente.
     */
    private void goHome() {
        Intent intent=new Intent(this, FrameLayaout.class);
        startActivity(intent);
        finish();
    }
    /**
     * Método para iniciar sesión con usuario y contraseña.
     * @param view La vista del botón de inicio de sesión.
     */

    public void login(View view) {
        // Obtener el usuario y la contraseña ingresados
        String usuario = user.getText().toString();
        String password = pass.getText().toString();

        // Verificar si el usuario y la contraseña son "admin"
        if (usuario.equals("vedruna") && password.equals("vedruna2003")) {
            message.setVisibility(View.VISIBLE);
            message.setText("Usuario y contraseña correctos");

            // Intent para pasar a la segunda ventana
            Intent intent = new Intent(this, FrameLayaout.class);

            // Iniciar la segunda ventana y finalizar la primera
            startActivity(intent);
            finish();
        } else {
            // Mensajes de error
            message.setVisibility(view.VISIBLE);
            message.setText("Usuario o contraseña incorrectos");
        }
    }
    /**
     * Método para iniciar sesión al presionar el botón de entrada.
     * @param view La vista del botón de entrada.
     */

    public void entrar(View view) {
        // Obtener el usuario y la contraseña ingresados
        String usuario = user.getText().toString().trim();
        String password = pass.getText().toString();

        // Verificar si el usuario y la contraseña son "admin"
        if (usuario.equals("admin") && password.equals("admin")) {
            // Mostrar Toast de inicio de sesión correcta
            Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();

            // Intent para pasar a la segunda ventana
            Intent intent = new Intent(this, FrameLayaout.class);

            // Iniciar la segunda ventana y finalizar la primera
            startActivity(intent);
            finish();
        } else {
            // Mensajes de error
            // Mostrar Toast de inicio de sesión correcta
            Toast.makeText(this, "Usario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}