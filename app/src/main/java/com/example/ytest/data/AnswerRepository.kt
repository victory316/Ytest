package com.example.ytest.data


class AnswerRepository private constructor(private val dao: AnswerDao) {

    companion object {

        @Volatile
        private var instance: AnswerRepository? = null

        fun getInstance(dao: AnswerDao) =
            instance
                ?: synchronized(this) {
                instance
                    ?: AnswerRepository(
                        dao
                    )
                        .also { instance = it }
            }
    }
}
