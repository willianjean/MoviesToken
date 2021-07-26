package br.com.tokenlab.android.moviestoken.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.tokenlab.android.moviestoken.MainActivity
import br.com.tokenlab.android.moviestoken.R
import br.com.tokenlab.android.moviestoken.databinding.HomeFragmentBinding
import br.com.tokenlab.android.moviestoken.network.ErrorResponse
import br.com.tokenlab.android.moviestoken.network.NetworkResponse
import br.com.tokenlab.android.moviestoken.network.TokenlabApi
import br.com.tokenlab.android.moviestoken.network.model.dto.MoviesResponseDTOItem
import br.com.tokenlab.android.moviestoken.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels<HomeViewModel>{ viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //chamada desnecessaria pra teste
        //val view:MoviesResponseDTOItem = viewModel.getMoviesList()


        binding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        attachObserver()

        return binding.root
    }

    private fun attachObserver(){
        listOfMoviesObserver()
    }

    private fun listOfMoviesObserver(){
        viewModel.listOfMovies.observe(viewLifecycleOwner, Observer { lists ->
            lists?.let {
                binding.rvListsOfMovies.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                binding.rvListsOfMovies.adapter = MoviesAdapter(requireContext(), lists)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}