package app.guava.cryptotracker.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import app.guava.cryptotracker.presentation.util.ErrorHandler

/**
 * Created by rasulmammadov on 14,Jun,2022
 */
abstract class BaseFragment<T : ViewDataBinding?, V : BaseViewModel?> : Fragment() {

    private lateinit var mActivity: BaseActivity<Nothing, Nothing>

    private var mRootView: View? = null
    var viewDataBinding: T? = null
    private var mViewModel: V? = null

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity = context as BaseActivity<Nothing, Nothing>
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
        mViewModel!!.getErrorLiveData().observe(this) { throwable ->
            try {
                val errorHandler = ErrorHandler(mActivity)
                val message: String = errorHandler.handleError(throwable)
                mActivity.showMessage(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()

        mViewModel?.loadingState?.observe(
            viewLifecycleOwner
        ) { state -> if (state) showLoading() else hideLoading() }

    }

    val baseActivity: BaseActivity<Nothing, Nothing>
        get() = mActivity

    private fun hideKeyboard() {
        mActivity.hideKeyboard()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun showLoading() {
        mActivity.showLoading()
    }

    private fun hideLoading() {
        mActivity.hideLoading()
    }

}