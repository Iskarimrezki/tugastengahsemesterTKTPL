package id.ac.ui.cs.mobileprogramming.muhammad_rezki.notasuar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
            val toMain  = Intent(this@SplashScreen, MainActivity ::class.java)
            startActivity(toMain)
            finish()
        }, 3000)
    }
}