
import { fetchContent } from "./common.js";
import { courseComponent } from "./course_component.js";

(function() {
    const urlSearchParams = new URLSearchParams(window.location.search);

    const studentId = urlSearchParams.get('student_id');
    const studentName = urlSearchParams.get('student_name');

    const studentNameElement = document.getElementById('student_name');
    studentNameElement.innerText = `Courses for ${studentName}`;

    fetchContent(`/api/students/${studentId}/courses`, courseComponent.empty, courseComponent.list);
})();


