
import { fetchContent } from "./common.js";
import { assignCourseComponent } from "./assign_courses_component.js";

(function() {
    const urlSearchParams = new URLSearchParams(window.location.search);

    const studentId = urlSearchParams.get('student_id');
    const studentName = urlSearchParams.get('student_name');

    const studentNameElement = document.getElementById('student_name');
    studentNameElement.innerText = `Assign courses to ${studentName}`;

    fetchContent(`/api/courses`, assignCourseComponent.empty, assignCourseComponent.list);
})();



