package br.com.app.picpayclone.di

import br.com.app.picpayclone.ComponentesViewModel
import br.com.app.picpayclone.service.ApiService
import br.com.app.picpayclone.service.RetrofitService
import br.com.app.picpayclone.ui.pagar.PagarViewModel
import br.com.app.picpayclone.ui.home.HomeViewModel
import br.com.app.picpayclone.ui.ajuste.AjusteViewModel
import br.com.app.picpayclone.ui.transacao.TransacaoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ComponentesViewModel()
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        PagarViewModel(get())
    }
    viewModel {
        AjusteViewModel()
    }
    viewModel {
        TransacaoViewModel(get())
    }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}