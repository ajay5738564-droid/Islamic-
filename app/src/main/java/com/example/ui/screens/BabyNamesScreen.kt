package com.example.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.repository.BabyNameGender
import com.example.data.repository.BabyNamesRepository

@Composable
fun BabyNamesScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
 var gender by remember { mutableStateOf(BabyNameGender.BOY) }
 var query by remember { mutableStateOf("") }
 val source = if (gender == BabyNameGender.BOY) BabyNamesRepository.boys else BabyNamesRepository.girls
 val shown = source.filter { it.name.contains(query, true) || it.meaningHindi.contains(query, true) }
 Column(modifier.fillMaxSize().testTag("baby_names_screen")) {
  Row(Modifier.fillMaxWidth().padding(12.dp)) {
   IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
   Text("Muslim Baby Names", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
  }
  Row(Modifier.padding(horizontal = 12.dp)) {
   FilterChip(gender == BabyNameGender.BOY, { gender = BabyNameGender.BOY }, label = { Text("लड़के (${BabyNamesRepository.boys.size})") })
   Spacer(Modifier.width(8.dp))
   FilterChip(gender == BabyNameGender.GIRL, { gender = BabyNameGender.GIRL }, label = { Text("लड़कियाँ (${BabyNamesRepository.girls.size})") })
  }
  OutlinedTextField(query, { query = it }, Modifier.fillMaxWidth().padding(12.dp).testTag("baby_names_search"), label = { Text("नाम या अर्थ खोजें") }, singleLine = true)
  Text("${shown.size} नाम मिले", Modifier.padding(horizontal = 16.dp))
  LazyColumn(contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
   items(shown, key = { it.name }) { name ->
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(14.dp)) {
     Text(name.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
     Text("अर्थ: ${name.meaningHindi}")
    } }
   }
  }
 }
}
