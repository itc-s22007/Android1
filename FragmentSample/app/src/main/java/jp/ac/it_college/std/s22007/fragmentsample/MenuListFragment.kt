package jp.ac.it_college.std.s22007.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22007.fragmentsample.databinding.FragmentMenuListBinding

internal const val REQUEST_SELECTED_MENU = "selectedManu"
internal const val RESULT_NAME = "menuName"
internal const val RESULT_RPICE = "menuPrice"

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.menuList.apply {
            adapter = MenuAdapter(teishokuList) {
                parentFragmentManager.setFragmentResult(
                    REQUEST_SELECTED_MENU, bundleOf(
                        RESULT_NAME to it.name,
                        RESULT_RPICE to it.price
                    )
                )
            }

            val manager = LinearLayoutManager(context)
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}