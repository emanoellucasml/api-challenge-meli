package com.example.challengmelibootcamp.utils

class Constants {
    object API{
        public const val BASE_URL: String = "https://api.mercadolibre.com/"
        public const val AUTHORIZATION = "Authorization"
    }

    object API_HEADER{
        private const val ACCESS_TOKEN = "APP_USR-4118503939507203-070614-e4f05049854749523920a7bbcb4b525a-166140586"
        public const val AUTHORIZATION_VALUE = "Bearer ${ACCESS_TOKEN}"
    }

    object HTTP{
        public const val SUCCESS: Int = 200
        public const val NOT_FOUND: Int = 404
        public const val UNAUTHORIZED: Int = 401
    }

    object MESSAGE{
        public const val INTERNET_CONNECTION: String = "Erro, verifique sua conexao."
        public const val UKNOWN_ERROR: String = "Desculpe-nos, houve um erro desconhecido."
        public const val INVALID_CATEGORY: String = "Erro. Verifique o termo digitado."
    }

    object PRODUCT{
        public const val PRODUCT_ID = "PRODUCT_ID"
    }

    object APP{
        public const val APP_NAME = "CHALLENGE_MELI"
    }

    object SHARED{
        public const val FAVORITES = "FAVORITES"
    }
}