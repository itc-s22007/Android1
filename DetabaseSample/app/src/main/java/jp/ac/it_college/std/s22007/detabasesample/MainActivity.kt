package jp.ac.it_college.std.s22007.detabasesample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22007.detabasesample.databinding.ActivityMainBinding
import jp.ac.it_college.std.s22007.detabasesample.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _helper: DatabaseHelper

    private var _cocktailId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _helper = DatabaseHelper(this)

        initList(binding.lvCocktail)

        binding.btnSave.setOnClickListener(::onSaveButton)
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

    private fun initList(list: RecyclerView) {
        val data = resources.getStringArray(R.array.lv_cocktail_list)
        val adapter = CocktailAdapter(data.toList()) { pos, name ->
            // 選択されたカクテル名を画面上に表示
            binding.tvCocktailName.text = name

            _cocktailId = pos.toLong()

            binding.etNote.setText(loadCocktailMemos())
        }
        val manager = LinearLayoutManager(this)
        list.adapter = adapter
        list.layoutManager = manager
        list.addItemDecoration(
            DividerItemDecoration(this, manager.orientation)
        )
    }

    private fun onSaveButton(view: View) {
        //入力された感想を取得
        val note = binding.etNote.text.toString()
        //データベースヘルパーオブジェクトからデータベース接続オブジェクトを習得
        val db = _helper.writableDatabase
        //既存のデータを削除してから、新たな感想を保存する方式
        val deleteSQL = """
            | DELETE FROM cocktail_memos
            | WHERE _id = ?
        """.trimMargin()
        db.compileStatement(deleteSQL).let { stmt ->
            stmt.bindLong(1, _cocktailId)
            stmt.executeUpdateDelete()
        }

        val insertSQL = """
            | INSERT INTO cocktail_memos (_id, name, note)
            | VALUES (?, ?, ?)
        """.trimMargin()
        db.compileStatement(insertSQL).let { stmt ->

            stmt.bindLong(1, _cocktailId)
            stmt.bindString(2, binding.tvCocktailName.text.toString())
            stmt.bindString(3, note)

            stmt.executeInsert()
        }
        binding.etNote.setText("")
        binding.tvCocktailName.text = ""
        _cocktailId = 0
    }

    private fun loadCocktailMemos(): String {
        val db = _helper.readableDatabase

        val select = """
            | SELECT * FROM cocktail_memos
            | WHERE _id = ?
        """.trimMargin()
        val cursor = db.rawQuery(select, arrayOf("$_cocktailId"))

        return cursor.use {
            if (it.moveToNext()) {
                it.getString(it.getColumnIndexOrThrow("note"))
            } else {
                ""
            }
        }
    }
}