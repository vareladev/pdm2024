package com.evarela.coursemanager.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evarela.coursemanager.data.database.entity.Course

@Composable
fun ListItem(
    course: Course,
    onItemClick: (Course) -> Unit = {}
){
    TextButton(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { onItemClick(course) },
        shape = CutCornerShape(0f)
    ) {
        Text(text = "${course.code} - ${course.title}")
    }
}