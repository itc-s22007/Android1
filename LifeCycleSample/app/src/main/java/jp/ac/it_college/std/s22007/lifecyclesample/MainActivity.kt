package jp.ac.it_college.std.s22007.lifecyclesample


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import jp.ac.it_college.std.s22007.lifecyclesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNext.setOnClickListener(::onNextClick)
    }

    override fun onStart() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onStart()
    }

    override fun onResume() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onResume()
    }

    override fun onPause() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onPause()
    }

    override fun onStop() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onRestart()
    }

    private fun onNextClick(view: View) {
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }
}