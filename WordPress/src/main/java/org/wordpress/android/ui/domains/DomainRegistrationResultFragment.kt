package org.wordpress.android.ui.domains

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.domain_registration_result_fragment.*
import org.wordpress.android.R
import org.wordpress.android.WordPress

class DomainRegistrationResultFragment : Fragment() {
    private var domainName: String? = null

    companion object {
        private const val EXTRA_REGISTERED_DOMAIN_NAME = "extra_registered_domain_name"

        fun newInstance(domainName: String): DomainRegistrationResultFragment {
            val fragment = DomainRegistrationResultFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_REGISTERED_DOMAIN_NAME, domainName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nonNullActivity = checkNotNull(activity)
        (nonNullActivity.application as WordPress).component()?.inject(this)

        domainName = arguments?.getString(EXTRA_REGISTERED_DOMAIN_NAME, "")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.domain_registration_result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNotNull((activity?.application as WordPress).component())
        (activity?.application as WordPress).component().inject(this)

        continue_button.setOnClickListener {
            activity!!.finish()
        }

        domain_registration_result_message.text = getString(
                R.string.domain_registration_result_description,
                domainName
        )

        continue_button.setOnClickListener {
            activity?.finish()
        }
    }
}
