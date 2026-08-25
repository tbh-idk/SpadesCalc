package com.example.spadescalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.spadescalc.ui.theme.SpadesCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpadesCalcTheme {
                Screen()
            }
        }
    }
}

@Composable
fun GenDropdown(
    selectedText: String,
    onItemSelected: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf("Select an option") }
    val items = List(14) { "${it}" }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer, // Your custom background color
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer   // Your custom text/icon color
            )) {
            Text(text = selectedText)
        }

        // Dropdown Menu Surface
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, // Closes menu when clicking outside

        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        onItemSelected(label)
                        expanded = false // Hide menu after selection
                    }
                )
            }
        }
    }
}
@Composable
fun PointsHolder(
    selectedText: String,
){
    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf("Select an option") }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Button(
            onClick = { expanded = false },
            colors = ButtonDefaults.filledTonalButtonColors(
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer, // Your custom background color
                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer  // Your custom text/icon color
            ),
            enabled = false
        ) {
            Text(text = selectedText)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, // Closes menu when clicking outside

        ) {
            DropdownMenuItem(
                text = { Text(text = selectedText) },
                onClick = {
                    expanded = false // Hide menu after selection
                }
            )
        }
    }
}


@Composable
fun Group1Selections(scorecard: Scorecard) {
//    var g1p1Bid by remember { mutableStateOf("0") }
//    var g1p2Bid by remember { mutableStateOf("0") }
//    var g1p1Tricks by remember { mutableStateOf("0") }
//    var g1p2Tricks by remember { mutableStateOf("0") }

    Column (modifier = Modifier
        .padding(20.dp)) {
        Text(text = "Player 1 Bid")
        GenDropdown(scorecard.group1Player1HandBids.toString(), {scorecard.group1Player1HandBids=it.toInt()})
        Text(text = "Player 2 Bid")
        GenDropdown(scorecard.group1Player2HandBids.toString(), {scorecard.group1Player2HandBids=it.toInt()})
        HorizontalDivider(thickness = 1.dp)
        Text(text = "Player 1 Tricks")
        GenDropdown(scorecard.group1Player1HandTricks.toString(), {scorecard.group1Player1HandTricks=it.toInt()})
        Text(text = "Player 2 Tricks")
        GenDropdown(scorecard.group1Player2HandTricks.toString(), {scorecard.group1Player2HandTricks=it.toInt()})
    }
}
@Composable
fun Group2Selections(scorecard: Scorecard) {
//    var g2p1Bid by remember { mutableStateOf("0") }
//    var g2p2Bid by remember { mutableStateOf("0") }
//    var g2p1Tricks by remember { mutableStateOf("0") }
//    var g2p2Tricks by remember { mutableStateOf("0") }

    Column (modifier = Modifier
        .padding(20.dp)) {
        Text(text = "Player 1 Bid")
        GenDropdown(scorecard.group2Player1HandBids.toString(), {scorecard.group2Player1HandBids=it.toInt()})
        Text(text = "Player 2 Bid")
        GenDropdown(scorecard.group2Player2HandBids.toString(), {scorecard.group2Player2HandBids=it.toInt()})
        HorizontalDivider(thickness = 1.dp)
        Text(text = "Player 1 Tricks")
        GenDropdown(scorecard.group2Player1HandTricks.toString(), {scorecard.group2Player1HandTricks=it.toInt()})
        Text(text = "Player 2 Tricks")
        GenDropdown(scorecard.group2Player2HandTricks.toString(), {scorecard.group2Player2HandTricks=it.toInt()})
    }
}

@Composable
fun Group1Points(scorecard: Scorecard) {

    Column (modifier = Modifier
        .padding(20.dp)) {
        Text(text = "Group 1 Bags")
        PointsHolder(scorecard.group1TotalBags.toString())
        Text(text = "Group 1 Total Points")
        PointsHolder(scorecard.group1TotalPoints.toString())
    }
}
@Composable
fun Group2Points(scorecard: Scorecard) {

    Column (modifier = Modifier
        .padding(20.dp)) {
        Text(text = "Group 2 Bags")
        PointsHolder(scorecard.group2TotalBags.toString())
        Text(text = "Group 2 Total Points")
        PointsHolder(scorecard.group2TotalPoints.toString())
    }
}

@Composable
fun Screen(viewModel: SpadesViewModel = viewModel()) {
    val scorecard = viewModel.scorecard
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Row(modifier = Modifier.statusBarsPadding()) {
            Box(modifier = Modifier.weight(1f).padding(3.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        FilledTonalButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContentColor = MaterialTheme.colorScheme.onSecondary
                            ),
                            enabled = false
                        ) {
                            Text(text = "GROUP 1", fontSize = 24.sp)
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        Group1Selections(scorecard)
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        FilledTonalButton(
                            onClick = { scorecard.calculateG1() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Text(text = "Calculate", fontSize = 22.sp)
                        }
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        Group1Points(scorecard)
                    }
                }

            }
//            VerticalDivider(
//                thickness = 2.dp,
//                modifier = Modifier.padding(1.dp)
//            )
            Box(modifier = Modifier.weight(1f).padding(3.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        FilledTonalButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContentColor = MaterialTheme.colorScheme.onSecondary
                            ),
                            enabled = false
                        ) {
                            Text(text = "GROUP 2", fontSize = 24.sp)
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        Group2Selections(scorecard)
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        FilledTonalButton(
                            onClick = { scorecard.calculateG2() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Text(text = "Calculate", fontSize = 22.sp)
                        }
                    }

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        modifier = Modifier.fillMaxWidth().padding(3.dp)
                    ) {
                        Group2Points(scorecard)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpadesCalcTheme {
        Screen()
    }
}

