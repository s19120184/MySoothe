/*
 * Copyright 2022 The Android Open Source Project
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

package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import com.codelab.basiclayouts.ui.theme.MySootheTheme
import androidx.compose.ui.res.stringResource as stringResource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            val windowSizeClass= calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass) 
        }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    TextField(value ="",
             onValueChange ={},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = null )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(id = R.string.placeholder_search))
        },
             modifier= modifier
                 .fillMaxWidth()
                 .heightIn(min = 56.dp),//altura minima que puede ser mayor

    )


}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement
            (@DrawableRes imagen : Int,
             @StringRes descripcion:  Int,
             modifier: Modifier = Modifier
) {
    // Implement composable here
    Column(//para alinear eleementos es dede column
        horizontalAlignment = Alignment.CenterHorizontally
        ,modifier =modifier ) {

         Image(painter = painterResource(imagen),
             contentDescription = null,
             contentScale=ContentScale.Crop,//imagne redonda
             modifier= Modifier
                 .size(88.dp)
                 .clip(CircleShape))

        Text(text = stringResource(descripcion),
                  modifier= Modifier.padding(top=24.dp , bottom = 8.dp),
                  style=MaterialTheme.typography.bodyMedium)
    }
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes imagen: Int ,
    @StringRes texto: Int,
    modifier: Modifier = Modifier
) {
    // Implement composable here
    Surface (
        color=MaterialTheme.colorScheme.surfaceVariant,
        shape=MaterialTheme.shapes.medium,//esquinas redondeadeas
        modifier=modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            modifier=Modifier.width(255.dp)){
             Image(
                 painter = painterResource(imagen) ,
                 contentDescription =null ,
                 contentScale=ContentScale.Crop,
                 modifier=
                 Modifier.size(80.dp))
            //Spacer(modifier = modifier.padding( horizontal = 16.dp))
            Text(
                text = stringResource(texto),
                style = MaterialTheme.typography.titleMedium,
                 modifier= Modifier.padding(horizontal = 16.dp))
        }

    }
}

// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        contentPadding= PaddingValues(horizontal = 16.dp),
        modifier=modifier
    ){
        items(alignYourBodyData){ item->
            AlignYourBodyElement(imagen = item.drawable ,descripcion = item.text )
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyHorizontalGrid(

        rows = GridCells.Fixed(2),
        contentPadding= PaddingValues(horizontal = 16.dp),
        modifier=modifier.height(168.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp))
    {

        items(favoriteCollectionsData){item->
            FavoriteCollectionCard(
                imagen = item.drawable,
                texto =item.text,
                Modifier.height(80.dp))
        }
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes titulo: Int,
    modifier: Modifier = Modifier,
    contenido: @Composable ()->Unit //recibe una funcion composable
) {
    // Implement composable here\
    Column(modifier) {
         Text(stringResource(id =titulo),
             style=MaterialTheme.typography.titleMedium,
             modifier= Modifier
                 .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                 .padding(horizontal = 16.dp)

             )
          contenido()
    }
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Implement composable here
    Column(modifier
        .verticalScroll(rememberScrollState())) {

        Spacer(Modifier.height(16.dp))
        SearchBar()
        HomeSection(titulo =R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(titulo = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer( Modifier.height(16.dp))
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
    NavigationBar(
        containerColor=MaterialTheme.colorScheme.surfaceVariant,
        modifier= Modifier) {
        NavigationBarItem(
            icon = {
               Icon(imageVector=Icons.Default.Spa,
                    contentDescription=null)
        },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )//ternima navigation bar
        NavigationBarItem(
            icon = {
                Icon(imageVector=Icons.Default.AccountCircle,
                    contentDescription=null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            },
            selected = true,
            onClick = {}
        )

    }
}

// Step: MySoothe App - Scaffold
@Composable
fun MySootheAppPortrait() {
    // Implement composable here
    Scaffold(bottomBar = { SootheBottomNavigation()}
    ) {
        padding->
        HomeScreen(Modifier.padding(padding))

        }
    }


// Step: Bottom navigation - Material
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    // Implement composable here
    NavigationRail(
        modifier=modifier.padding(start = 8.dp , end=8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier=modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment=Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    // Implement composable here
    Surface (color=MaterialTheme.colorScheme.background){
        Row {
            SootheNavigationRail()
            HomeScreen()

        }
    }

    }


// Step: MySoothe App
@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    // Implement composable here
    when(windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact->{
            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded->{
            MySootheAppLandscape()
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
){

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElemddsentPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            imagen=R.drawable.ab1_inversions,
            descripcion=R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    MySootheTheme {
        FavoriteCollectionCard(
            imagen=R.drawable.fc2_nature_meditations,
            texto=R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    //MySootheTheme { HomeSection() }
    MySootheTheme {
        HomeSection(titulo =R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}
