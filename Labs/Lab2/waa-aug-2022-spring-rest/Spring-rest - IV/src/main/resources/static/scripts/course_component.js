const courseComponent = {
    empty: (rootElement) => {
        const containerElement = document.createElement("strong");
        containerElement.className = "student";
        containerElement.innerText = "No course found";
        rootElement.appendChild(containerElement);
    },
    list: (rootElement, students) => {
        for (const element of students) {
            const containerElement = document.createElement("div");
            containerElement.className = "student";

            const span1 = document.createElement("span");
            span1.className = "student-detail";
            span1.innerText = element.id;

            const span2 = document.createElement("span");
            span2.className = "student-detail";
            span2.innerText = element.code;

            const span3 = document.createElement("span");
            span3.className = "student-detail";
            span3.innerText = element.name;

            containerElement.appendChild(span1);
            containerElement.appendChild(span2);
            containerElement.appendChild(span3);
            rootElement.appendChild(containerElement);
        }
    }
};

export { courseComponent };