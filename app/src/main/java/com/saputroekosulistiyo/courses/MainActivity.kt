package com.saputroekosulistiyo.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saputroekosulistiyo.courses.data.DataSource
import com.saputroekosulistiyo.courses.model.Topic
import com.saputroekosulistiyo.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    // Fungsi onCreate dipanggil saat activity pertama kali dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        // Memungkinkan penggunaan edge-to-edge pada UI
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            // Menerapkan tema aplikasi
            CoursesTheme {
                // Membuat surface sebagai container dengan warna latar belakang dari tema
                Surface(
                    modifier = Modifier
                        .fillMaxSize()  // Memenuhi seluruh ukuran layar
                        .statusBarsPadding(),  // Menambahkan padding di sekitar status bar
                    color = MaterialTheme.colorScheme.background  // Warna dari tema yang diterapkan
                ) {
                    // Memanggil fungsi TopicGrid untuk menampilkan grid topik
                    TopicGrid(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // Menampilkan grid secara vertikal dengan dua kolom
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // Mengatur grid dengan 2 kolom
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),  // Jarak antar item secara vertikal
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),  // Jarak antar item secara horizontal
        modifier = modifier
    ) {
        // Memetakan data dari DataSource.topics dan menampilkan masing-masing item dalam TopicCard
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    // Membuat Card untuk menampilkan informasi topik
    Card {
        Row {
            // Menampilkan gambar topik
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,  // Gambar tidak memerlukan deskripsi konten
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)  // Ukuran gambar
                        .aspectRatio(1f),  // Mengatur rasio aspek gambar
                    contentScale = ContentScale.Crop  // Gambar dipotong agar sesuai dengan ukuran
                )
            }

            // Menampilkan nama topik dan jumlah kursus yang tersedia
            Column {
                // Menampilkan nama topik
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,  // Gaya teks dari tema
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                // Menampilkan ikon dan jumlah kursus
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,  // Ikon tidak memerlukan deskripsi konten
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(),  // Menampilkan jumlah kursus
                        style = MaterialTheme.typography.labelMedium,  // Gaya teks untuk label
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    // Fungsi untuk menampilkan pratinjau tampilan TopicCard
    CoursesTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)  // Contoh topik
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,  // Mengatur elemen secara vertikal di tengah
            horizontalAlignment = Alignment.CenterHorizontally  // Mengatur elemen secara horizontal di tengah
        ) {
            TopicCard(topic = topic)  // Menampilkan kartu topik dalam pratinjau
        }
    }
}

