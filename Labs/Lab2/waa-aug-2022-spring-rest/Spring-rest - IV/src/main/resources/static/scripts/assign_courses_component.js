const assignCourseComponent = {
    empty: (rootElement) => {
        const formElement = document.getElementById('assign_course_form');
        formElement.hidden = true;

        const containerElement = document.createElement('p');
        containerElement.className = "student";
        containerElement.innerText = "No course found";

        const link = document.createElement('a');
        link.innerText = 'Create courses here';
        link.href = '/new_course.html';

        rootElement.appendChild(containerElement);
        rootElement.appendChild(link);
    },
    list: (rootElement, courses) => {
        const formElement = document.getElementById('assign_course_form');
        formElement.hidden = false;

        const selectElement = document.getElementById('courses_select');

        for (const course of courses) {
            const optionElement = document.createElement('option');
            optionElement.innerText = course.name;
            optionElement.value = course.id;
            selectElement.appendChild(optionElement);
        }
    }
};

export { assignCourseComponent };