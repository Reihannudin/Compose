package com.ktor.di



import com.ktor.repository.HeroRepository
import com.ktor.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}