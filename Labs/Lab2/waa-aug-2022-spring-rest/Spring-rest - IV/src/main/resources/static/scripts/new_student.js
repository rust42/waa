
function  submitForm(event) {
    event.preventDefault();

    const firstName = document.getElementById("first_name_input").value;
    const lastName = document.getElementById("last_name_input").value;
    const email = document.getElementById("email_input").value;
    const major = document.getElementById("major_input").value;
    const gpa = document.getElementById("gpa_input").value;

    const response = sendInputs({ firstName, lastName, email, major, gpa: parseInt(gpa)});

    return false;
}


function sendInputs(inputs) {
    return fetch('/api/students',
        {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                    },
            body: JSON.stringify(inputs)}
    ).then(response => {
            const jsonResponse = response.json();
            window.location.href = "/students.html";
        });
}