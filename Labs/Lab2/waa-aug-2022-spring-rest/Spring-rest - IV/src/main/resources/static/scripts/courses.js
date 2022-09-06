
import { fetchContent } from "./common.js";
import { courseComponent } from "./course_component.js";

(function() {
    fetchContent("/api/courses", courseComponent.empty, courseComponent.list);
})();


