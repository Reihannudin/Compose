/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.rally

import android.os.Bundle
import android.service.autofill.UserData
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.compose.rally.ui.accounts.AccountsBody
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose.rally.data.UserData.accounts
import com.example.compose.rally.data.UserData.getAccount
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.components.RallyTabRow
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.RallyScreen.Accounts
import com.example.compose.rally.RallyScreen.Overview
import com.example.compose.rally.RallyScreen.Bills
/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}


private fun navigateToSingleAccount(
    navController: NavHostController,
    accountName: String
) {
    navController.navigate("${Accounts.name}/$accountName")
}


@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = RallyScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )
        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val accountsName = Accounts.name

    NavHost(
        navController = navController,
        startDestination = RallyScreen.Overview.name ,
        modifier = modifier
    ){
        composable(Overview.name){
            OverviewBody(
                onClickSeeAllAccounts = {navController.navigate(Accounts.name)},
                onClickSeeAllBills = { navController.navigate(Bills.name)},
                onAccountClick = {name ->
                    navigateToSingleAccount(navController, name)
                }
            )
        }
        composable(Accounts.name){
            AccountsBody(accounts = com.example.compose.rally.data.UserData.accounts){ name->
                navigateToSingleAccount(
                    navController = navController,
                    accountName = name
                )
            }
        }
        composable(Bills.name){
            BillsBody(bills = com.example.compose.rally.data.UserData.bills)
        }

        composable(
            "$accountsName/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                }
            ),
            deepLinks =  listOf(navDeepLink {
                uriPattern = "rally://$accountsName/{name}"
            })
        ){ entry ->
            val accountName = entry.arguments?.getString("name")
            val account = com.example.compose.rally.data.UserData.getAccount(accountName)
            SingleAccountBody(account = account)
        }
    }
}
