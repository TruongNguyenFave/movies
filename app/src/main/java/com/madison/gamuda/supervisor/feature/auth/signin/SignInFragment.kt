package com.madison.gamuda.supervisor.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.madison.gamuda.supervisor.R
import com.madison.gamuda.supervisor.extention.customview.button.PrimaryButton
import com.madison.gamuda.supervisor.extention.customview.inputview.SecondaryInputView
import com.madison.gamuda.supervisor.extention.helper.validation.CompositeValidation
import com.madison.gamuda.supervisor.extention.helper.validation.rule.NoneEmptyRule
import com.madison.gamuda.supervisor.extention.helper.validation.validation.SecondaryInputViewValidation
import com.madison.gamuda.supervisor.extention.helper.validation.validation.ValidationError
import com.madison.gamuda.supervisor.extention.helper.validation.validator.Validator
import com.madison.gamuda.supervisor.feature.base.BaseValidationFragment
import com.madison.gamuda.supervisor.feature.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseValidationFragment() {
    private lateinit var signInViewModel: SignInViewModel

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        signInViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun initView() {
        super.initView()
        btnSignIn.handleEnable(false)
    }

    override fun handleEvent() {
        super.handleEvent()
        btnSignIn.listener = object : PrimaryButton.OnPrimaryButtonListener {
            override fun onClick(view: View?) {
                signInViewModel.login(edtUserName.getText(), edtPassword.getText())
            }
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()
        signInViewModel.isLoginLoadingObservable.observe(viewLifecycleOwner, Observer {
            btnSignIn.handleLoading(it)
        })

        signInViewModel.loginSuccessObservable.observe(viewLifecycleOwner, Observer {
            navigator.startActivityAtRoot(requireActivity(), HomeActivity::class.java)
        })

        signInViewModel.loginErrorObservable.observe(viewLifecycleOwner, Observer {
            handleShowServerError(it)
        })
    }

    override fun initValidation() {
        // todo temp validation, should update later
        compositeValidation.add(
            SecondaryInputViewValidation(
                edtPassword, Validator().addRule(
                    NoneEmptyRule("Empty field")
                )
            )
        )

        compositeValidation.add(
            SecondaryInputViewValidation(
                edtUserName, Validator().addRule(
                    NoneEmptyRule("Empty field")
                )
            )
        )

        compositeValidation.setListener(object : CompositeValidation.CompositeValidationListener {
            override fun onValidationSuccess(views: List<View>) {
                for (v in views) {
                    if (v is SecondaryInputView) {
                        v.errorMsg = null
                    }
                }
            }

            override fun onValidationError(errors: List<ValidationError>) {
                for (error in errors) {
                    val v = error.view
                    if (v is SecondaryInputView) {
                        v.errorMsg = error.errorRules[0].error
                    }
                }
                btnSignIn.handleEnable(false)
            }

            override fun onAllValidationSuccess() {
                btnSignIn.handleEnable(true)
            }
        })
    }
}