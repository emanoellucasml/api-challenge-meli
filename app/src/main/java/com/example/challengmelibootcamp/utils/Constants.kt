package com.example.challengmelibootcamp.utils

class Constants {
    object API{
        public const val BASE_URL: String = "https://api.mercadolibre.com/"
        public const val AUTHORIZATION = "Authorization"
    }

    object API_HEADER{
        private const val ACCESS_TOKEN = "APP_USR-4118503939507203-070414-f3da232cca5240e9e2972aef8c032c94-166140586"
        public const val AUTHORIZATION_VALUE = "Bearer ${ACCESS_TOKEN}"
    }

    object HTTP{
        public const val SUCCESS: Int = 200
    }

    object MESSAGE{
        public const val INTERNET_CONNECTION: String = "Erro, verifique sua conexao."
        public const val UKNOWN_ERROR: String = "Desculpe-nos, houve um erro desconhcido."
    }
}