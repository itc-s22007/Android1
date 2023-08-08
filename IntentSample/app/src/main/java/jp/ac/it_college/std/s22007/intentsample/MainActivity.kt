package jp.ac.it_college.std.s22007.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22007.intentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvMenu.apply {
            //アダブタとデータをセット
            adapter = MenuListAdapter(menuList){ name, price ->
                /* アイテムを選択されたときの処理はここに書くことができる */
                //インテントオブジェクトの作成
                val intent2MenuThanks = Intent(
                    this@MainActivity,
                    MenuThanksActivity::class.java
                )
                //次の画面に送るデータをセットする
                intent2MenuThanks.putExtra("menuName", name)
                intent2MenuThanks.putExtra("menuPrice", price)

                // 次の画面を起動
                startActivity(intent2MenuThanks)
            }

            LinearLayoutManager(this@MainActivity).let{
                layoutManager = it

            addItemDecoration(DividerItemDecoration(this@MainActivity, it.orientation))
            }
        }
    }
}