
function  submitForm(event) {
    event.preventDefault();

    const courseName = document.getElementById("course_name_input").value;
    const courseCode = document.getElementById("course_code_input").value;

    const response = sendInputs({ name: courseName, code: courseCode });
    return false;
}


function sendInputs(inputs) {
    return fetch('/api/courses',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inputs)}
    ).then(response => {
        const jsonResponse = response.json();
        window.location.href = "/courses.html";
    });
}