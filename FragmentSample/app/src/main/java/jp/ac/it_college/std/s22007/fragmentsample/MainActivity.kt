package jp.ac.it_college.std.s22007.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import jp.ac.it_college.std.s22007.fragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.run {
            setFragmentResultListener(
                REQUEST_SELECTED_MENU, this@MainActivity, ::onSelectedMenu
            )
            setFragmentResultListener(
                REQUEST_BACK_MENU, this@MainActivity, ::onBackMenu
            )
        }
    }
    private fun onSelectedMenu(requestKey: String, bundle: Bundle){
        Log.i("SELECTED_MENU", "requestKey: ${requestKey}, bundle: ${bundle}.")

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val args = bundleOf(
                ARG_NAME to bundle.getString(RESULT_NAME, ""),
                ARG_PRICE to bundle.getInt(RESULT_RPEICE, 0)
            )
            if (binding.fragmentMainContainer != null) {
                addToBackStack("Only List")
                replace(
                    R.id.fragmentMainContainer, MenuFragment2::class.java, args)
            } else {
                // flagmentMainContainer がないのでタブレットの横向き版
                replace(R.id.fragment2Container, MenuFragment2::class.java, args)
            }
        }
    }

    private fun onBackMenu(requestKey: String, bundle: Bundle){
        if (binding.fragmentMainContainer != null){
            supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.commit {
                binding.fragment2Container?.let {
                    remove(it.getFragment())
                }
            }
        }
    }
}
