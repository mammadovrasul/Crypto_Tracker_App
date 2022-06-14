package app.guava.cryptotracker.presentation.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.guava.cryptotracker.presentation.util.Utils
import com.google.android.material.snackbar.Snackbar


/**
 * Created by rasulmammadov on 14,Jun,2022
 */
abstract class BaseActivity<T : ViewDataBinding?, V : BaseViewModel> :
    AppCompatActivity() {
    private var mProgressDialog: Dialog? = null
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
    }

    open fun getViewDataBinding(): T {
        return this.mViewDataBinding!!
    }

    open fun getViewModel2(): V {
        return this.mViewModel!!
    }


    open fun performDataBinding() {
//        setTheme(R.style.AppTheme)
        mViewDataBinding = DataBindingUtil.setContentView<T>(this, getLayoutId())

        mViewModel = if (mViewModel == null) getViewModel() else mViewModel


        mViewDataBinding?.executePendingBindings()

        getViewModel().loadingState.observe(this) { state ->
            if (state) showLoading() else hideLoading()
        }
    }

    open fun hideKeyboard() {
        Log.d(TAG, "hideKeyboard: ")
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun showKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    open fun showLoading() {
        hideLoading()
        mProgressDialog = Utils.showLoadingDialog(this)
    }

    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    open fun showMessage(message: String) {
        val parentLayout = findViewById<View>(android.R.id.content)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        var TAG = "Base**"
    }

}