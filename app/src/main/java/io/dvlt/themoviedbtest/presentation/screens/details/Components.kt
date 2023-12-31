import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    note: String,
    voteCount: String
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeaderTitle(title = title, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(6.dp))
            Note(note = "Rating:" + note)
            Spacer(modifier = Modifier.height(6.dp))
            Note(note = "Vote count:" + voteCount)
        }

        Column(modifier = Modifier.weight(3f)) {
            Image(
                painter = rememberImagePainter(data = image),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = note,
            style = MaterialTheme.typography.subtitle2
        )
    }
}


@Composable
fun MovieDetailsBar(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    note: String,
    synopsys: String,
    voteCount: String

) {
    Surface(
        elevation = 5.dp
    ) {
        Column(modifier = modifier) {
            ItemCard(
                image = image,
                title = title,
                note = note,
                modifier = Modifier.height(200.dp),
                voteCount = voteCount
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(1.dp)
                    .padding(8.dp)
            ) {
                Text(text = synopsys, style = MaterialTheme.typography.body1, textAlign = TextAlign.Justify)
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackdropImage(
    moviePoster: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.height(300.dp)) {
        Image(
            painter = rememberImagePainter(data = moviePoster) {
                crossfade(true)
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}

