const studentComponent = {
    empty: (rootElement) => {
        const containerElement = document.createElement('strong');
        containerElement.className = 'student';
        containerElement.innerText = 'No Students found';
        rootElement.appendChild(containerElement);
    },
    list: (rootElement, students) => {
        for (const element of students) {
            const name = element.firstName + ' ' + element.lastName;
            const containerElement = document.createElement('div');
            containerElement.className = 'student';

            const span1 = document.createElement('span');
            span1.innerText = element.id;

            const link = document.createElement('a');
            link.href = `/student_courses.html?student_id=${element.id}&student_name=${name}`;
            link.appendChild(span1);
            link.className = 'student-detail';

            const span2 = document.createElement('span');
            span2.className = 'student-detail';
            span2.innerText = name;

            const span3 = document.createElement('span');
            span3.className = 'student-detail';
            span3.innerText = element.email;

            const link4 = document.createElement('a');
            link4.className = 'student-detail';
            link4.innerText = "Assign course";
            link4.href = `/assign_courses.html?student_id=${element.id}&student_name=${name}`;

            containerElement.appendChild(link);
            containerElement.appendChild(span2);
            containerElement.appendChild(span3);
            containerElement.appendChild(link4);

            rootElement.appendChild(containerElement);
        }
    }
};

export { studentComponent };