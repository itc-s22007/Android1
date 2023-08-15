package jp.ac.it_college.std.s22007.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22007.fragmentsample.databinding.FragmentMenuListBinding

/**
 * メニュー一覧を表示するためのフラグメント
 */
class MenuListFragment : Fragment() {
    //　Bindingクラスのインスタンスを入れておくプロパティ(Nullable)
    private var _binding: FragmentMenuListBinding? = null
    // Activityのときと同じように binding を使うための工夫
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.menuList.applay{
            adapter = MenuAdapter(teishokuList)
            val manager = LinearLayoutManager(context)
            layoutManeger = manager
            addItemDescoration(DividerItemDecoration(context, manager.orientation))
        }
    }
}