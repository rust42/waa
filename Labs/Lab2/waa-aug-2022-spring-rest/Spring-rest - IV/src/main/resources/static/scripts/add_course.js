function assignCourse(event) {
    event.preventDefault();

    const urlSearchParams = new URLSearchParams(window.location.search);

    const studentId = urlSearchParams.get('student_id');
    const studentName = urlSearchParams.get('student_name');

    const selectElement = document.getElementById('courses_select');
    const courseId = selectElement.value;
    sendCourseRequest(studentId, studentName, courseId);
    return false;
}

function sendCourseRequest(studentId, studentName, courseId) {
     fetch(`/api/students/${studentId}/courses/${courseId}/assign`,
         {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json'
             }
         }
    ).then(response => {
        window.location.href = `/student_courses.html?student_id=${studentId}&student_name=${studentName}`;
    });
}
