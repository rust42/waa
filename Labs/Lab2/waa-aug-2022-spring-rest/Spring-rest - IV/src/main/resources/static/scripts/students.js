
import { fetchContent } from "./common.js";
import { studentComponent} from "./student_component.js";

(function() {
    fetchContent("/api/students", studentComponent.empty, studentComponent.list);
})();


