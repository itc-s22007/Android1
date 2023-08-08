package jp.ac.it_college.std.s22007.lifecyclesample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import jp.ac.it_college.std.s22007.lifecyclesample.databinding.ActivitySubBinding
import kotlin.math.log

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btPrevious.setOnClickListener(::onPreviousClick)
    }

    override fun onStart() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onStart()
    }

    override fun onRestart() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onRestart()
    }

    override fun onResume() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onResume()
    }

    override fun onPause() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onPause()
    }

    override fun onStop() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("LifeCycleSample", "Sub onCreate() called")
        super.onDestroy()
    }
    private fun onPreviousClick(view: View) {
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }
}